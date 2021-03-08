import java.util.*;
import java.lang.*;
public class Test2{
   public static void main(String[] args){
      Vector3 v1 = new Vector3(3,2,3);
      Vector3 v2 = new Vector3(0,2,6);
      Vector3 v3 = new Vector3(-3,-4,-5);
      
      // v1.add(v3);
//       v2.add(v3);
      System.out.println(v1.normal(v2)[0]);
   }
}