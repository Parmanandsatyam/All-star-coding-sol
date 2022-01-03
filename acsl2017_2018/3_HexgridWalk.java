import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HexgridWalk {

	private static String[] input = new String[10];

	public static void main(String[] args) throws IOException {
		getInput();
		for (int i = 0; i < 10; i++) {
			logic(input[i]);
		}
	}

	private static void logic(String in) {
		int column = in.split(" ")[0].charAt(0) - 65;
		int height = Integer.parseInt(in.split(" ")[0].substring(1));
		String moves = in.split(" ")[1];

		for (int i = 0; i < moves.length(); i++) {
			if (moves.charAt(i) == '1') {
				height++;
			} else if (moves.charAt(i) == '4') {
				if (height > 1) {
					height--;
				}
			} else if (moves.charAt(i) == '2') {
				if (column < 25) {
					if (column % 2 == 0) {
						column++;
						height++;
					} else if (column % 2 == 1) {
						column++;
					}
				}
			} else if (moves.charAt(i) == '3') {
				if (column < 25 ) {
					if (column % 2 == 0) {
						column++;
					} else if ((column % 2 == 1) && (height > 1)) {
						column++;
						height--;
					}
				}
			} else if (moves.charAt(i) == '6') {
				if (column > 0) {
					if (column % 2 == 0) {
						height++;
						column--;
					} else if (column % 2 == 1) {
						column--;
					}
				}
			} else if (moves.charAt(i) == '5') {
				if (column > 0) {
					if (column % 2 == 0) {
						column--;
					} else if ((column % 2 == 1) && (height > 1)) {
						height--;
						column--;
					}
				}
			}
		}
		System.out.println((char)(column + 65)  + ""  + height);
	}

	private static void getInput() throws IOException {
		File file = new File("/Users/acsltest/Desktop/as3-test.txt");
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

}
