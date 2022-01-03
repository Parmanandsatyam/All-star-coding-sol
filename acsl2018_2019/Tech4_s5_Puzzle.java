package puzzle;

import java.util.*;
import java.io.*;

public class Puzzle {
	static int size;
	static Piece[] pieces;
	static boolean[][] board;
	static int index;
	static int totalAngle;

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("E:\\as9-test.txt"));
		String input = "";
		while (scan.hasNextLine()) {
			input += scan.nextLine();
			input += " ";
		}
		StringTokenizer st = new StringTokenizer(input);
		for (int counter = 0; counter < 10; counter++) {
			try {
				size = Integer.parseInt(st.nextToken());
				board = new boolean[size][size];
				int pieceNum = Integer.parseInt(st.nextToken());
				pieces = new Piece[pieceNum];
				for (int i = 0; i < pieceNum; i++) {
					String hex = st.nextToken();
					String[] bin = new String[3];
					for (int j = 0; j < 3; j++) {
						bin[j] = Integer.toBinaryString(Integer.parseInt(Character.toString(hex.charAt(j)), 16));
					}
					pieces[i] = new Piece(bin);
				}
				for (int i = 0; i < size; i++) {
					String binString = Integer.toBinaryString(Integer.parseInt(st.nextToken(), 16));
					for (int j = 0; j < binString.length(); j++) {
						if (binString.charAt(binString.length() - 1 - j) == '1') {
							board[i][size - 1 - j] = true;
						}
					}
				}
				index = 0;
				totalAngle = 0;
				int failCount = 0;
				outer: while (true) {
					boolean works = fillBoard();
					if (works == true) {
						failCount = 0;
					} else {
						failCount++;
					}
					if (failCount > pieceNum) {
						break outer;
					}
					index++;
					if (index == pieceNum) {
						index = 0;
					}
				}
				System.out.println(totalAngle);
			} catch (Throwable T) {
				System.out.println("Input failed");
			}
		}
		scan.close();
	}

	static boolean fillBoard() {
		Piece current = pieces[index];
		for (int i = size - 1; i >= 0; i--) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < 4; k++) {
					boolean works = testPiece(current.dx[k], current.dy[k], i, j);
					if (works == true) {
						totalAngle += k * 90;
						return true;
					}
				}
			}
		}
		return false;
	}

	static boolean testPiece(int[] dx, int[] dy, int x, int y) {
		for (int i = 0; i < dx.length; i++) {
			int tx = dx[i] + x;
			int ty = dy[i] + y;
			if (tx >= size || tx < 0 || ty >= size || ty < 0 || board[tx][ty] == true) {
				return false;
			}
		}
		for (int i = 0; i < dx.length; i++) {
			int tx = dx[i] + x;
			int ty = dy[i] + y;
			board[tx][ty] = true;
		}
		return true;
	}
}

class Piece {
	int[][] dx;
	int[][] dy;
	boolean[][] current = new boolean[3][4];
	int index = 0;
	int length = 0;

	public Piece(String[] bin) {
		for (int i = 0; i < 3; i++) {
			String binString = bin[i];
			for (int j = 0; j < binString.length(); j++) {
				if (binString.charAt(binString.length() - 1 - j) == '1') {
					current[i][4 - 1 - j] = true;
					length++;
				}
			}
		}
		dx = new int[4][length];
		dy = new int[4][length];
		calculateRotations();
	}

	void calculateRotations() {
		findBottomLeft();
		rotate();
		findBottomLeft();
		rotate();
		findBottomLeft();
		rotate();
		findBottomLeft();
	}

	void rotate() {
		boolean[][] newCurrent = new boolean[current[0].length][current.length];
		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current[0].length; j++) {
				if (current[i][j] == true) {
					newCurrent[j][current.length - 1 - i] = true;
				}
			}
		}
		current = newCurrent;
	}

	void findBottomLeft() {
		int x = -1;
		int y = -1;
		int index2 = 0;
		for (int i = current.length - 1; i >= 0; i--) {
			for (int j = 0; j < current[0].length; j++) {
				if (current[i][j] == true) {
					if (x == -1) {
						x = i;
						y = j;
					}
					dx[index][index2] = i - x;
					dy[index][index2] = j - y;
					index2++;
				}
			}
		}
		index++;
	}
}