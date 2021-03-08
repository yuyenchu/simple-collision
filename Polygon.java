import java.util.*;
import java.lang.Math;

public class Polygon{
   private Vector3 centroid;
   private ArrayList<Vector3> vertexes, edges;
   public double restitution, angularVel;
   public Vector3 velocity;
   
   public Polygon(Collection<Vector3> points){
      if(points == null || points.isEmpty())
         throw new IllegalArgumentException();
      vertexes = new ArrayList<Vector3>();
      Set<Double> angles = new TreeSet<Double>();
      for(Vector3 vec: points){
         if(!vertexes.contains(vec)){
            if(angles.contains(vec.direction()[0]) || vec == null)
               throw new IllegalArgumentException();
            vertexes.add(vec);
            angles.add(vec.direction()[0]);
         }
      }
      angularVel = 0;
      velocity = new Vector3();
      centroid = getCenter();
      Vector3.setCenter(vertexes, centroid);
//       for(int i = 0; i < vertexes.size(); i++){
//          if(vertexes.get(i).magnitude() < vertexes.get((i-1 < 0) ? vertexes.size()-1 : i-1).magnitude()&&
//             vertexes.get(i).magnitude() < vertexes.get((i+1 > vertexes.size()-1) ? 0 : i+1).magnitude())
//             throw new IllegalArgumentException("Concaved polygon");
//       }
      Vector3.setCenter(vertexes, centroid.multiply(-1));
      buildEdges();
      restitution = 1;
   }
   
   public Polygon(Vector3[] vecs){
      this(Arrays.asList(vecs));
   }
   
   private Vector3 getCenter(){
      double centroidX = 0, centroidY = 0, centroidZ = 0;
      for(Vector3 vec: vertexes){
         centroidX += vec.x;
         centroidY += vec.y;
         centroidZ += vec.z;
      }
      return new Vector3(centroidX/vertexes.size(), centroidY/vertexes.size(),
                         centroidZ/vertexes.size());
   }
   
   public void translate(Vector3 translation){
      for(int i = 0; i < vertexes.size(); i++)
         vertexes.get(i).add(translation);
      centroid = getCenter(); 
   }
   
   public void move(){
      move(1);
   }
   
   public void move(double d){
      translate(velocity.multiply(d));
      rotateXY(angularVel * d);
   }
   
   public void rotateXY(double rotation){
      centroid = getCenter(); 
      Vector3.setCenter(vertexes, centroid);
      for(int i = 0; i < vertexes.size(); i++){
         vertexes.get(i).x = vertexes.get(i).magnitude() * 
                             Math.cos(vertexes.get(i).direction()[0] + rotation);
         vertexes.get(i).y = vertexes.get(i).magnitude() * 
                             Math.sin(vertexes.get(i).direction()[0] + rotation);
      }
      Vector3.setCenter(vertexes, centroid.multiply(-1));
   }
   
   public List<Vector3> getVecList(){
      sort();
      return vertexes;
   }
   
   public double[][] getCordArray(){
      double[][] cords = new double[3][vertexes.size()];
      Vector3[] vecs = getVecArray();
      for(int i = 0; i < vecs.length; i++){
         cords[0][i] = vecs[i].x;
         cords[1][i] = vecs[i].y;
         cords[2][i] = vecs[i].z;
      }
      return cords;
   }
   
   public int[][] getIntCordArray(){
      int[][] cords = new int[3][vertexes.size()];
      Vector3[] vecs = getVecArray();
      for(int i = 0; i < vecs.length; i++){
         cords[0][i] = (int)vecs[i].x;
         cords[1][i] = (int)vecs[i].y;
         cords[2][i] = (int)vecs[i].z;
      }
      return cords;
   }
   
   public Vector3[] getVecArray(){
      sort();
      Vector3[] vecs = new Vector3[size()];
      getVecList().toArray(vecs);
      return vecs;
   }
   
   public int size(){
      return vertexes.size();
   }
   
   public void sort(){
      Vector3.setCenter(vertexes, centroid);
      Collections.sort(vertexes);
      Vector3.setCenter(vertexes, centroid.multiply(-1));
   }
   
   public double areaXY(){
      sort();
      double area = 0;
      for(int i = 0; i < vertexes.size()-1; i++){
         area += (vertexes.get(i).x * vertexes.get(i+1).y -
                 vertexes.get(i).y * vertexes.get(i+1).x);
      }
      area += (vertexes.get(vertexes.size()-1).x * vertexes.get(0).y -
              vertexes.get(vertexes.size()-1).y * vertexes.get(0).x);
      return Math.abs(area/2);
   }
   
