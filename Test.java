import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.*;
import java.lang.Math;
import java.io.*;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;
   
public class Test{
   
   //public static
   //private static 
   //protected final
   public static boolean stop = false;
   //public static DrawingPanel p1 = new DrawingPanel(600,730);
   //public static Graphics g1 = p1.getGraphics();
   
   public static void main(String[] args)
         throws FileNotFoundException{
      //Date d1 = new Date();
      //System.out.println(d1.getDate()+"/"+ (d1.getMonth()+1)+"/"+(d1.getYear()+1900));

      /*Console cnsl = null;
      
      try {
         cnsl = System.console();

         if (cnsl != null) {
            String fmt = "%1$-8s %2$-9s %3$s%n";
            
            // format
            cnsl.printf(fmt, "Items", "Quanity", "Price");
            cnsl.printf(fmt, "------", "-------", "-----");
            cnsl.printf(fmt, "Tomato", "  1Kg", " $15");
            cnsl.printf(fmt, "Potato", "  5Kg", " $50");
            cnsl.printf(fmt, "Onion", "  2Kg", " $30");
            cnsl.printf(fmt, "Apple", "  4Kg", " $80");
         }      
         
      } catch(Exception ex) {
         
         // if any error occurs
         ex.printStackTrace();     
      }*/
      DrawingPanel p = new DrawingPanel(500,350);
      Graphics2D g = p.getGraphics();
//       Vector3 vec = new Vector3(30.0, -30.0, 0);
//       double sin = vec.magnitude()*Math.sin(vec.direction()[0] + 15.0/180*Math.PI);
//       double cos = vec.magnitude()*Math.cos(vec.direction()[0] + 15.0/180*Math.PI);
//       Vector3 vec2 = new Vector3(cos, sin, 0);
//       System.out.println(vec2.magnitude()+" " + vec.magnitude());
//       System.out.println(vec2.direction()[0]/Math.PI*180+" " + vec.direction()[0]/Math.PI*180);
//       g.drawLine(250,175,250+(int)vec.x, 175-(int)vec.y);
//       g.drawLine(250,175,250+(int)vec2.x, 175-(int)vec2.y);
      int[] center = new int[]{250, 175};
      Polygon shape = new Polygon(rect(new Vector3(center[0], center[1], 0), 41, 55, Math.PI/4));
      Polygon shape2 = new Polygon(rect(new Vector3(center[0]-10, center[1]-10, 0), 30, 60, Math.PI/3));
      System.out.println(Polygon.intersect(shape, shape2));
            g.drawPolygon(shape.getIntCordArray()[0], shape.getIntCordArray()[1], shape.size());
            g.drawPolygon(shape2.getIntCordArray()[0], shape2.getIntCordArray()[1], shape2.size());
//       System.out.println(shape.getVecList());
//       Vector3.setSort(s, true);
//       Vector3.setCenter(s, new double[]{-250.0, -175.0, 0.0});
// //       for(Vector3 vec: s)
// //          System.out.println(vec.direction()[0]+" d");
//       Arrays.sort(s);
//       for(Vector3 vec: s)
//          System.out.println(vec.direction()[0]+" d");
//       Vector3.setCenter(s, new double[]{250.0, 175.0, 0.0});
//       int[] xPoints = new int[s.length], yPoints = new int[s.length];
//       int i = 0;
//       for(Vector3 vec: s){
//          xPoints[i] = (int)vec.x;
//          yPoints[i] = (int)vec.y;
//          i++;
//       }
//       System.out.println(Math.acos(s[3].x/s[3].magnitude())/Math.PI*180);
//       g.drawPolygon(shape.getIntCordArray()[0], shape.getIntCordArray()[1], shape.size());
//       p.addKeyListener(new KeyListener(){
//          @Override
//          public void keyPressed(KeyEvent e) {
//             switch(e.getKeyCode()){
//                case KeyEvent.VK_UP:
// //                   for(int i = 0; i < yPoints.length; i++)
// //                      yPoints[i]--;
//                   shape.translate(Vector3.down());
//                   break;
//                case KeyEvent.VK_DOWN:
// //                   for(int i = 0; i < yPoints.length; i++)
// //                      yPoints[i]++;
//                   shape.translate(Vector3.up());
//                   break;
//                case KeyEvent.VK_RIGHT:
// //                   for(int i = 0; i < xPoints.length; i++)
// //                      xPoints[i]++;
//                   shape.translate(Vector3.right());
//                   break;
//                case KeyEvent.VK_LEFT:
// //                   for(int i = 0; i < xPoints.length; i++)
// //                      xPoints[i]--;
//                   shape.translate(Vector3.left());
//                   break;
//                case KeyEvent.VK_SPACE:
//                   shape.rotateXY(1.0/180*Math.PI);
//                   break;
//                default:
//             }
//             p.clear();
//             g.drawPolygon(shape.getIntCordArray()[0], shape.getIntCordArray()[1], shape.size());
//             g.drawPolygon(shape2.getIntCordArray()[0], shape2.getIntCordArray()[1], shape2.size());
//             boolean b = Polygon.intersect(shape, shape2);
//             if(b)
//                g.setBackground(Color.YELLOW);
//             else
//                g.setBackground(Color.WHITE);
// //             g.drawPolygon(xPoints, yPoints, s.length);
//          }
//          @Override
//          public void keyTyped(KeyEvent e) {}
//          @Override
//          public void keyReleased(KeyEvent e) {}
//       });
//       while(true){}
         
//       Vector3 v1 = new Vector3(80, 0, 0), v2 = new Vector3(30, -40, 0);
//       int[] xPoints = new int[]{center[0], center[0] + (int)v1.x, center[0] + (int)v2.x};
//       int[] yPoints = new int[]{center[1], center[1] - (int)v1.y, center[1] - (int)v2.y};
//       g.drawPolygon(xPoints, yPoints, 3);
//       g.drawLine(center[0], center[1], center[0] + (int)v1.x, center[1] - (int)v1.y);
//       g.drawLine(center[0], center[1], center[0] + (int)v2.x, center[1] - (int)v2.y);
//       int radius = (int)Vector3.min(v1,v2).magnitude();
//       int startAngle = (int)(Math.min(v1.direction()[0], v2.direction()[0]) / Math.PI * 180);
//       int angle = (int)(Vector3.angle(v1, v2) / Math.PI * 180);
//       if(Math.max(v1.direction()[0], v2.direction()[0]) > Math.PI)
//          angle = -angle;
//       g.drawArc(center[0]-radius/2, center[1]-radius/2, radius, radius, startAngle, angle);
//       System.out.println(angle+":"+startAngle+":"+radius);
   }
   
//    public static Vector3[] square(Vector3 center, double length, double rotate){
//       rotate = Math.PI * 1.75 - rotate / 180 * Math.PI;
//       double diagMag = Math.sqrt(2 * length * length);
//       Vector3 diagonal = new Vector3(Math.cos(rotate) * diagMag, Math.sin(rotate) * diagMag, 0);
//       System.out.println(diagonal);
//       return new Vector3[]{center.add(diagonal.multiply(-1.0)), center.add(diagonal),
//                            new Vector3(center.x - diagonal.y, center.y + diagonal.x, 0),
//                             
//                            new Vector3(center.x + diagonal.y, center.y - diagonal.x, 0)};
//    }
   
