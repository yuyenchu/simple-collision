import java.lang.Math;
import java.util.*;

public class Vector3 implements Comparable<Vector3>{
   public double x, y, z;
   public boolean sortByAngle;
   
   public Vector3(double x, double y, double z, boolean b){
      this.x = x;
      this.y = y;
      this.z = z;
      sortByAngle = b;
   }
   
   public Vector3(double x, double y, double z){
      this(x, y, z, false);
   }
   
   public Vector3(){
      this(0,0,0,false);
   }
   
   public Vector3(Vector3 vec){
      this(vec.x, vec.y, vec.z, vec.sortByAngle);
   }
   
   public static Vector3 right(){
      return new Vector3(1,0,0);
   }
   
   public static Vector3 left(){
      return new Vector3(-1,0,0);
   }
   
   public static Vector3 up(){
      return new Vector3(0,1,0);
   }
   
   public static Vector3 down(){
      return new Vector3(0,-1,0);
   }
   
   public static Vector3 forward(){
      return new Vector3(0,0,1);
   }
   
   public static Vector3 back(){
      return new Vector3(0,0,-1);
   }
   
   public static Vector3 one(){
      return new Vector3(1,1,1);
   }
   
   public static boolean equals(Vector3 vec1, Vector3 vec2){
      if(vec1.x == vec2.x && vec1.y == vec2.y && vec1.z == vec2.z)
         return true;
      else 
         return false;
   }
   
   public boolean equals(Vector3 other){
      return equals(this, other);
   }
   
   public static Vector3 add(Vector3 vec1, Vector3 vec2){
      return new Vector3(vec1.x+vec2.x, vec1.y+vec2.y, vec1.z+vec2.z);
   }
   
   public void add(Vector3 other){
      x += other.x;
      y += other.y;
      z += other.z;
   }
  
   public static Vector3 add(Vector3 vec, double d){
      return new Vector3(vec.x+d, vec.y+d, vec.z+d);
   }
   
   public void add(double d){
      x += d;
      y += d;
      z += d;
   }
  
   public static double dotProduct(Vector3 vec1, Vector3 vec2){
      return (vec1.x * vec2.x + vec1.y * vec2.y + vec1.z * vec2.z);
   }
   
   public double dotProduct(Vector3 other){
      return dotProduct(this, other);
   }
  
   public static Vector3 multiply(Vector3 vec, double d){
      return new Vector3(vec.x*d, vec.y*d, vec.z*d);
   }
   
   public Vector3 multiply(double d){
      return multiply(this, d);
   }
   
   public static Vector3 pow(Vector3 vec, double d){
      return new Vector3(Math.pow(vec.x, d), Math.pow(vec.y, d), Math.pow(vec.z, d));
   }
   
   public Vector3 pow(double d){
      return pow(this, d);
   }
   
   public static Vector3 max(Vector3 vec1, Vector3 vec2){
      double x = Math.max(vec1.x, vec2.x);
      double y = Math.max(vec1.y, vec2.y);
      double z = Math.max(vec1.z, vec2.z);
      return new Vector3(x, y, z);
   }
   
   public static Vector3 min(Vector3 vec1, Vector3 vec2){
      double x = Math.min(vec1.x, vec2.x);
      double y = Math.min(vec1.y, vec2.y);
      double z = Math.min(vec1.z, vec2.z);
      return new Vector3(x, y, z);
   }
    
   public static Vector3 diff(Vector3 vec1, Vector3 vec2){
      return new Vector3(vec1.x - vec2.x,vec1.y - vec2.y, vec1.z - vec2.z);
   }
   
   public Vector3 diff(Vector3 other){
      return diff(this, other);
   }

   public static Vector3 absDiff(Vector3 vec1, Vector3 vec2){
      Vector3 diff = diff(vec1, vec2);
      diff.x = Math.abs(diff.x);
      diff.y = Math.abs(diff.y);
      diff.z = Math.abs(diff.z);
      return diff;
   }
   
   public Vector3 absDiff(Vector3 other){
      return absDiff(this, other);
   }
   
