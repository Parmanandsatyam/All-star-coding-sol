/*
TJHSST Junior 5
ACSL All-Stars 2012-2013
ACSL Check Sum
*/

import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class Check_Sum {

   public static void main(String[] args) throws Exception {
   
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      
      for (int counter = 1; counter <= 5; counter++) {
      
         out.println("Please enter the data for input #" + counter + ": ");    
         
         long x = Long.parseLong(in.readLine());
         
         if (method(x))
            out.println("VALID");
         else
            check(x);
         
      }
      
      in.close();
   
   }
   
   private static void check(long x) {
      
      char[] line = (x + "").toCharArray();
      
      String temp = "901234567890";
      
      for (int i = 0; i < line.length-1; i++) {
      
         char c = line[i];
         
         line[i] = temp.charAt(temp.indexOf(c + "", 1) + 1);
         
         String s = convert(line);
         
         if (method(Long.parseLong(s))) {
            out.println(s);
            return;
         }
         line[i] = temp.charAt(temp.indexOf(c + "", 1) - 1);
         
         s = convert(line);
         
         if (method(Long.parseLong(s))) {
            out.println(s);
            return;
         }
         
         line[i] = c;
                    
      }
   
   }
   
   private static String convert(char[] line) {
   
      String ans = "";
      
      for (char c: line)
         ans += c;
      
      return ans;
      
   }
   
   private static boolean method(long x) {
   
      int check = (int)(x % 10);
      x = x/10;
      int sum = 0;
         
      boolean flag = true;
         
      while (x > 0) {
         
         int num = (int)(x % 10);
         x = x/10;
         if (flag) {
            
            num *= 2;
            flag = false;
            
         } 
         else
            flag = true;
               
         if (num > 9)
            num = (num%10) + (num/10);
               
         sum += num;
         
      }
      
      sum = sum*9;
      sum = sum % 10;
      
      if (sum == check)
         return true;
        
      return false;
   
   }

}