   public static Vector3[] rect(Vector3 center, double height, double width, double rotate){
      double angle1 = Math.atan(height / width) + rotate;// / 180 * Math.PI\\\;
      double angle2 = rotate - Math.atan(height / width);
      double diagMag = Math.sqrt(height * height + width * width)/2;
      Vector3 diagonal1 = new Vector3(Math.cos(angle1) * diagMag, -Math.sin(angle1) * diagMag, 0);
      Vector3 diagonal2 = new Vector3(Math.cos(angle2) * diagMag, -Math.sin(angle2) * diagMag, 0);
      Vector3[] r = new Vector3[]{Vector3.add(center,diagonal1.multiply(-1.0)), Vector3.add(center,diagonal1),
                                  Vector3.add(center,diagonal2.multiply(-1.0)), Vector3.add(center,diagonal2)};
      Vector3.setSort(r, true);
      Vector3.setCenter(r, center);
      Arrays.sort(r);
//       for(Vector3 vec: r)
//          System.out.println(Math.acos(vec.x/vec.magnitude())/Math.PI*180+"deg:"+vec.direction()[0]+"rad |"+vec.x+"(x)/"+vec.y+"(y)");
      Vector3.setCenter(r, center.multiply(-1));
      //g.drawLine((int)center.x, (int)center.y, (int)center.x + (int)diagonal1.x, (int)center.y - (int)diagonal1.y);
      return r;
   }
   
   public static void addToXTwice(int a, Point p1) {
      a = a + a;
      p1.x = a;
      System.out.println(a + " " + p1.x);
   }
   
