import java.io.*;
import java.util.*;

public class Luhn
{
    static Scanner cin;
    public static void main(String[] args)
    {
        setup();
        //System.out.println(above(9));
        //Runs #1-4
        for (int run=0; run<4; run++)
        {
            try {
            String temp = cin.nextLine();
            int[] splitUp = new int[temp.length()-1];
            for (int i=0; i<temp.length()-1; i++)
                splitUp[i] = Integer.parseInt(""+temp.charAt(i));
            int possCheckSum = Integer.parseInt(""+temp.charAt(temp.length()-1));
            if (calcCheckSum(splitUp)==possCheckSum)
                System.out.println("VALID\n");
            else
                System.out.println(calcCheckSum(splitUp)+"\n");
            } catch (Exception e) {
                System.out.println(3);
            }
        }
        
        //Runs 5-6
        for (int run=0; run<2; run++)
        {
            String temp = cin.nextLine();
            try {
            int[] splitUp = new int[temp.length()-1];
            for (int i=0; i<temp.length()-1; i++)
                splitUp[i] = Integer.parseInt(""+temp.charAt(i));
            int possCheckSum = Integer.parseInt(""+temp.charAt(temp.length()-1));
            System.out.println(singleDigitError(splitUp,possCheckSum)+"\n");
            } catch (Exception e)
            {
                System.out.println(temp);
            }
        }
        
        //Runs 7-8
        for (int run=0; run<2; run++)
        {
            String temp = cin.nextLine();
            try {
            int[] splitUp = new int[temp.length()-1];
            for (int i=0; i<temp.length()-1; i++)
                splitUp[i] = Integer.parseInt(""+temp.charAt(i));
            int possCheckSum = Integer.parseInt(""+temp.charAt(temp.length()-1));
            System.out.println(transpositionError(splitUp,possCheckSum)+"\n");
            } catch (Exception e) {
                System.out.println(temp);
            }
        }
        
        //Runs 9-10
        for (int run=0; run<2; run++)
        {
            String temp = cin.nextLine();
            try {
            int[] splitUp = new int[temp.length()-1];
            for (int i=0; i<temp.length()-1; i++)
                splitUp[i] = Integer.parseInt(""+temp.charAt(i));
            int possCheckSum = Integer.parseInt(""+temp.charAt(temp.length()-1));
            System.out.println(twinError(splitUp,possCheckSum)+"\n");
            } catch (Exception e) {
                System.out.println(temp);
            }
        }
    }
    static void setup()
    {
        cin = new Scanner(System.in);
    }
    static String twinError(int[] val, int correctCheckSum)
    {
        int[] tempChange = new int[val.length];
        
        for (int i=0; i<val.length-1; i++)
        {
            //check i & i+1 - below
            set(tempChange,val);
            if (tempChange[i]==tempChange[i+1])
            {
                tempChange[i] = below(tempChange[i]);
                tempChange[i+1]=tempChange[i];
            }
            if (calcCheckSum(tempChange)==correctCheckSum)
                return arrToStr(tempChange)+""+correctCheckSum;
            
            //above
            set(tempChange,val);
            if (tempChange[i]==tempChange[i+1])
            {
                tempChange[i] = above(tempChange[i]);
                tempChange[i+1]=above(tempChange[i+1]);
            }
            //System.out.println(arrToStr(tempChange)+calcCheckSum(tempChange));
            if (calcCheckSum(tempChange)==correctCheckSum)
                return arrToStr(tempChange)+""+correctCheckSum;
        }
        //shouldn't ever reach here
        return arrToStr(val)+""+correctCheckSum;
    }
    static String transpositionError(int[] val, int correctCheckSum)
    {
        int[] tempChange = new int[val.length];
        
        for (int i=0; i<val.length-1; i++)
        {
            //swap i & i+1
            set(tempChange,val);
            swap(tempChange,i,i+1);
            if (calcCheckSum(tempChange)==correctCheckSum)
                return arrToStr(tempChange)+""+correctCheckSum;
        }
        //shouldn't ever reach here
        return arrToStr(val)+""+correctCheckSum;
    }
    static String singleDigitError(int[] val, int correctCheckSum)
    {
        int[] tempChange = new int[val.length];
        
        for (int i=0; i<val.length; i++)
        {
            //above
            set(tempChange,val);
            tempChange[i] = below(tempChange[i]);
            if (calcCheckSum(tempChange)==correctCheckSum)
                return arrToStr(tempChange)+""+correctCheckSum;
            
            //below
            set(tempChange,val);
            tempChange[i] = above(tempChange[i]);
            if (calcCheckSum(tempChange)==correctCheckSum)
                return arrToStr(tempChange)+""+correctCheckSum;
        }
        //just in case - shouldn't actually happen
        return arrToStr(val)+""+correctCheckSum;
    }
    static void set(int[] destination, int[] source)
    {
        //exception??
        if (destination.length!=source.length)
            return ;
        
        for (int i=0; i<destination.length; i++)
            destination[i] = source[i];
    }
    static String arrToStr(int[] a)
    {
        String answer = "";
        for (int i:a)
            answer += ""+i;
        return answer;
    }
    //test
    static int calcCheckSum(int[] val)
    {
        int[] doubles = new int[val.length];
        int[] sumDigits=new int[val.length];
        
        boolean use = true;
        for (int i=val.length-1; i>=0; i--)
        {
            if (use)
                doubles[i] = val[i]+val[i];
            else
                doubles[i] = val[i];
            sumDigits[i] = sumDigit(doubles[i]);
            use = !use;
        }
        
        //print(val);
        //print(doubles);
        //print(sumDigits);
        
        int totalSum = 0;
        for (int i:sumDigits)
            totalSum += i;
        
        //System.out.println(totalSum+" "+(totalSum*9%10));
        
        return ((totalSum*9)%10);
    }
    static void swap(int[] val, int ind1, int ind2)
    {
        int temp = val[ind1];
        val[ind1]= val[ind2];
        val[ind2] = temp;
    }
    //test
    static int sumDigit(int a)
    {
        int counter=0;
        while (a!=0)
        {
            counter += a%10;
            a/=10;
        }
        return counter;
    }
    static int above(int a)
    {
        return (a+1)%10;
    }
    static int below(int a)
    {
        return ((a-1)+10)%10;
    }
    static void print(int[] a)
    {
        for (int i:a)
            System.out.print(i+" ");
        System.out.println();
    }
}