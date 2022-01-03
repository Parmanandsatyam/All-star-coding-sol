import java.util.*;
import java.io.*;


public class CompressedTrees {

	ArrayList<Node> list;
	ArrayList<Node> combList;
	
	public CompressedTrees(String input) {
		//int x = 1/0;
		String[] inputs = input.split(" ");
		list = new ArrayList<Node>();
		combList = new ArrayList<Node>();
		for(int i = 0; i < inputs[0].length(); i++) {
			Node n = new Node(1, inputs[0].substring(i, i + 1));
			increment(n,list);
		}
		for(Node n: list) {
			combList.add(n);
		}
		while(combList.size() > 1) {
//			System.out.println(combList);
			merge(combList.get(0),combList.get(1));
		}
//		System.out.println(combList);
		
		Node n = getNode(inputs[1]);
		System.out.println(position(n));
		
	}
	private void merge(Node node, Node node2) {
		int total = node.num + node2.num;
		String name;
		if (node.name.compareTo(node2.name) < 0)
			name = node.name + node2.name;
		else
			name = node2.name + node.name;
		Node c = new Node(total, name);
		node.rpar = c;
		node2.lpar = c;
		combList.remove(node);
		combList.remove(node2);
		increment(c, combList);
		Collections.sort(combList);
		
	}
	private void increment(Node n, ArrayList<Node> list2) {
		int index = -1;
		for(int i = 0; i < list2.size(); i++) {
			if(list2.get(i).name.equals(n.name)) { 
				index = i;
				break;
			}
		}
		if(index == -1) {
			list2.add(n);
		} else {
			list2.get(index).num += n.num;
		}
		Collections.sort(list2);
	}
	
	public String position(Node n) {
		if(n.lpar == null && n.rpar == null) {
			return "";
		}
		if(n.lpar == null)
			return position(n.rpar) + "0";
		if(n.rpar == null)
			return position(n.lpar) + "1"; 
		return "";
	}
	
	public Node getNode(String name) {
		for(Node n: list) {
			if(n.name.equals(name)) 
				return n;
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("as8-test.txt"));
		while(sc.hasNextLine()) {
			try {
				String input = sc.nextLine();
				CompressedTrees c = new CompressedTrees(input);
			} catch(Exception e) {
				System.out.println("ERROR: MISSION FAILED.");
			}
			
		}
			

	}
	
	
	private class Node implements Comparable<Node> {
		int num;
		String name;
		Node lpar, rpar;
		
		public Node(int i, String s) {
			num = i;
			name = s;
		}
		
		public int compareTo(Node n) {
			if (num != n.num) {
				return num - n.num;
			}
			return name.compareTo(n.name);
		}
		
		public String toString() {
			return num+name;
		}
	}
}
