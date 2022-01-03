import java.util.*;

public class Binary {

   public static String convert (double a) {
      
      String output = "";
      
      if (a>=1) {
               
         //int s = (int)(a);
         String str ="";
         str=Integer.toBinaryString((int)a);
         output = output.concat(""+Integer.parseInt(str)+".");
         a=a-(int)(a);
      }
      else {
         output+="0.";
      }
      
      for (int k=0; k<6; k++) {
      
         if (a<Math.pow(2, -(k+1))) {
            output = output.concat("0");
         }
         
         else {
            output=output.concat("1");
            a=a-Math.pow(2, -(k+1));
         }
      }
      return output;
   }

   public static void main (String [] args) {
   
      Scanner keyboard = new Scanner (System.in);
   
      for (int j=0; j<5; j++) {
      try {
         String[] line=keyboard.nextLine().split("[, ]+");
         double a = Double.parseDouble(line[0]);
         double b = Double.parseDouble(line[1]);
         double fraction = a/b;
         String radix = "" + convert(fraction);
         //System.out.println(radix);  
         
         double decimal = 0;
         int power = 0;
         int frontI = radix.indexOf(".")-1;
         for (int k=frontI; k>=0; k--) {
            decimal+=Double.parseDouble(radix.substring(k,k+1))*Math.pow(2.0,power) ;
            power++;
         }
         int endI = radix.indexOf(".")+1;
         power = -1;
         for(int k = endI; k<radix.length(); k++)
         {
            decimal+=Double.parseDouble(radix.substring(k,k+1))*Math.pow(2.0,power) ;
            power--;
         }
         String decimalString = ""+decimal;
         String dec = decimalString.substring(decimalString.indexOf(".")+1);
         if(dec.length()<8)
         {
            for(int k = dec.length(); k<8; k++)
               decimalString+="0";
         }
         decimalString = decimalString.substring(0, decimalString.indexOf(".")+7);
         System.out.println(radix+", "+decimalString); 
         } catch (Throwable t) {
         System.out.println("Reenter input");
         j--;
         }
      }
      
   }
}