   public static double distance(Vector3 start, Vector3 end){
      Vector3 diff = new Vector3(absDiff(start, end));
      diff.x = diff.x * diff.x;
      diff.y = diff.y * diff.y;
      diff.z = diff.z * diff.z;
      return Math.sqrt(diff.x + diff.y + diff.z);
   }
   
   public double distance(Vector3 other){
      return distance(this, other);
   }
   
   public static double magnitude(Vector3 vec){
      return distance(vec, new Vector3());
   }
   
   public double magnitude(){
      return distance(this, new Vector3());
   }
 
   public String toString(){
      return "[" + x + ", " + y + ", " + z + "]";   
   }  
   
   public static Vector3[] normal(Vector3 vec1, Vector3 vec2){
      double x = vec1.y * vec2.z - vec1.z * vec2.y;
      double y = vec1.z * vec2.x - vec1.x * vec2.z;
      double z = vec1.x * vec2.y - vec1.y * vec2.x;
      return new Vector3[]{new Vector3(x, y, z), new Vector3(-x, -y, -z)};
   }
   
   public Vector3[] normal(Vector3 other){
      return normal(this, other);
   }   
   
   //return the angle between this vector and x, y, z axis(from 0 ~ 2*PI)
   //dimession problem: not fixed   
   public double[] direction(){
      double m = magnitude();
      return new double[]{absAngle(x, y, m), absAngle(y, z, m), absAngle(z, x, m)};
   }
   
   private double absAngle(double axis1, double axis2, double mag){
      double angle = Math.acos(axis1/mag);
      if(axis2 < 0)
         angle = Math.PI*2 - angle;
      return angle;
   }
   
   //return the angle between 2 vectors
   public static double angle(Vector3 vec1, Vector3 vec2){
      return Math.acos(dotProduct(vec1, vec2) / (vec1.magnitude() * vec2.magnitude()));
   }
   
   public double angle(Vector3 other){
      return angle(this, other);
   }
   
   public static Vector3 unitVec(Vector3 vec){
      double m = vec.magnitude();
      return new Vector3(vec.x / m, vec.y / m, vec.z / m);
   }
   
   public Vector3 unitVec(){
      return unitVec(this);
   }
   
   public static void setSort(Vector3[] vecArray, boolean b){
      for(Vector3 vec: vecArray)
         vec.sortByAngle = b;
   }
   
   public static void setCenter(Vector3[] vecArray, Vector3 center){
      if(vecArray.length < 1 || center == null)
         throw new IllegalArgumentException();
      for(Vector3 vec: vecArray){
         vec.x -= center.x;
         vec.y -= center.y;
         vec.z -= center.z;
      }
   }
   
//    public static void setCenter(Vector3[] vecArray, double[] center){
//       if(center.length < 3)
//          throw new IllegalArgumentException();
//       setCenter(vecArray, new double[]{center.x, center.y, center.z});
//    }
   
   public static void setCenter(Collection<Vector3> vecs, Vector3 center){
      if(vecs.isEmpty() || center == null)
         throw new IllegalArgumentException();
      for(Vector3 vec: vecs){
         vec.x -= center.x;
         vec.y -= center.y;
         vec.z -= center.z;
      }
   }
   
   public int compareTo(Vector3 other){
      if(sortByAngle && other.sortByAngle){
         if(direction()[0] == other.direction()[0]){
            if(direction()[1] == other.direction()[1])
               return getSign(direction()[2] - other.direction()[2]);
            return getSign(direction()[1] - other.direction()[1]);
         }
         return getSign(direction()[0] - other.direction()[0]);
      }else{
         if(magnitude() == other.magnitude()){
            if(x == other.x){
               if(y == other.y)
                  return getSign(z - other.z);
               return getSign(y - other.y);
            }
            return getSign(x - other.x);
         }
         return getSign(magnitude() - other.magnitude());
      }
   }
   
   private int getSign(double d){
      if(d == 0)
         return 0;
      return d > 0 ? 1 : -1;
   }
}