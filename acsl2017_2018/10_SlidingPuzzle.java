import java.util.*;
import java.io.*;


public class SlidingPuzzle {

	int[][] grid;
	int[] notStrings;
	
	public SlidingPuzzle(String input) {
		//int x = 1/0;
		String[] inputs = input.split(" ");
		notStrings = new int[inputs.length];
		for(int i = 0; i < notStrings.length; i++) {
			notStrings[i] = Integer.parseInt(inputs[i]);
		}
		grid = new int[notStrings[0]][notStrings[0]];
		int index = 1;
		boolean found = false;
		for(int i = 0; i < grid.length; i++) {
			for(int k = 0; k < grid[i].length; k++) {
				if(index == notStrings[1] && !found) {
					grid[i][k] = 0;
					found = true;
					index--;
				} else {
					grid[i][k] = index;
				}
				
				index++;
			}
		}
		//printBoard();
		
		int row = (notStrings[1] - 1) / grid.length;
		int col = (notStrings[1] - 1) % grid.length;
		
	
	}
	
	
	// x,y
	public int[] getPos() {
		int[] pos = {0,0};
		for(int i = 0; i < grid.length; i++) {
			for(int k = 0; k < grid[i].length; k++) {
				if(grid[i][k] == 0) {
					pos[0] = i;
					pos[1] = k;	
				}
			}
		}
		return pos;
	}
	
	public int[] getPos(int target) {
		int[] pos = {0,0};
		for(int i = 0; i < grid.length; i++) {
			for(int k = 0; k < grid[i].length; k++) {
				if(grid[i][k] == target) {
					pos[0] = i;
					pos[1] = k;	
				}
			}
		}
		return pos;
	}
	
	public void solve() {
		int index = 2;
		while(index < notStrings.length) {
			int[] zeroPos = getPos();
			int[] targetPos = getPos(notStrings[index]);
			ArrayList<Integer> moved = new ArrayList<Integer>();
			// target is positionally higher than zero
			if(targetPos[0] < zeroPos[0]) {
				
				for(int i = targetPos[0]; i <= zeroPos[0]; i++) {
					moved.add(grid[i][targetPos[1]]);
				}
				moved.remove(moved.size() - 1);
				moved.add(0,0);
				
				int movedIndex = 0;
				for(int i = targetPos[0]; i <= zeroPos[0]; i++) {
					grid[i][targetPos[1]] = moved.get(movedIndex);
					movedIndex++;
				}
			} else if(targetPos[0] > zeroPos[0]) {
				for(int i = zeroPos[0]; i <= targetPos[0]; i++) {
					moved.add(grid[i][zeroPos[1]]);
				}
				moved.remove(0);
				moved.add(0);
				int movedIndex = 0;
				for(int i = zeroPos[0]; i <= targetPos[0]; i++) {
					grid[i][zeroPos[1]] = moved.get(movedIndex);
					movedIndex++;
				}
			} else if(targetPos[1] < zeroPos[1]) {
				for(int i =targetPos[1]; i <= zeroPos[1]; i++) {
					moved.add(grid[targetPos[0]][i]);
				}
				moved.remove(moved.size() - 1);
				moved.add(0,0);
				int movedIndex = 0;
				for(int i = targetPos[1]; i <= zeroPos[1]; i++) {
					grid[targetPos[0]][i] = moved.get(movedIndex);
					movedIndex++;
				}
			} else {
				for(int i = zeroPos[1]; i <= targetPos[1]; i++) {
					moved.add(grid[targetPos[0]][i]);
				}
				moved.remove(0);
				moved.add(0);
				int movedIndex = 0; 
				for(int i = zeroPos[1]; i <= targetPos[1]; i++) {
					grid[targetPos[0]][i] = moved.get(movedIndex);
					movedIndex++;
				}
			}
			
			//printBoard();
			
			index++;
		}
		System.out.println(getPos()[0] * notStrings[0] + getPos()[1] + 1);
	}
	
	
	public void printBoard() {
		for(int[] rows: grid) {
			for(int i: rows) {
				System.out.print(i + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
			
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("as10-test.txt"));
		while(sc.hasNextLine()) {
			try {
				String input = sc.nextLine();
				SlidingPuzzle p = new SlidingPuzzle(input);
				p.solve();
			} catch(Exception e) {
				System.out.println("ERROR: MISSION FAILED");
			}
		}	
		
		//p.printBoard();
	}
}
