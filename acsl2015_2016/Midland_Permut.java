import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Permut {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("in2.in"));
		while(scan.hasNext()){
			String s = scan.next();
			//long l = uniquePerm(s);
			//if(l < 0){
				//l *= -1;
			//}
			System.out.println(uniquePerm(s));
		}
		scan.close();
	}
	
	public static BigInteger uniquePerm(String s){
		if(s.length() == 1){
			return new BigInteger("1");
		}
		int[] x = new int[26];
		for(int a = 0; a < s.length(); a++){
			char c = s.charAt(a);
			x[c-'a']++;
		}
		BigInteger total = new BigInteger("0");
		for(int a = 0; a < 26; a++){
			if(a == s.charAt(0)-'a'){
				break;
			}else if(x[a] > 0){
				x[a]--;
				String x2 = "";
				for(int b = 0; b < 26; b++){
					for(int c = 0; c < x[b]; c++){
						x2 += "" + (char)(b + 'a');
					}
				}
				total = total.add(formula2(x2));
				x[a]++;
			}
		}
		return total.add(uniquePerm(s.substring(1)));		
	}
	
	public static long formula(String s){
		int[] x = new int[26];
		int n = 0;
		for(int a = 0; a < s.length(); a++){
			char c = s.charAt(a);
			x[c-'a']++;
			n++;
		}
		ArrayList<Integer> numerator = new ArrayList<Integer>();
		for(int a = 1; a <= n; a++){
			numerator.add(new Integer(a));
		}
		ArrayList<Integer> denom = new ArrayList<Integer>();
		for(int a = 0; a < 26; a++){
			for(int b = 1; b <= x[a]; b++){
				denom.add(new Integer(b));
			}
		}
		for(int a = 0; a < numerator.size(); a++){
			//System.out.println(numerator.get(a));
			int i = denom.indexOf(numerator.get(a));
			if(i != -1){
				numerator.remove(a);
				denom.remove(i);
				a--;
			}
		}
		long total = 1;
		for(int a = 0; a < numerator.size(); a++){
			total *= numerator.get(a).intValue();
		}
		for(int a = 0; a < denom.size(); a++){
			total /= denom.get(a).intValue();
		}
		return total;
	}
	
	public static BigInteger formula2(String s){
		int[] x = new int[26];
		int n = 0;
		for(int a = 0; a < s.length(); a++){
			char c = s.charAt(a);
			x[c-'a']++;
			n++;
		}
		ArrayList<Integer> numerator = new ArrayList<Integer>();
		for(int a = 1; a <= n; a++){
			numerator.add(new Integer(a));
		}
		ArrayList<Integer> denom = new ArrayList<Integer>();
		for(int a = 0; a < 26; a++){
			for(int b = 1; b <= x[a]; b++){
				denom.add(new Integer(b));
			}
		}
		for(int a = 0; a < numerator.size(); a++){
			//System.out.println(numerator.get(a));
			int i = denom.indexOf(numerator.get(a));
			if(i != -1){
				numerator.remove(a);
				denom.remove(i);
				a--;
			}
		}
		BigInteger total = new BigInteger("1");
		for(int a = 0; a < numerator.size(); a++){
			total = total.multiply(new BigInteger("" + numerator.get(a).intValue()));
		}
		for(int a = 0; a < denom.size(); a++){
			total = total.divide(new BigInteger("" + denom.get(a).intValue()));
		}
		return total;
	}

}
