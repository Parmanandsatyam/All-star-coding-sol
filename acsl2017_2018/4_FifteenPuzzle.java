import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FifteenPuzzle {

	private static String[] input = new String[10];

	public static void main(String[] args) throws IOException {
		getInput();
		for (int i = 0; i < 10; i++) {
			logic(input[i]);
		}
	}

	private static void logic (String in) {
		boolean[][] grid = new boolean[4][4];
		for (int i = 0 ; i < 4; i++) {
			for (int x = 0; x < 4; x++) {
				grid[i][x] = true;
			}
		}
		int original = Integer.parseInt(in.split(" ")[0]);
		String moves = in.split(" ")[1];
		
		grid[pos(original)[0]][pos(original)[1]] = false;
		
		for (int i = 0 ; i < moves.length(); i++) {
			here:
			for (int x = 0; x < 4; x++) {
				for (int y = 0 ; y < 4; y++) {
					if (grid[x][y] == false) {
						if(moves.charAt(i) == 'R') {
							grid[x][y+1] = false;
							grid[x][y] = true;
						} else if(moves.charAt(i) == 'L') {
							grid[x][y-1] = false;
							grid[x][y] = true;
						} else if(moves.charAt(i) == 'A') {
							grid[x-1][y] = false;
							grid[x][y] = true;
						} else if(moves.charAt(i) == 'B') {
							grid[x+1][y] = false;
							grid[x][y] = true;
						}
						break here;
					}
				}
			}
		}
		
		here:
		for (int i = 0 ; i < 4; i++) {
			for (int x = 0; x < 4; x++) {
				if(grid[i][x] == false) {
					System.out.println(i*4 + x + 1);
					break here;
				}
			}
		}
	}
	
	private static void getInput() throws IOException {
		File file = new File("/Users/acsltest/Desktop/as4-test.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				input[count] = line;
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static int[] pos (int n) {
		return new int [] {(int)(n-1)/4, (n-1)%4};
	}
}
