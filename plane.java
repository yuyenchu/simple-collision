public class Plane{
   private Vector3 normal, point;
   
   public Plane(Vector3 normal, Vector3 point){
      this.normal = new Vector3(normal);
      this.point = new Vector3(point);
   }
   
   public Plane(Vector3 pt1, Vector3 pt2,Vector3 pt3){
      pt1.multiply(-1);
      pt2.add(pt1);
      pt3.add(pt1);
      this.normal = new Vector3(pt2.normal(pt3)[0]);
      this.point = new Vector3(pt1.multiply(-1));
   }
   
   public double[] coefficients(){
      return new double[]{normal.x, normal.y, normal.z, 
             -1*(normal.x*point.x + normal.y*point.y + normal.z*point.z)};
   }
   
   public String toString(){
      return normal.x+"(x-"+point.x+")"+normal.y+"(y-"+point.y+")"+normal.z+"(z-"+point.z+")=0";
   }
}