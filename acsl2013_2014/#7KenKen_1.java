import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class KenKen {
	static Integer[] grid3 = new Integer[9];
	static Integer[] ans3 = new Integer[9];

	static Integer[] grid4 = new Integer[16];
	static Integer[] ans4 = new Integer[16];

	static ArrayList<ArrayList<Integer>> rules = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> rules2 = new ArrayList<ArrayList<Integer>>();


	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("puzzle.in"));
		int numSects = scan.nextInt();
		for(int x = 0; x < numSects; x++){
			ArrayList<Integer> sect = new ArrayList<Integer>();
			String n = scan.next();
			while(!(n.endsWith("+") || n.endsWith("#"))){
				sect.add(Integer.parseInt(n) - 1);
				n = scan.next();
			}
			sect.add(Integer.parseInt(n.substring(0,1)));
			rules.add(sect);
		}
		for(int x = 0; x < 9; x++){
			grid3[x] = 0;
			ans3[x] = 0;
		}

		build(0, 1);
		build(0, 2);
		build(0, 3);


		if(ans3[0] == 0){
			System.out.println(1);
		}else{
			for(int x = 0; x < 5; x++){
				int printLoc = scan.nextInt() - 1;
				System.out.println(x+1+". " +ans3[printLoc]);
			}	
		}







		//4 by 4

		numSects = scan.nextInt();
		for(int x = 0; x < numSects; x++){
			ArrayList<Integer> sect = new ArrayList<Integer>();
			String n = scan.next();
			while(!(n.endsWith("+") || n.endsWith("#"))){
				sect.add(Integer.parseInt(n) - 1);
				n = scan.next();
			}
			sect.add(Integer.parseInt(n.substring(0,1)));
			rules2.add(sect);
		}
		for(int x = 0; x < 16; x++){
			grid4[x] = 0;
			ans4[x] = 0;
		}

		build2(0, 1);
		build2(0, 2);
		build2(0, 3);
		build2(0, 4);

		if(ans4[0] == 0){
			System.out.println(1);
		}else{
			for(int x = 0; x < 5; x++){
				int printLoc = scan.nextInt() - 1;
				System.out.println(x+6+". " + ans4[printLoc]);
			}	
		}
	}


	private static void build(int loc, int guess) {
		grid3[loc] = guess;
		boolean valid = true;
		for(int i = 0; i < 9; i++){
			if(i != loc){
				if(grid3[i] != 0){
					if(loc/3 == i/3 && grid3[i] == grid3[loc]){
						valid = false;
					}
					if(loc%3 == i%3 && grid3[i] == grid3[loc]){
						valid = false;
					}
				}
			}
		}
		if(valid && loc != 8){
			build(loc+1, 1);
			build(loc+1, 2);
			build(loc+1, 3);
		}else if(valid && loc == 8){
			boolean done = checkrules();
			if(done){
				for(int j = 0; j < 9; j++){
					ans3[j] = grid3[j];
				}
			}
		}
		grid3[loc] = 0;
	}

	private static void build2(int loc, int guess) {
		grid4[loc] = guess;
		boolean valid = true;
		for(int i = 0; i < 16; i++){
			if(i != loc){
				if(grid4[i] != 0){
					if(loc/4 == i/4 && grid4[i] == grid4[loc]){
						valid = false;
					}
					if(loc%4 == i%4 && grid4[i] == grid4[loc]){
						valid = false;
					}
				}
			}
		}
		if(valid && loc != 15){
			build2(loc+1, 1);
			build2(loc+1, 2);
			build2(loc+1, 3);
			build2(loc+1, 4);
		}else if(valid && loc == 15){
			boolean done = checkrules2();
			if(done){
				for(int j = 0; j < 16; j++){
					ans4[j] = grid4[j];
				}
			}
		}
		grid4[loc] = 0;
	}

	private static boolean checkrules() {
		for(int x = 0; x < rules.size(); x++){
			int sum = 0;
			for(int i = 0; i < rules.get(x).size()-1; i++){
				sum += grid3[rules.get(x).get(i)];
			}
			if(sum != rules.get(x).get(rules.get(x).size() - 1)) return false;
		}
		return true;
	}

	private static boolean checkrules2() {
		for(int x = 0; x < rules2.size(); x++){
			int sum = 0;
			for(int i = 0; i < rules2.get(x).size()-1; i++){
				sum += grid4[rules2.get(x).get(i)];
			}
			if(sum != rules2.get(x).get(rules2.get(x).size() - 1)) return false;
		}
		return true;
	}

}
