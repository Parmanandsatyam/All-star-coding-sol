import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ACSL3x2 {
	
	public static final int empty = 0;
	public static final int white = 1;
	public static final int black = 2;
	public static ArrayList<ArrayList<Integer>> solutions;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("in.in"));
		int t = 1;
		while(scan.hasNext()){
			solutions = null;
			int[][] board = input(scan);
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					if(board[x][y] == black){
						board[x][y] = empty;
						move(new Point(x, y), board, new ArrayList<Point>());
						board[x][y] = black;
					}
				}
			}
			System.out.print(t + ": ");
			removeDuplicates();
			if(solutions == null || solutions.size() == 0){
				System.out.println("NONE");
			}else{
				for(int r = 0; r < solutions.size(); r++){
					for(int c = 0; c < solutions.get(r).size(); c++){
						if(c != solutions.get(r).size()-1){
							System.out.print(solutions.get(r).get(c) + ", ");
						}else{
							System.out.println(solutions.get(r).get(c));
						}
					}
				}
			}
			t++;
		}
		scan.close();
	}
	
	public static int[][] input(Scanner scan){
		int w = scan.nextInt();
		int[][] board = new int[8][8];
		for(int a = 0; a < w; a++){
			int n = scan.nextInt();
			Point p = toCoor(n);
			board[p.x][p.y] = white;
		}
		int b = scan.nextInt();
		for(int a = 0; a < b; a++){
			int n = scan.nextInt();
			Point p = toCoor(n);
			board[p.x][p.y] = black;
		}
		return board;
	}
	
	public static void removeDuplicates(){
		for(int a = 0; a < solutions.size(); a++){
			for(int b = a+1; b < solutions.size(); b++){
				if(equals(solutions.get(a), solutions.get(b))){
					solutions.remove(b);
					b--;
				}
			}
		}
		if(solutions.get(0).size() == 0){
			solutions = null;
		}
	}
	
	public static boolean equals(ArrayList<Integer> a, ArrayList<Integer> b){
		Collections.sort(a);
		Collections.sort(b);
		for(int c = 0; c < a.size(); c++){
			if(!a.get(c).equals(b.get(c))){
				return false;
			}
		}
		return true;
	}
	
	//remove piece that is being moved from board before calling move.
	public static void move(Point p, int[][] board, ArrayList<Point> captured){
		ArrayList<Point> moves = determineLegalMoves(p, board);
		removeFailedToCapture(moves, board);
		if(moves.size() == 0){
			if(solutions == null || solutions.size() == 0 || captured.size() > solutions.get(0).size()){
				solutions = new ArrayList<ArrayList<Integer>>();
				solutions.add(copy(captured));
			}else if(solutions.get(0).size() == captured.size()){
				solutions.add(copy(captured));
			}
			return;
		}
		for(int a = 0; a < moves.size(); a++){
			int[][] map = copy(board);
			ArrayList<Point> capture = Capture(moves.get(a), map);
			for(int b = 0; b < capture.size(); b++){
				map[capture.get(b).x][capture.get(b).y] = empty;
			}
			ArrayList<Point> param = new ArrayList<Point>();
			for(int b = 0; b < capture.size(); b++){
				param.add(capture.get(b));
			}
			for(int b = 0; b < captured.size(); b++){
				param.add(captured.get(b));
			}
			move(moves.get(a), map, param);
		}
	}
	
	public static void removeFailedToCapture(ArrayList<Point> moves, int[][] board){
		for(int a = 0; a < moves.size(); a++){
			if(Capture(moves.get(a), board).size() == 0){
				moves.remove(a);
				a--;
			}
		}
	}
	
	public static ArrayList<Point> Capture(Point p, int[][] board){
		ArrayList<Point> r = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		boolean works = true;
		if(p.x > 0){
			for(int a = p.x-1; a >= 0; a--){
				if(board[a][p.y] == white){
					temp.add(new Point(a, p.y));
				}else if(board[a][p.y] == black){
					break;
				}else if(board[a][p.y] == empty){
					works = false;
					break;
				}
			}
		}
		if(works){
			for(int a = 0; a < temp.size(); a++){
				r.add(temp.get(a));
			}
		}
		temp = new ArrayList<Point>();
		works = true;
		if(p.x < 7){
			for(int a = p.x+1; a <= 7; a++){
				if(board[a][p.y] == white){
					temp.add(new Point(a, p.y));
				}else if(board[a][p.y] == black){
					break;
				}else if(board[a][p.y] == empty){
					works = false;
					break;
				}
			}
		}

		if(works){
			for(int a = 0; a < temp.size(); a++){
				r.add(temp.get(a));
			}
		}
		temp = new ArrayList<Point>();
		works = true;
		if(p.y > 0){
			for(int a = p.y-1; a >= 0; a--){
				if(board[p.x][a] == white){
					temp.add(new Point(p.x, a));
				}else if(board[p.x][a] == black){
					break;
				}else if(board[p.x][a] == empty){
					works = false;
					break;
				}
			}
		}
		if(works){
			for(int a = 0; a < temp.size(); a++){
				r.add(temp.get(a));
			}
		}
		temp = new ArrayList<Point>();
		works = true;
		if(p.y < 7){
			for(int a = p.y+1; a <= 7; a++){
				if(board[p.x][a] == white){
					temp.add(new Point(p.x, a));
				}else if(board[p.x][a] == black){
					break;
				}else if(board[p.x][a] == empty){
					works = false;
					break;
				}
			}
		}
		if(works){
			for(int a = 0; a < temp.size(); a++){
				r.add(temp.get(a));
			}
		}
		return r;
	}
	
	public static ArrayList<Integer> copy(ArrayList<Point> p){
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int a = 0; a < p.size(); a++){
			int n = toIndex(p.get(a));
			values.add(new Integer(n));
		}
		return values;
	}
	
	public static int[][] copy(int[][] b){
		int[][] r = new int[b.length][b[0].length];
		for(int x = 0; x < b.length; x++){
			for(int y = 0; y < b[0].length; y++){
				r[x][y] = b[x][y];
			}
		}
		return r;
	}
	
	public static ArrayList<Point> determineLegalMoves(Point p, int[][] board){
		ArrayList<Point> r = legalMoves(p);
		verifyLegalMoves(r, board, p);
		for(int a = 0; a < r.size(); a++){
			if(board[r.get(a).x][r.get(a).y] != empty){
				r.remove(a);
				a--;
			}
		}
		return r;
	}
	
	public static void verifyLegalMoves(ArrayList<Point> r, int[][] board, Point p){
		//x
		if(p.x-3 >= 0){
			for(int a = p.x-1; a >= p.x-3; a--){
				if(board[a][p.y] == white){
					remove(r, new Point(p.x-3, p.y-2));
					remove(r, new Point(p.x-3, p.y+2));
					break;
				}
			}
			int a = p.x-3;
			if(p.y-2 >= 0){
				for(int b = p.y-1; b >= p.y-2; b--){
					if(board[a][b] == white){
						remove(r, new Point(a, p.y-2));
						break;
					}
				}
			}
			if(p.y+2 <= 7){
				for(int b = p.y+1; b <= p.y+2; b++){
					if(board[a][b] == white){
						remove(r, new Point(a, p.y+2));
						break;
					}
				}
			}
		}
		if(p.x+3 <= 7){
			for(int a = p.x+1; a <= p.x+3; a++){
				if(board[a][p.y] == white){
					remove(r, new Point(p.x+3, p.y-2));
					remove(r, new Point(p.x+3, p.y+2));
					break;
				}
			}
			int a = p.x+3;
			if(p.y-2 >= 0){
				for(int b = p.y-1; b >= p.y-2; b--){
					if(board[a][b] == white){
						remove(r, new Point(a, p.y-2));
						break;
					}
				}
			}
			if(p.y+2 <= 7){
				for(int b = p.y+1; b <= p.y+2; b++){
					if(board[a][b] == white){
						remove(r, new Point(a, p.y+2));
						break;
					}
				}
			}
		}
		//y
		if(p.y-3 >= 0){
			for(int b = p.y-1; b >= p.y-3; b--){
				if(board[p.x][b] == white){
					remove(r, new Point(p.x-2, p.y-3));
					remove(r, new Point(p.x+2, p.y-3));
					break;
				}
			}
			int b = p.y-3;
			if(p.x-2 >= 0){
				for(int a = p.x-1; a >= p.x-2; a--){
					if(board[a][b] == white){
						remove(r, new Point(p.x-2, b));
						break;
					}
				}
			}
			if(p.x+2 <= 7){
				for(int a = p.x+1; a <= p.x+2; a++){
					if(board[a][b] == white){
						remove(r, new Point(p.x+2, b));
						break;
					}
				}
			}
		}
		if(p.y+3 <= 7){
			for(int b = p.y+1; b <= p.y+3; b++){
				if(board[p.x][b] == white){
					remove(r, new Point(p.x-2, p.y+3));
					remove(r, new Point(p.x+2, p.y+3));
					break;
				}
			}
			int b = p.y+3;
			if(p.x-2 >= 0){
				for(int a = p.x-1; a >= p.x-2; a--){
					if(board[a][b] == white){
						remove(r, new Point(p.x-2, b));
						break;
					}
				}
			}
			if(p.x+2 <= 7){
				for(int a = p.x+1; a <= p.x+2; a++){
					if(board[a][b] == white){
						remove(r, new Point(p.x+2, b));
						break;
					}
				}
			}
		}
	}
	
	public static void remove(ArrayList<Point> r, Point p){
		for(int a = 0; a < r.size(); a++){
			if(r.get(a).x == p.x && r.get(a).y == p.y){
				r.remove(a);
				return;
			}
		}
	}
	
	public static ArrayList<Point> legalMoves(Point p){
		ArrayList<Point> r = new ArrayList<Point>();
		if(p.x - 3 >= 0){
			if(p.y-2 >= 0 && p.y-2 <= 7){
				r.add(new Point(p.x-3, p.y-2));
			}
			if(p.y+2 >= 0 && p.y+2 <= 7){
				r.add(new Point(p.x-3, p.y+2));
			}
		}
		if(p.x - 2 >= 0){
			if(p.y-3 >= 0 && p.y-3 <= 7){
				r.add(new Point(p.x-2, p.y-3));
			}
			if(p.y+3 >= 0 && p.y+3 <= 7){
				r.add(new Point(p.x-2, p.y+3));
			}
		}
		if(p.x + 3 <= 7){
			if(p.y-2 >= 0 && p.y-2 <= 7){
				r.add(new Point(p.x+3, p.y-2));
			}
			if(p.y+2 >= 0 && p.y+2 <= 7){
				r.add(new Point(p.x+3, p.y+2));
			}
		}
		if(p.x + 2 <= 7){
			if(p.y-3 >= 0 && p.y-3 <= 7){
				r.add(new Point(p.x+2, p.y-3));
			}
			if(p.y+3 >= 0 && p.y+3 <= 7){
				r.add(new Point(p.x+2, p.y+3));
			}
		}
		return r;
	}
	
	public static int toIndex(Point p){
		int index = p.y*8;
		index += p.x;
		index++;
		return index;
	}
	
	public static Point toCoor(int n){
		int x = (n-1)%8;
		int y = (n-1)/8;
		return new Point(x, y);
	}

}
