import java.util.Arrays;
import java.util.Scanner;

public class Numbers {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] inputs = scan.nextLine().trim().split(", ");
		char[] num = inputs[0].toCharArray();
		if(num[0] != '-') {
			int secondInput = Integer.parseInt(inputs[1]);
			Arrays.sort(num);
			String number = "";
			for(char x:num) {
				number += x;
			}
			long small = Long.parseLong(number);
			System.out.println(small);
			
			char[] newNum = new char[num.length];
			for(int i = 0; i < num.length; i++) {
				newNum[i] = num[num.length-1-i];
			}
			number = "";
			for(char x:newNum) {
				number += x;
			}
			long large = Long.parseLong(number);
			System.out.println(large);
			
			int numFound = 0;		
			long answer = 0;
			for(long iter = large; iter >= small; iter -= 1) {
				char[] temp = Long.toString(iter).toCharArray();
				Arrays.sort(temp);
				number = "";
				for(char x:temp) {
					number += x;
				}
				long tempNum = Long.parseLong(number);
				if(tempNum == small) {
					numFound += 1;
					if(numFound == 50) {
						answer = iter;
						break;
					}
				}
			}
			System.out.println(answer);
			
			numFound = 0;		
			answer = 0;
			for(long iter = small; iter <= large; iter += 1) {
				char[] temp = Long.toString(iter).toCharArray();
				Arrays.sort(temp);
				number = "";
				for(char x:temp) {
					number += x;
				}
				long tempNum = Long.parseLong(number);
				if(tempNum == small) {
					numFound += 1;
					if(numFound == secondInput) {
						answer = iter;
						break;
					}
				}
			}
			System.out.println(answer);
			
			double average = (large+small)/2.0;
			if(large+small % 2 == 1) {
				for(double distance = 0.5; ;distance += 1.0) {
					char[] temp = Long.toString((long) (distance + average)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					long tempNum = Long.parseLong(number);
					if(tempNum == small) {
						answer = (long) (distance + average);
						break;
					}
					
					temp = Long.toString((long) (average-distance)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					tempNum = Long.parseLong(number);
					if(tempNum == small) {
						answer = (long) (average-distance);
						break;
					}
				}
			}
			else {
				for(double distance = 0; ;distance += 1.0) {
					char[] temp = Long.toString((long) (distance + average)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					long tempNum = Long.parseLong(number);
					if(tempNum == small) {
						answer = (long) (distance + average);
						break;
					}
					temp = Long.toString((long) (average-distance)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					tempNum = Long.parseLong(number);
					if(tempNum == small) {
						answer = (long) (average-distance);
						break;
					}
				}
			}
			System.out.println(answer);
		}
		else {
			int posNum = -1*Integer.parseInt(inputs[0]);
			num = Integer.toString(posNum).toCharArray();
			int secondInput = Integer.parseInt(inputs[1]);
			Arrays.sort(num);
			String number = "";
			for(char x:num) {
				number += x;
			}
			long large = Long.parseLong(number);
			
			char[] newNum = new char[num.length];
			for(int i = 0; i < num.length; i++) {
				newNum[i] = num[num.length-1-i];
			}
			number = "";
			for(char x:newNum) {
				number += x;
			}
			long small = Long.parseLong(number);
			System.out.println(-1*small);
			System.out.println(-1*large);
			
			
			int numFound = 0;		
			long answer = 0;
			for(long iter = large; iter <= small; iter += 1) {
				char[] temp = Long.toString(iter).toCharArray();
				Arrays.sort(temp);
				number = "";
				for(char x:temp) {
					number += x;
				}
				long tempNum = Long.parseLong(number);
				if(tempNum == large) {
					numFound += 1;
					if(numFound == 50) {
						answer = -1*iter;
						break;
					}
				}
			}
			System.out.println(answer);
			
			
			numFound = 0;		
			answer = 0;
			for(long iter = small; iter >= large; iter -= 1) {
				char[] temp = Long.toString(iter).toCharArray();
				Arrays.sort(temp);
				number = "";
				for(char x:temp) {
					number += x;
				}
				long tempNum = Long.parseLong(number);
				if(tempNum == large) {
					numFound += 1;
					if(numFound == secondInput) {
						answer = -1*iter;
						break;
					}
				}
			}
			System.out.println(answer);
			
			double average = (large+small)/2.0;
			if(large+small % 2 == 1) {
				for(double distance = 0.5; ;distance += 1.0) {
					char[] temp = Long.toString((long) (distance + average)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					long tempNum = Long.parseLong(number);
					if(tempNum == large) {
						answer = -1 * (long) (distance + average);
						break;
					}
					
					temp = Long.toString((long) (average-distance)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					tempNum = Long.parseLong(number);
					if(tempNum == large) {
						answer = -1 * (long) (average-distance);
						break;
					}
				}
			}
			else {
				for(double distance = 0; ;distance += 1.0) {
					char[] temp = Long.toString((long) (distance + average)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					long tempNum = Long.parseLong(number);
					if(tempNum == large) {
						answer = -1 * (long) (distance + average);
						break;
					}
					temp = Long.toString((long) (average-distance)).toCharArray();
					Arrays.sort(temp);
					number = "";
					for(char x:temp) {
						number += x;
					}
					tempNum = Long.parseLong(number);
					if(tempNum == large) {
						answer = -1 * (long) (average-distance);
						break;
					}
				}
			}
			System.out.println(answer);
		}
		System.exit(0);
	}
}
/*
-123456789, 50
-987654321
-123456789
-123475698
-987635412
-561234789
*/