   private void buildEdges(){
		Vector3 vec1, vec2;
		edges = new ArrayList<Vector3>();
		for (int i = 0; i < size(); i++) {
			vec1 = vertexes.get(i);
			vec2 = vertexes.get((i + 1 >= size()) ? 0 : i+1);
			edges.add(Vector3.add(vec2,vec1.multiply(-1)));
		}
   }
   
   public static PolygonCollisionResult intersect(Polygon p1, Polygon p2,
                                                  double updateTime){
      PolygonCollisionResult result = new PolygonCollisionResult();
      result.intersect = true;
      double minDeltaDist = Double.MAX_VALUE;
      Vector3 translationAxis = new Vector3();
      Vector3 edge;
      // Loop through all the edges of both polygons
      for (int i = 0; i < p1.size() + p2.size(); i++) {
         if (i < p1.size()) {
            edge = p1.edges.get(i);
         } else {
            edge = p2.edges.get(i - p1.size());
         }      
         
      
         // ===== 1. Find if the polygons are currently intersecting =====
         // Find the axis perpendicular to the current edge
         Vector3 axis = Vector3.unitVec(new Vector3(-edge.y, edge.x, 0));
         
         // Find the projection of the polygon on the current axis
         double[] A = projectPolygon(axis, p1);
         double[] B = projectPolygon(axis, p2);
         
         // Check if the polygon projections are currentlty intersecting
         if (intervalDistance(A[0], A[1], B[0], B[1]) > 0)
            result.intersect = false;
            
         double vProjection = axis.dotProduct(Vector3.add(p2.velocity,
                              p1.velocity.multiply(-1)).multiply(updateTime));
         if(vProjection < 0)
            A[0] += vProjection;
         else
            A[1] += vProjection;
         double deltaDist = intervalDistance(A[0], A[1], B[0], B[1]);
            if(deltaDist <= 0){
               result.collisionNormal = axis;
               result.penetrationDepth = -deltaDist;
            } else{
               result.penetrationDepth = 0;
               return result;
            }
      }
      return result;
   }
 
//         // ===== 2. Now find if the polygons *will* intersect =====
// 
//         // Project the velocity on the current axis
//         float velocityProjection = axis.DotProduct(velocity);
// 
//         // Get the projection of polygon A during the movement
//         if (velocityProjection < 0) {
//             minA += velocityProjection;
//         } else {
//             maxA += velocityProjection;
//         }
// 
//         // Do the same test as above for the new projection
//         float intervalDistance = IntervalDistance(minA, maxA, minB, maxB);
//         if (intervalDistance > 0) result.WillIntersect = false;
// 
//         // If the polygons are not intersecting and won't intersect, exit the loop
//         if (!result.Intersect && !result.WillIntersect) break;
// 
//         // Check if the current interval distance is the minimum one. If so store
//         // the interval distance and the current distance.
//         // This will be used to calculate the minimum translation vector
//         intervalDistance = Math.Abs(intervalDistance);
//         if (intervalDistance < minIntervalDistance) {
//             minIntervalDistance = intervalDistance;
//             translationAxis = axis;
// 
//             Vector d = polygonA.Center - polygonB.Center;
//             if (d.DotProduct(translationAxis) < 0)
//                 translationAxis = -translationAxis;
//         }
//     }
// 
//     // The minimum translation vector
//     // can be used to push the polygons appart.
//       if (result.WillIntersect)
//          result.MinimumTranslationVector = translationAxis * minIntervalDistance;
//       return result;
//     
//       return true;
   
	private static double[] projectPolygon(Vector3 axis, Polygon p) {
		// To project a point on an axis use the dot product
		double d = axis.dotProduct(p.vertexes.get(0));
		double min = d, max = d;
		for (int i = 0; i < p.size(); i++) {
			d = p.vertexes.get(i).dotProduct(axis);
			if (d < min) 
				min = d;
			else {
				if (d > max)
					max = d;
			}
		}
      return new double[]{min , max};
	}
   
	public static double intervalDistance(double minA, double maxA, double minB, double maxB) {
		return (minA < minB) ? (minB - maxA) : (minA - maxB);
	}
   
   
   
//    public static Vector3 collidAt(Polygon p1, Polygon p2){
//       return new Vector3();
//    }
}