   public static void boyGirl(File in)throws FileNotFoundException {
      Scanner lineScan = new Scanner(in);
      int boys = 0, girls = 0, bInt =0, gInt = 0, people = 0;
      while(lineScan.hasNext()){
         String str = lineScan.next();
         System.out.println(str);
         people++;
         if(people%2==1){
            boys++;
            bInt+=lineScan.nextInt();
         } else if(people%2==0){
            girls++;
            gInt+=lineScan.nextInt();
         }
      }
      System.out.println(boys+" boys, "+girls+" girls"); 
      System.out.println(bInt+"/"+gInt);
      System.out.println("Difference between boys' and girls' sums: "+Math.abs(gInt-bInt));
   }
   
   public static void collapseSpaces(File in)throws FileNotFoundException {
      Scanner fileScan = new Scanner(in);
      while(fileScan.hasNextLine()){
         Scanner lineScan = new Scanner(fileScan.nextLine());
         while(lineScan.hasNext())
            System.out.print(lineScan.next()+" ");
         System.out.println();
      }
   }
               
   public static void integer(){
      Scanner console = new Scanner(System.in);
      System.out.print("how many integers? ");
      int num = console.nextInt();
      int sum = 0, max = 0, temp = 0;
      for (int i = 0; i < num; i++){
         System.out.print("next integer? ");
         temp = console.nextInt();
         if (temp % 2 == 0){
            sum = sum + temp;
            max = Math.max(max, temp);
         }
      }
      System.out.println("even sum = " + sum);
      System.out.println("even max = " + max);
   }
   
   public static void printPrimes(int max) {
       if (max >= 2) {
           System.out.print("2");
           for (int i = 3; i <= max; i++) {
               if (countFactors(i) == 2) {
                   System.out.print(", " + i);
               }
           }
           System.out.println();
       }
   }

   // Returns how many factors the given number has.
   public static int countFactors(int number) {
       int count = 0;
       for (int i = 1; i <= number; i++) {
           if (number % i == 0) {
               count++;   // i is a factor of number
           }
       }
       return count;
   }
   
   //sum of all digits
   public static int digitSum(int i){
      int sum = 0;
      while(i>0){
         sum += i%10;
         i = i/10;
      }
      return sum;
   }        
   
   //tell if key is at the same position in 2 strings
   public static boolean keySamePos(String s1, String s2, String key){
      int index1 = 0, index2 = 0;
      while(index1 == index2){
         index1 = s1.indexOf(key);
         index2 = s2.indexOf(key);
         if(index1 == -1 && index2 == -1)
            return true;
         //System.out.println(s1+":"+index1+" / "+s2+":"+index2);
         s1 = s1.substring(index1+1);
         s2 = s2.substring(index2+1);
      }
      return false;
   }
   
   //andrew -> nardwe
   public static String reverse(String ss){
      String str = new String();
      for(int i = 1; i<ss.length();i+=2){
         str=str+ss.charAt(i)+ss.charAt(i-1);
      }
      if(ss.length()%2!=0)
         str=str+ss.charAt(ss.length()-1);
      return str; 
   } 
  
   public static void drawfigure(){
      for(int i = 0; i <= 4; i++){
         for(int j = 0; j<= (4-i)*4; j++){
            System.out.print("/");
         }
         for(int k = 0; k < 8*i; k++){
            System.out.print("*");
         }
         for(int j = 0; j< (4-i)*4; j++){
            System.out.print("\\");   
         }
         System.out.println();
      } 
   }
   
   public static boolean isPalindrome(String word){
      word = word.toLowerCase();
      for(int i = 0;i <= word.length()/2;i++){
         if(word.charAt(i)!=word.charAt(word.length()-1-i))
            return false;
      }
      return true;
   }
   
   public static boolean isSorted(double[] arr){
      for(int i = 0;i < arr.length-1;i++){
         if(arr[i]>arr[i+1])
            return false;
      }
      return true;
   }
   
   public static int[] countLastDigits(int[] arr){
      int[] digits = new int[10];
      for(int i = 0; i < arr.length; i++)
         digits[arr[i]%10]++;
      return digits;
   }
   
   public static int[] countLastDigits(String s1){
      int[] counts = new int[5];
      char[] vowels = {'a','e','i','o','u'};
      s1 = s1.toLowerCase();
      for(int i = 0; i < s1.length(); i++){
         for(int j = 0; j < vowels.length; j++){
            if(s1.charAt(i) == vowels[j])
               counts[j]++;
         }
         /*if(s1.charAt(i) == 'a')
            vowels[0]++;
         else if(s1.charAt(i) == 'e')
            vowels[1]++;
         else if(s1.charAt(i) == 'i')
            vowels[2]++;
         else if(s1.charAt(i) == 'o')
            vowels[3]++;
         else if(s1.charAt(i) == 'u')
            vowels[4]++;*/
      }
      return counts;
   }
   
}  


