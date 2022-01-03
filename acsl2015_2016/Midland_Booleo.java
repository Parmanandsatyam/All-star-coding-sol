import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Booleo {
	
	static Scanner scan;
	static String[] given = {"A1", "R1", "X1", "A0", "R0", "X0"};
	static boolean done;
	static boolean error;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		Scanner scan1 = new Scanner(new File("booleo.in"));
		
		for(int x = 0; x < 10; x++) {
			
			String line = scan1.nextLine();
			scan = new Scanner(line);
			
			int[] col = new int[scan.nextInt()];
			int n = Integer.parseInt(scan.next(), 16);
			String s = Integer.toBinaryString(n);
			
			int start = s.length()-1;
			for(int y = col.length-1; y >= 0; y--) {
				if(start >= 0) {
					col[y] = s.charAt(start) - '0';
				}else {
					col[y] = 0;
				}
				
				start--;
			}
			
			/*
			for(int y = 0; y < col.length; y++){
				System.out.print(col[y]);
			}
			System.out.println();
			*/
			
			done = false;
			error = false;
			
			buildRow(2, col);
			
			System.out.println();
		}
		
		
	}
	
	private static boolean test(String s, int a, int b) {
		if(s.charAt(0) == 'R') {
			if((a == 1 || b == 1) && s.charAt(1) == '1') return true;
			if((a == 0 && b == 0) && s.charAt(1) == '0') return true;
			return false;
		}else if(s.charAt(0) == 'X') {
			if(((a == 1 && b == 0) || (a == 0 && b == 1)) && s.charAt(1) == '1') return true;
			if(((a == 1 && b == 1) || (a == 0 && b == 0)) && s.charAt(1) == '0') return true;
			return false;
		}else {
			if((a == 1 && b == 1) && s.charAt(1) == '1') return true;
			if((a == 0 || b == 0) && s.charAt(1) == '0') return true;
			return false;
		}
	}

	
	private static void buildRow(int row, int[] col) {
		int[] newcol = new int[col.length - 1];
		
		if(newcol.length == 0) {
			if(!error) System.out.println("TRUE");
			done = true;
			return;
		}
		
		
		for(int x = 0; x < col.length-1; x++) {
			if(done) return;
			
			String s = scan.next();
			
			//System.out.println(s + " " + col[x] + " " + col[x+1]);
			
			if(test(s, col[x], col[x+1])) {
				
			}else {
				System.out.print(row + ", " + (x+1) + ", ");
				boolean none = true;
				
				error = true;
				
				for(int y = 0; y < given.length; y++) {
					if(given[y].charAt(1) == s.charAt(1) && test(given[y], col[x], col[x+1])) {
						none = false;
						
						String t = "";
						if(given[y].charAt(0) == 'R') t = "OR";
						else if(given[y].charAt(0) == 'A') t = "AND";
						else t = "XOR";
						t += given[y].charAt(1);
						
						System.out.println(t);
						
						break;
					}
				}
				
				if(none) {
					System.out.println("NONE");
					done = true;
				}
			}
			
			newcol[x] = s.charAt(1) - '0';
		}
		
		buildRow(row +1, newcol);
		
	}
}
