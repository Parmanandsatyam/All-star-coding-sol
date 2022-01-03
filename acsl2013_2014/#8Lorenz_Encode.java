import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Lorenz_Encode {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("cipher.txt"));
		for(int x = 0; x < 10; x++){
			System.out.print(x + 1 + ". ");
			encode(scan.nextInt(), scan.nextInt(), scan.next());
		}
		

	}
	
	private static void encode(int k1, int k2, String word){
		String encoded = "";
		for(int x = 0; x < word.length(); x++){
			encoded += returnChar(xor(xor(returnBinary(word.charAt(x)), returnBinary(key1(k1 + x))), returnBinary(key2(k2 + x))));
		}
		System.out.println(encoded);
	}
	
	private static String xor(String s1, String s2){
		String s = "";
		for(int x = 0; x < 5; x++){
			if(s1.charAt(x) != s2.charAt(x)) s+= "1";
			else s+= "0";
		}
		return s;
	}
	
	private static char key1(int n){
		while(n > 14) n -= 14;
		if(n == 1) return 'A';
		if(n == 2) return 'B';
		if(n == 3) return 'C';
		if(n == 4) return 'D';
		if(n == 5) return 'E';
		if(n == 6) return 'F';
		if(n == 7) return 'G';
		if(n == 8) return 'H';
		if(n == 9) return 'I';
		if(n == 10) return 'J';
		if(n == 11) return 'K';
		if(n == 12) return 'L';
		if(n == 13) return 'M';
		return 'N';
	}
	
	private static char key2(int n){
		while(n > 4) n -= 4;
		if(n == 1) return 'A';
		if(n == 2) return 'A';
		if(n == 3) return 'B';
		return 'B';
	}
	private static char returnChar(String b){
		if(b.equals("11000")) return 'A';
		if(b.equals("10011")) return 'B';
		if(b.equals("01110")) return 'C';
		if(b.equals("10010")) return 'D';
		if(b.equals("10000")) return 'E';
		if(b.equals("10110")) return 'F';
		if(b.equals("01011")) return 'G';
		if(b.equals("00101")) return 'H';
		if(b.equals("01100")) return 'I';
		if(b.equals("11010")) return 'J';
		if(b.equals("11110")) return 'K';
		if(b.equals("01001")) return 'L';
		if(b.equals("00111")) return 'M';
		if(b.equals("00110")) return 'N';
		if(b.equals("00011")) return 'O';
		if(b.equals("01101")) return 'P';
		if(b.equals("11101")) return 'Q';
		if(b.equals("01010")) return 'R';
		if(b.equals("10100")) return 'S';
		if(b.equals("00001")) return 'T';
		if(b.equals("11100")) return 'U';
		if(b.equals("01111")) return 'V';
		if(b.equals("11001")) return 'W';
		if(b.equals("10111")) return 'X';
		if(b.equals("10101")) return 'Y';
		if(b.equals("10001")) return 'Z';
		if(b.equals("11011")) return '+';
		if(b.equals("00000")) return '/';
		if(b.equals("00100")) return '9';
		if(b.equals("11111")) return '8';
		if(b.equals("01000")) return '4';
		return '3';
	}
	
	
	private static String returnBinary(char c){
		if(c == 'A') return "11000";
		if(c == 'B') return "10011";
		if(c == 'C') return "01110";
		if(c == 'D') return "10010";
		if(c == 'E') return "10000";
		if(c == 'F') return "10110";
		if(c == 'G') return "01011";
		if(c == 'H') return "00101";
		if(c == 'I') return "01100";
		if(c == 'J') return "11010";
		if(c == 'K') return "11110";
		if(c == 'L') return "01001";
		if(c == 'M') return "00111";
		if(c == 'N') return "00110";
		if(c == 'O') return "00011";
		if(c == 'P') return "01101";
		if(c == 'Q') return "11101";
		if(c == 'R') return "01010";
		if(c == 'S') return "10100";
		if(c == 'T') return "00001";
		if(c == 'U') return "11100";
		if(c == 'V') return "01111";
		if(c == 'W') return "11001";
		if(c == 'X') return "10111";
		if(c == 'Y') return "10101";
		if(c == 'Z') return "10001";
		if(c == '+') return "11011";
		if(c == '/') return "00000";
		if(c == '9') return "00100";
		if(c == '8') return "11111";
		if(c == '4') return "01000";
		return "00010";
	}
	

}
