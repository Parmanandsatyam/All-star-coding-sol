import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class LorenzEncode {
	
	static Scanner sc; static void pl(){System.out.println();} static void pl(Object o){System.out.println(o);} static int pi(String s){return Integer.parseInt(s);} static String nl(){return sc.nextLine();} static void pt(Object o){System.out.print(o);}
	public static void main(String[] args) throws Exception{
		sc = new Scanner(new File("Lorenz1.dat"));
		HashMap<Character,Integer> val = new HashMap<>();
		val.put('A',0b11000);
		val.put('B',0b10011);
		val.put('C',0b01110);
		val.put('D',0b10010);
		val.put('E',0b10000);
		val.put('F',0b10110);
		val.put('G',0b01011);
		val.put('H',0b00101);
		val.put('I',0b01100);
		val.put('J',0b11010);
		val.put('K',0b11110);
		val.put('L',0b01001);
		val.put('M',0b00111);
		val.put('N',0b00110);
		val.put('O',0b00011);
		val.put('P',0b01101);
		val.put('Q',0b11101);
		val.put('R',0b01010);
		val.put('S',0b10100);
		val.put('T',0b00001);
		val.put('U',0b11100);
		val.put('V',0b01111);
		val.put('W',0b11001);
		val.put('X',0b10111);
		val.put('Y',0b10101);
		val.put('Z',0b10001);
		val.put('+',0b11011);
		val.put('/',0b00000);
		val.put('9',0b00100);
		val.put('8',0b11111);
		val.put('4',0b01000);
		val.put('3',0b00010);
		
		char[] k1 = "ABCDEFGHIJKLMN".toCharArray();
		char[] k2 = "AABB".toCharArray();
		
		for(int $=1;$<=10;$++){
			try{
			String[] dat = nl().trim().split("\\s+");
			int a = pi(dat[0])-1;
			int b = pi(dat[1])-1;
			char[] plaintext = dat[2].toCharArray();
			char[] cipher = new char[plaintext.length];
			for(int x=0;x<plaintext.length;x++){
				int lol = val.get(plaintext[x]) ^ val.get(k1[a++]) ^ val.get(k2[b++]);
				a%=k1.length;
				b%=k2.length;
				for(char c:val.keySet()){
					if(val.get(c) == lol){
						cipher[x] = c;
						break;
					}
				}
				 
			}
			pl($+". "+new String(cipher));
			}catch(Exception e){
				pl($+". Exception");				
			}
		}
	}
	
}
