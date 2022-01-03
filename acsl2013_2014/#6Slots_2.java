import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Slots {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("slots.txt"));
		int num = 1;
		
		for(int i = 0; i < 2; i++){
			String rows = scan.next();
			int bet = scan.nextInt();
			String hex = scan.next();
			String s = "";
			int[] board = new int[25];
			
			for(int x = 0; x < 7; x++){
				if(hex.charAt(x) == '0') s+= "0000";
				else if(hex.charAt(x) == '1') s+= "0001";
				else if(hex.charAt(x) == '2') s+= "0010";
				else if(hex.charAt(x) == '3') s+= "0011";
				else if(hex.charAt(x) == '4') s+= "0100";
				else if(hex.charAt(x) == '5') s+= "0101";
				else if(hex.charAt(x) == '6') s+= "0110";
				else if(hex.charAt(x) == '7') s+= "0111";
				else if(hex.charAt(x) == '8') s+= "1000";
				else if(hex.charAt(x) == '9') s+= "1001";
				else if(hex.charAt(x) == 'A') s+= "1010";
				else if(hex.charAt(x) == 'B') s+= "1011";
				else if(hex.charAt(x) == 'C') s+= "1100";
				else if(hex.charAt(x) == 'D') s+= "1101";
				else if(hex.charAt(x) == 'E') s+= "1110";
				else s+= "1111";
			}
			
			//System.out.println(hex + " " + s);
			
			for(int x = 0; x < 25; x++){
				board[x] = s.charAt(x) - '0';
			}
			
			for(int x = 0; x < 5; x++){
				int score = 0;
				int pattern = scan.nextInt();
				int start = (rows.charAt(pattern - 1) - '0') * 5 - 5;
				//System.out.println(start + " " + bet);
				
				int loc1 = board[start], loc2, loc3, loc4, loc5;
				if(pattern == 1){
					loc2 = board[start+1];
					loc3 = board[start+7];
					loc4 = board[start+13];
					loc5 = board[start+14];
				}else if(pattern == 2){
					loc2 = board[start-4];
					loc3 = board[start-3];
					loc4 = board[start-2];
					loc5 = board[start+4];
				}else if(pattern == 3){
					loc2 = board[start+6];
					loc3 = board[start+12];
					loc4 = board[start+8];
					loc5 = board[start+4];
				}else if(pattern == 4){
					loc2 = board[start-4];
					loc3 = board[start-8];
					loc4 = board[start-2];
					loc5 = board[start+4];
				}else if(pattern == 5){
					loc2 = board[start+6];
					loc3 = board[start+7];
					loc4 = board[start+8];
					loc5 = board[start+4];
				}else{
					loc2 = board[start+1];
					loc3 = board[start-3];
					loc4 = board[start-7];
					loc5 = board[start-6];
				}
				
				//System.out.println(loc1 + " " + loc2 + " " + loc3 + " " + loc4 + " " + loc5);
				
				if(loc3 != 0){
					if(loc1 == 1 && loc2 == 1 && loc4 == 1 && loc5 == 1){
						score = bet*4;
					}else if(loc1 == 1 && loc2 == 1 && loc4 == 1){
						score = bet*3;
					}else if(loc5 == 1 && loc2 == 1 && loc4 == 1){
						score = bet*3;
					}else if(loc1 == 1 && loc2 == 1){
						score = bet*2;
					}else if(loc4 == 1 && loc2 == 1){
						score = bet*2;
					}else if(loc4 == 1 && loc5 == 1){
						score = bet*2;
					}
				}
				
				System.out.println(num + ". " + score);
				num++;
			}
		}
		
		

	}

}
