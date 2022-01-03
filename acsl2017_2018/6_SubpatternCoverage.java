
import java.util.*;
import java.io.*;


public class SubpatternCoverage {

	int[][] board;
	
	public SubpatternCoverage(String input) {
	//	int x = 1/0;
		String[] inputs = input.split(" ");
		int rows = Integer.parseInt(inputs[0]);
		int cols = Integer.parseInt(inputs[1]);
		board = new int[rows][cols];
		int index = 2;
		while(index < inputs.length) {
			String puttingin = Integer.toBinaryString(Integer.parseInt(inputs[index],16));
			while(puttingin.length() < 4 * inputs[index].length()) {
				puttingin = "0" + puttingin;
			}
			
			for(int i = 0; i < cols; i++) {
				board[index - 2][i] = Integer.parseInt(puttingin.substring(i,i+1));
			}
			index++;
		}
		
	}
	
	public boolean canTile(int r, int c) {
		int[][] subBoard = new int[r][c];
		if(board.length % r != 0 || board[0].length % c != 0)
			return false;
		
		for(int i = 0; i < r; i++) {
			for(int k = 0; k < c; k++) {
				subBoard[i][k] = board[i][k];
			}
		}
		
		for(int i = 0; i < board.length; i += r) {
			for(int k = 0; k < board[i].length; k += c) {
				int[][] newSub = new int[r][c];
				for(int m = 0; m < r; m++) {
					for(int n = 0; n < c; n++) {
						newSub[m][n] = board[i + m][k + n];
					
					}
				}
				
				if(!areEqual(subBoard,newSub))
					return false;
			}
		}
		
		return true;
	}
	
	public boolean areEqual(int[][] set, int[][] check) {
		if(set.length != check.length)
			return false;
		if(set[0].length != check[0].length)
			return false;
		
		for(int i = 0; i < set.length; i++) {
			for(int k = 0; k < set[i].length; k++)  {
				if(set[i][k] != check[i][k])
					return false;
			}
		}
		return true;
	}
	
	public void test() {
		int[][] meh = { {1,1},{1,0},{0,1},{0,0} };
		int[][] notmeh = { {1,1},{1,0},{0,1},{0,0} };
		System.out.println(areEqual(meh,notmeh));
	}
	
	public void printBoard() {
		for(int[] rows: board) {
			for(int i: rows) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	
	public void solve() {
		int min = board.length * board[0].length;
		int row = board.length;
		int col = board[0].length;
		for(int i = 1; i <= board.length; i++) {
			for(int k = 1; k <= board[0].length; k++) {
				if (canTile(i, k)) {
					if (i * k < min) {
						min = i*k;
						row = i;
						col = k;
					}
				}
			}
		}
		System.out.println(row + " " + col);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(new File("as6-test.txt"));
		while(sc.hasNextLine()) {
			try {
				String input = sc.nextLine();
				SubpatternCoverage s = new SubpatternCoverage(input);
				s.solve();
			} catch (Exception e) {
				System.out.println("ERROR: MISSION FAILED.");
			}
			
		}
	
	}

}
