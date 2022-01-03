import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

//Alabama School of Fine Arts
//ACSL All-Star Intermediate 3

public class Main {
	public static String[] cells;
	public static Stack<String> inStack;
	public static Stack<String> outStack;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		cells = new String[3];

		String[] input = new String[10];
		String[] output = new String[10];

		for (int i = 0; i < 3; i++) {
			System.out.print(i + 1 + ". ");
			cells[i] = in.nextLine();
		}

		for (int i = 0; i < 10; i++) {
			System.out.print(i + 4 + ". ");
			input[i] = in.nextLine();

			output[i] = process(input[i]);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(i + 1 + ". " + output[i]);
		}
	}

	public static String process(String input) {
		String cell = "";
		inStack = new Stack<String>();
		outStack = new Stack<String>();
		
		String[] splitIn = input.split(" ");
		
		for (int i=0; i<splitIn.length; i++)
			inStack.push(splitIn[i]);
		
		while (!inStack.isEmpty()) {
			processCmd(inStack.pop());
		}
		
		return outStack.pop();
	}
	
	public static void processCmd(String cmd) {
		int num = 0;
		String cell1 = "", cell2 = "";
		
		if (cmd.equals("DIV")) {
			cell1 = outStack.pop();
			
			String first = cell1.substring(0, 4);
			String second = cell1.substring(4);
			
			outStack.push(first+first + " and " + second+second);
			return;
		}
		
		if (cmd.startsWith("ADD")) {
			cell1 = outStack.pop();
			
			num = Integer.parseInt(cmd.substring(3, 4));
			
			outStack.push(cell1.substring(0, num) + cell1.substring(0, cell1.length()-num));
			return;
		}
		
		if (cmd.startsWith("SUB")) {
			cell1 = outStack.pop();
			
			num = Integer.parseInt(cmd.substring(3, 4));
			
			cell1 = cell1.substring(num);
			
			String end = cell1.substring(cell1.length()-num);
			
			end = alphabetize(end);
			
			outStack.push(cell1 + end);
			return;
		}
		
		if (cmd.equals("UNI")) {
			cell1 = outStack.pop();
			cell2 = outStack.pop();
			
			outStack.push(alphabetize(cell1.substring(4)) + alphabetize(cell2.substring(0,  4)));
			return;
		}
		
		if (cmd.equals("INT")) {
			cell1 = outStack.pop();
			cell2 = outStack.pop();
			
			String cell1Mid = alphabetize(cell1.substring(0, 2) + cell1.substring(6, 8));
			String cell2Mid = alphabetize(cell2.substring(0, 2) + cell2.substring(6, 8));
			
			outStack.push(cell1Mid + cell2Mid);
			return;
		}
		
		if (cmd.equals("ALI")) {
			cell1 = outStack.pop();
			String push = "";
			
			cell1 = alphabetize(cell1);
			
			for (int i=7; i>=0; i--)
				push += cell1.charAt(i);
			
			outStack.push(push);
			return;
		}
		
		if (cmd.equals("X")) {
			outStack.push(cells[0]);
			return;
		}
		if (cmd.equals("Y")) {
			outStack.push(cells[1]);
			return; 
		}
		if (cmd.equals("Z")) {
			outStack.push(cells[2]);
			return;
		}
	}
	
	public static String alphabetize(String str) {
		char[] chars = str.toCharArray();
		
		Arrays.sort(chars);
		
		String ret = "";
		
		for (Character c : chars)
			ret += c;
		
		return ret;
	}

}
