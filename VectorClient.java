import java.util.*;

public class VectorClient{

   private static Scanner console = new Scanner(System.in);
   private static List<Vector3> vectors = new ArrayList<Vector3>();
   
   public static void main(String[] args){
      char ch = getChoice("cvasnoq", 1);
      //vectors.add(new Vector3(100.1,100.2,100.3));
      //vectors.add(new Vector3(200.1,200.2,200.3));
      while(ch != 'q'){
         switch(ch){
            case 'c':
               vectors.add(new Vector3(create('x'), create('y'), create('z')));
               break;
            case 'v':
               printVec();
               break;
            case 'a':
               addVec();
               break;
            case 's':
               subVec();
               break;
            case 'o':
               operateVec();
               break;
            case 'n':
               findNormal();
               break;
         }   
         ch = getChoice("cvasnoq", 1);  
      }
   }
   
   public static boolean answer(String message){
      System.out.println(message + "(y/n)?");
      String str = console.next();
      return str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes");
   }
   
   public static char getChoice(String options, int type){
      if(type == 1)
         printOptions();
      else if (type ==2)
         printOperateOptions();
      String ans = console.next().toLowerCase();
      if(ans.replace('-', ' ').replaceAll("["+options+"]","-").charAt(0) != '-'){
         System.out.println("This is not an option");
         System.out.println();
         return getChoice(options, type);
      }      
      return ans.charAt(0);
   }
   
   public static void printOptions(){
      System.out.println("What do you want to do? options:");
      System.out.println("create new vector(c) , view vectors(v), add vectors(a) "+
                         ", subtract vectors(s), find normal(n), operate vector(o), quit(q)");
      System.out.print("=>");    
   }
   
   public static void printOperateOptions(){
      System.out.println("What do you want to do? options:");
      System.out.println("multiply(*/m), add(+/a), subtract(-/s), to power(^/p)");
      System.out.print("=>");    
   }
   
   public static double create(char ch){
      boolean b = false;
      double d = 0.0;
      do{
      System.out.print("please enter the "+ch+" for the vector: ");
      if(console.hasNextDouble()){
         d = console.nextDouble();
         b = answer(ch + " = " + d +"is this value correct");
      } else
         System.out.println("this is not an able entry, please enter a number");
      }while(!b);
      return d;
   }
   
   public static void printVec(){
      int count = 0;
      System.out.printf("index---vector(x, y, z)\n");
      for(Vector3 vec: vectors){
         System.out.printf("%-8s%s\n", count+":", vec.toString().substring(1, vec.toString().length()-1));
         count++;
      }
   }
   
   public static void addVec(){
      int[] iarray = getVecs();
      Vector3 temp = Vector3.add(vectors.get(iarray[0]), vectors.get(iarray[1]));
      System.out.println("sum of vectors: " + temp);
      if(answer("add this vector to list"))
         vectors.add(temp);
   }
   
   public static void subVec(){
      int[] iarray = getVecs();
      Vector3 temp = Vector3.add(vectors.get(iarray[0]), vectors.get(iarray[1]).multiply(-1));
      System.out.println("remainder of vectors: " + temp);
      if(answer("add this vector to list"))
         vectors.add(temp);
   }
   
   public static void findNormal(){
      int[] iarray = getVecs();
      Vector3[] temp = Vector3.normal(vectors.get(iarray[0]), vectors.get(iarray[1]).multiply(-1));
      System.out.println("normal of vectors: " + temp[0] +"/" + temp[1]);
      if(answer("add this vector to list")){
         vectors.add(temp[0]);
         vectors.add(temp[1]);
      }
   }
   
   public static int[] getVecs(){
      int i1 = -1, i2 = -1;
      do{
         System.out.print("index of first vector: ");
         i1 = console.nextInt();
         if(i1 < 0 || i1 > vectors.size()-1)
            System.out.println("index out of range");
      }while(i1 < 0 || i1 > vectors.size()-1);
      do{
         System.out.print("index of second vector: ");
         i2 = console.nextInt();
         if(i2 == i1)
            System.out.println("it's same with the first index");
         else if(i2 < 0 || i1 > vectors.size()-1)
            System.out.println("index out of range");
      }while(i2 < 0 || i2 > vectors.size()-1 || i2 == i1);
      System.out.println(i1+"/"+i2);
      return new int[]{i1, i2};
   }
   
   public static void operateVec(){
      int index = -1;
      do{
         System.out.print("index of the vector: ");
         index = console.nextInt();
         if(index < 0 || index > vectors.size()-1)
            System.out.println("index out of range");
      }while(index < 0 || index > vectors.size()-1);
      switch(getChoice("masp*+-^", 2)){
         case 'm':
         case '*':
            vectors.set(index, vectors.get(index).multiply(getNum("multiply by")));
            break;
         case 'a':
         case '+':
            vectors.set(index, vectors.get(index).add(getNum("add by")));
            break;
         case 's':
         case '-':
            vectors.set(index, vectors.get(index).add(-1*getNum("subtract by")));
            break;
         case 'p':
         case '^':
            vectors.set(index, vectors.get(index).pow(getNum("to the power of")));
            break;
      }
   }
   
   public static double getNum(String message){
      while(true){
         System.out.print(message +": ");
         if(console.hasNextDouble())
            return console.nextDouble();
      }
   }
}   