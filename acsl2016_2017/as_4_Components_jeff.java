import java.util.*;
public class Components {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		for(int problem = 1; problem < 11; problem++) {
			try {
				String[] inputs = scan.nextLine().trim().split(", ");
				int N = Integer.parseInt(inputs[0]);
				String binString = "";
				String hexString = inputs[1];
				for(int i = 0; i < hexString.length(); i++) {
					String curChar = hexString.substring(i, i+1);
					String tempBin = Integer.toBinaryString(Integer.parseInt(curChar,16));
					while(tempBin.length() != 4) {
						tempBin = "0" + tempBin;
					}
					binString += tempBin;
				}
				HashMap<String, HashSet<String>> graph = new HashMap<String, HashSet<String>>();
				HashSet<String> letters = new HashSet<String>();
				for(int i = 0; i < N; i++) {
					char let = (char) ((int)'A' + i);
					graph.put(Character.toString(let), new HashSet<String>());
					letters.add(Character.toString(let));
				}
				int curIndex = 0;
				for(int i = 0; i < N; i++) {
					int length = N-i-1;
					String tempString = binString.substring(curIndex, curIndex + length);
					curIndex = curIndex + length;
					char curLet = (char) ((int) 'A' + i);
					for(int j = 1; j <= length; j++) {
						char finalLet = (char) ((int) curLet + j);
						if(tempString.charAt(j-1) == '1') {
							graph.get(Character.toString(curLet)).add(Character.toString(finalLet));
							graph.get(Character.toString(finalLet)).add(Character.toString(curLet));
						}
					}
				}
				ArrayList<ArrayList<String>> components= new ArrayList<ArrayList<String>>();
				ArrayList<String> tempArr = new ArrayList<String>();
				tempArr.add("A");
				ArrayList<String> cc = getCC(graph, tempArr, "A");
				for(String x:cc) {
					letters.remove(x);
				}
				components.add(cc);
				while(letters.size() != 0) {
					String node = "";
					for(String y:letters) {
						node = y;
					}
					tempArr = new ArrayList<String>();
					tempArr.add(node);
					cc = getCC(graph, tempArr, node);
					components.add(cc);
					for(String x:cc) {
						letters.remove(x);
					}
				}
				ArrayList<String> answer = components.get(0);
				Collections.sort(answer);
				String actanswer = "";
				HashSet<String> answer2 = new HashSet<String>();
				for(String x:answer) {
					if(!answer2.contains(x)) {
						answer2.add(x);
						actanswer += x;
					}
				}
				System.out.println(components.size() + " " + actanswer);
			}
			catch(Exception e) {
				System.out.println("BAD INPUT");
			}
		}
		System.exit(0);
	}
	public static ArrayList<String> getCC(HashMap<String, HashSet<String>> graph, ArrayList<String> curr, String node) {
		HashSet<String> neigh = graph.get(node);
		for(String x:neigh) {
			if(!curr.contains(x)) {
				curr.add(x);
				curr.addAll(getCC(graph, curr, x));
			}
		}
		return curr;
	}
}