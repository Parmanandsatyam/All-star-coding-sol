import java.util.*;
import java.io.*;


public class HexgridPathGenerator {

	String endNode, startNode;
	int pathLength;
	
	String[] inputs;
	
	public HexgridPathGenerator(String input) {
		inputs = input.split(" ");
		startNode = inputs[0];
		endNode = inputs[1];
		pathLength = Integer.parseInt(inputs[2]);
		
	}
	
	public void solve() {
		
		ArrayList<String> initTouched = new ArrayList<String>();
		initTouched.add(startNode);
		ArrayList<String> candidates = moveHelper(initTouched, pathLength, startNode);
		
		int count = 0;
		for(String s: candidates) {
			if(s.substring(s.length() - endNode.length()).equals(endNode))
				count++;
		}
		
		System.out.println(count);
		
		
	}
	
	public ArrayList<String> moveHelper(ArrayList<String> touched, int remainingMoves, String position) {
		ArrayList<String> result = new ArrayList<String>();
		if(remainingMoves == 0) {
			result.add(consolidate(touched));
			return result;
		}
			
		ArrayList<String> adj = new ArrayList<String>();
		char first = position.charAt(0);
		int second = Integer.parseInt(position.substring(1));
		int parity = (int) (position.charAt(0) - 'A') % 2;
		if (parity ==0) {
			adj.add("" + ((char) (first-1)) + (second+1));
			adj.add("" + ((char) (first-1)) + (second));
			adj.add("" + ((char) (first+1)) + (second+1));
			adj.add("" + ((char) (first+1)) + (second));
			adj.add("" + ((char) (first)) + (second+1));
			adj.add("" + ((char) (first)) + (second-1));
		}
		else {
			adj.add("" + ((char) (first-1)) + (second-1));
			adj.add("" + ((char) (first-1)) + (second));
			adj.add("" + ((char) (first+1)) + (second-1));
			adj.add("" + ((char) (first+1)) + (second));
			adj.add("" + ((char) (first)) + (second+1));
			adj.add("" + ((char) (first)) + (second-1));
		}
		
		clean(adj,touched);
		
		for(String s: adj) {
			ArrayList<String> newTouch = new ArrayList<String>();
			newTouch.addAll(touched);
			newTouch.add(s);
			result.addAll(moveHelper(newTouch, remainingMoves-1, s));
		}
		
		return result;
	}
	
	private String consolidate(ArrayList<String> touched) {
		// TODO Auto-generated method stub
		String result = "";
		for(String s: touched) {
			result += s;
		}
		return result;
	}

	public void clean(ArrayList<String> adj, ArrayList<String> touched) {
		for(int i = 0; i < touched.size(); i++) {
			adj.remove(touched.get(i));
		}
		
		for(int i = 0; i < adj.size(); i++) {
			if(adj.get(i).charAt(0) > 'Z' || adj.get(i).charAt(0) < 'A' || Integer.parseInt(adj.get(i).substring(1)) < 1) {
				adj.remove(i);
				i--;
			}
				
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("as9-test.txt"));
		while(sc.hasNextLine()) {
			try {
				String input = sc.nextLine();
				HexgridPathGenerator g  = new HexgridPathGenerator(input);
				g.solve();
			} catch(Exception e) {
				System.out.println("ERROR: MISSION FAILED");
			}
			
		}
		
	}
	
	

}
