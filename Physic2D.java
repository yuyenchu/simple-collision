import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.util.List;

public class Physic2D{
   private static ArrayList<Polygon> shapes;
   private static final boolean isGravity = false;
   private static final double Gravity = 9.80665;
   private static final double SleepTime = 100, Milli = 1000;
   private static final double CorrectionPercent = 0.2; // usually 20% to 80%
   private static final double Slop = 0.01; // usually 0.01 to 0.1
   
   public static void main(String args[]){
      shapes = new ArrayList<Polygon>();
      DrawingPanel p = new DrawingPanel(500, 600);
      Graphics g = p.getGraphics();
      int[] center = new int[]{250, 175};
      shapes.add(new Polygon(rect(new Vector3(center[0], center[1], 0), 30, 60, Math.PI/3)));
      shapes.add(new Polygon(rect(new Vector3(center[0]-100, center[1], 0), 30, 60, Math.PI/4)));
//       shapes.add(new Polygon(rect(new Vector3(center[0]-50, center[1]+50, 0), 30, 60, Math.PI/4)));
      System.out.println(Polygon.intersect(shapes.get(0), shapes.get(1), 1).intersect);
      shapes.get(0).velocity = Vector3.left().multiply(10);
      shapes.get(1).velocity = Vector3.right().multiply(10);
//       shapes.get(2).velocity = Vector3.up().multiply(10);
      shapes.get(0).restitution = 1;
      shapes.get(1).restitution = 1;
      while(true){
         p.clear();
         for(Polygon shape: shapes){
            shape.move(SleepTime/Milli);
            g.drawPolygon(shape.getIntCordArray()[0], shape.getIntCordArray()[1],
                          shape.size());
            if(isGravity)
               shape.velocity.y += (Gravity*SleepTime/Milli);
         }
         try{Thread.sleep((long)SleepTime);}catch(Exception e){}
         for(int i = 0; i < shapes.size(); i++){
            for(int j = i+1; j < shapes.size(); j++){
               PolygonCollisionResult result = Polygon.intersect(shapes.get(i),
                                               shapes.get(j),SleepTime/Milli);
               if(result.collisionNormal != null)
                  collision(shapes.get(i), shapes.get(j), result.collisionNormal);
                  if(result.penetrationDepth > 0)
                     correction(shapes.get(i), shapes.get(j), result.penetrationDepth,
                                result.collisionNormal);
               
            }
         }
      }
   }
   
   public static void collision(Polygon p1, Polygon p2, Vector3 normal){
      // Calculate relative velocity
      Vector3 rv = Vector3.add(p2.velocity, p1.velocity.multiply(-1));
    
      // Calculate relative velocity in terms of the normal direction
      double velAlongNormal = normal.dotProduct(rv);
    
      // Do not resolve if velocities are separating
      if(velAlongNormal <= 0){
         // Calculate restitution
         double e = Math.min(p1.restitution, p2.restitution);
       
         // Calculate impulse scalar
         double j = -(1 + e) * velAlongNormal;
         j /= 1 / p1.areaXY() + 1 / p2.areaXY();
         
         // Apply impulse
         Vector3 impulse = normal.multiply(j);
         p1.velocity.add(impulse.multiply(-1 / p1.areaXY()));
         p2.velocity.add(impulse.multiply(1 / p2.areaXY()));
      }
   }
   
   public static void correction(Polygon p1, Polygon p2, double depth, Vector3 n){
      if((depth - Slop) > 0){
         Vector3 correction = n.multiply((depth - Slop)/(1/p1.areaXY()+1/p2.areaXY())*CorrectionPercent);
//          correction.y *= -1;
         p1.translate(correction.multiply(-1/p1.areaXY()));
         p2.translate(correction.multiply(1/p2.areaXY()));
      }
   }
   
   public static Vector3[] rect(Vector3 center, double height, double width, double rotate){
      double angle1 = Math.atan(height / width) + rotate;
      double angle2 = rotate - Math.atan(height / width);
      double diagMag = Math.sqrt(height * height + width * width)/2;
      Vector3 diagonal1 = new Vector3(Math.cos(angle1) * diagMag, -Math.sin(angle1) * diagMag, 0);
      Vector3 diagonal2 = new Vector3(Math.cos(angle2) * diagMag, -Math.sin(angle2) * diagMag, 0);
      Vector3[] r = new Vector3[]{Vector3.add(center,diagonal1.multiply(-1.0)), Vector3.add(center,diagonal1),
                                  Vector3.add(center,diagonal2.multiply(-1.0)), Vector3.add(center,diagonal2)};
      Vector3.setSort(r, true);
      Vector3.setCenter(r, center);
      Arrays.sort(r);
      Vector3.setCenter(r, center.multiply(-1));
      return r;
   }
}