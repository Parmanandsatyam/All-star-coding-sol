import java.util.*;
public class ProblemFour {
   public static void main(String[] a) {
   //ascii();
      Scanner sc=new Scanner(System.in);
      for (int p=0; p<10; p++) {
         try {
            String line=sc.nextLine();
            while (line.length()%3!=0)line=line+"0";
            String bin="";
            //for (char c:s) bin=bin+((Integer)(int)c).toBinaryString();
            for (char c:line.toCharArray()) {
               String in=Integer.toBinaryString(c);
               while (in.length()<8) in='0'+in;
               bin=bin+in;
            }
            //System.out.println(bin);
            //while (bin.length()%6!=0)bin=bin+"0";
            for (int i=0; i<bin.length(); i+=6) {
               convert(bin.substring(i,i+6));
            }
            System.out.println();
         } 
         catch(Throwable t) {
            t.printStackTrace();
         
            System.out.println("Retry input");
            p--;
         }
      }
   }
   static void convert(String s) {
      int num=0;
      if (s.charAt(0)=='1') num+=32;
      if (s.charAt(1)=='1') num+=16;
      if (s.charAt(2)=='1') num+=8;
      if (s.charAt(3)=='1') num+=4;
      if (s.charAt(4)=='1') num+=2;
      if (s.charAt(5)=='1') num+=1;
      num+=32;
      if (num==32) System.out.print("~");
      else System.out.print((char)num);
   }
   static void ascii() {
      for (int i=32; i<127; i++) {
         System.out.println((char)i+": "+i);
      }
   }
}