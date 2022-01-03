import java.util.*;
public class ProblemThree {
   public static void main(String[] a) {
      Scanner sc=new Scanner(System.in);
      for (int p=0; p<10; p++) {
         try {
            String[] s=sc.nextLine().split("[, ]+");
            mult(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
         } 
         catch(Throwable t) {
            t.printStackTrace();
         
            System.out.println("Retry input");
            p--;
         }
      }
   }
   public static void mult(int n, int a) {
      if (a>n) {
         int temp=a;
         a=n;
         n=temp;
      }
      int maxpow=(int)(Math.log(n)/Math.log(2));
      System.out.print((int)Math.pow(2,maxpow)*a);
      n=n-(int)Math.pow(2,maxpow);
      while (n>0) {
         maxpow=(int)(Math.log(n)/Math.log(2));
         System.out.print(", "+(int)Math.pow(2,maxpow)*a);
         n=n-(int)Math.pow(2,maxpow);
      }
      System.out.println();
   }
}