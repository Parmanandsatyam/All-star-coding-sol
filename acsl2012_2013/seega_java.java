package seega;

import java.util.*;

public class _driver_Seega {
	public static void main(String[] args){
		char[][] seega = new char[5][5];
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to ACSL Seega\n");
				
		for(int a=1; a<=5; a++){
			
			for(int b=0; b<5;b++){
				for(int c=0; c<5; c++)
					seega[b][c]='E';
			}
			
			System.out.print("#" + a + " - Please input the number 'X's and 'O's: ");
			int num=input.nextInt();
			input.nextLine();
			
			int lineNum;
			
			for(int b=1; b<=num; b++){
				System.out.print("#" + a + " - Please input 'X' #" + b + ": ");
				lineNum = input.nextInt();
				seega[(lineNum-1)/5][(lineNum-1)%5] = 'X';
				input.nextLine();
			}
			for(int b=1; b<=num; b++){
				System.out.print("#" + a + " - Please input 'O' #" + b + ": ");
				lineNum = input.nextInt();
				seega[(lineNum-1)/5][(lineNum-1)%5] = 'O';
				input.nextLine();
			}
			
			System.out.println("X move: " + play(seega,'X'));
			System.out.println("O move: " + play(seega,'O'));
			
			
		}
	}
	
	public static String play(char[][] seega, char type){
		int pos=0;
		
		for(int r=0; r<5; r++){
			for(int c=0; c<5; c++){
				if(seega[r][c]==type){
					pos = canKill(seega,r,c);
					if(pos!=0)
						return "" + pos;
				}
				
			}
		}
		
		for(int r=0; r<5; r++){
			for(int c=0; c<5; c++){
				if(seega[r][c]==type&&isDanger(seega,r,c)){
					pos = canSafe(seega,r,c);
					if(pos!=0)
						return "" + pos;
				}
				
			}
		}
		
		for(int r=0; r<5; r++){
			for(int c=0; c<5; c++){
				if(seega[r][c]==type){
					pos = canSafe(seega,r,c);
					if(pos!=0)
						return "" + pos;
				}
				
			}
		}
		
		return "LOSE A TURN";
	}
	
	public static int canKill(char[][] seega, int r, int c){
		char p1 = seega[r][c];
		char p2 = flip(p1);
		if(r+1<5 && seega[r+1][c]=='E'){
			if( c-2>=0 && seega[r+1][c-2]==p1 && seega[r+1][c-1]==p2 || c+2<5 && seega[r+1][c+2]==p1 && seega[r+1][c+1]==p2 || r+3<5 && seega[r+2][c]==p2 && seega[r+3][c]==p1)
				return backToLine(r+1,c);
		}
		if(r-1>=0 && seega[r-1][c]=='E'){
			if( c-2>=0 && seega[r-1][c-2]==p1 && seega[r-1][c-1]==p2 || c+2<5 && seega[r-1][c+2]==p1 && seega[r-1][c+1]==p2 || r-3>=0 && seega[r-2][c]==p2 && seega[r-3][c]==p1)
				return backToLine(r-1,c);
		}
		if(c+1<5 && seega[r][c+1]=='E'){
			if( r-2>=0 && seega[r-2][c+1]==p1 && seega[r-1][c+1]==p2 || r+2<5 && seega[r+2][c+1]==p1 && seega[r+1][c+1]==p2 || c+3<5 && seega[r][c+2]==p2 && seega[r][c+3]==p1)
				return backToLine(r,c+1);
		}
		if(c-1>=0 && seega[r][c-1]=='E'){
			if( r-2>=0 && seega[r-2][c-1]==p1 && seega[r-1][c-1]==p2 || r+2<5 && seega[r+2][c-1]==p1 && seega[r+1][c-1]==p2 || c-3>=0 && seega[r][c-2]==p2 && seega[r][c-3]==p1)
				return backToLine(r,c-1);
		}
		return 0;
	}
	
	public static int canSafe(char[][] seega, int r, int c){
		char p1 = seega[r][c];
		char p2 = flip(p1);
		
		//B1
		if(seega[2][2]=='E'&& (backToLine(r,c)==18||backToLine(r,c)==12||backToLine(r,c)==14||backToLine(r,c)==8))
			//if(!(seega[2][1]==p2&&seega[2][3]==p2||seega[1][2]==p2&&seega[3][2]==p2))
				return 13;
		
		//B2
		if(r+1<5 && seega[r+1][c]=='E'){
			if( c-1>=0 && seega[r+1][c-1]==p2 && c+1<5 && seega[r+1][c+1]==p2 )
				return backToLine(r+1,c);
		}
		if(r-1>=0 && seega[r-1][c]=='E'){
			if( c-1>=0 && seega[r-1][c-1]==p2 && c+1<5 && seega[r-1][c+1]==p2 )
				return backToLine(r-1,c);
		}
		if(c+1<5 && seega[r][c+1]=='E'){
			if( r-1>=0 && seega[r-1][c+1]==p2 && r+1<5 && seega[r+1][c+1]==p2 )
				return backToLine(r,c+1);
		}
		if(c-1>=0 && seega[r][c-1]=='E'){
			if( r-1>=0 && seega[r-1][c-1]==p2 && r+1<5 && seega[r+1][c-1]==p2 )
				return backToLine(r,c-1);
		}
		
		//B3&B4
		if(c+1<5 && seega[r][c+1]=='E'){
				return backToLine(r,c+1);
		}
		if(c-1>=0 && seega[r][c-1]=='E'){
				return backToLine(r,c-1);
		}
		if(r+1<5 && seega[r+1][c]=='E'){
				return backToLine(r+1,c);
		}
		if(r-1>=0 && seega[r-1][c]=='E'){
				return backToLine(r-1,c);
		}
		
		return 0;
	}
	
	public static boolean isDanger(char[][] seega, int r, int c){
		char p1 = seega[r][c];
		char p2 = flip(p1);
		

		if(r+1<5 && seega[r+1][c]==p2){
			if( r-1>=0 && (c-1>0 && seega[r-1][c-1]==p2 || c+1<5 && seega[r-1][c+1]==p2) )
				return true;
		}
		if(r-1>=0 && seega[r-1][c]==p2){
			if( r+1<5 && (c-1>0 && seega[r+1][c-1]==p2 || c+1<5 && seega[r+1][c+1]==p2) )
				return true;
		}
		if(c+1<5 && seega[r][c+1]==p2){
			if( c-1>=0 && (r-1>0 && seega[r-1][c-1]==p2 || r+1<5 && seega[r+1][c-1]==p2) )
				return true;
		}
		if(c-1>=0 && seega[r][c-1]==p2){
			if( c+1<5 && (r-1>0 && seega[r-1][c+1]==p2 || r+1<5 && seega[r+1][c+1]==p2) )
				return true;
		}
		
		return false;
	}
	
	public static char flip(char type){
		if(type=='X')
			return 'O';
		if(type=='O')
			return 'X';
		return 'E';

	}
	
	public static int backToLine(int r, int c){
		return 1+c+r*5;
	}
	

	

}
