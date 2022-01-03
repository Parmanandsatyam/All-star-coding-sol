import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Element {

	Element(int a, String b) {
		this.frequency = a;
		this.name = b;
	}

	int frequency;
	String name;
}

public class CompressedLists {

	private static String[] input = new String[10];

	public static void main(String[] args) throws IOException {
		getInput();
		for (int i = 0; i < 10; i++) {
			logic(input[i]);
		}
	}

	private static void logic(String in) {
		
		String output = "";
		
		String word = in.split(" ")[0];
		int freq = Integer.parseInt("" + in.split(" ")[1]);
		
		ArrayList<Element> fList = new ArrayList<Element>();
		for (int i = 0 ; i < word.length(); i++) {
			boolean repeat = false;
			for (int x = 0 ; x < fList.size(); x++) {
				if(("" + word.charAt(i)).equals(fList.get(x).name)) {
					fList.get(x).frequency++;
					repeat = true;
				}
			}
			if (repeat == false) {
				fList.add(new Element(1, "" + word.charAt(i)));
			}
		}
		fList = sort(fList);
		
		for (int i = 0 ; i < fList.size(); i++) {
			if(fList.get(i).frequency == freq) {
				output = output + fList.get(i).name;
			}
		}
		
		while(fList.size() != 1) {
			fList.add(new Element(fList.get(0).frequency + fList.get(1).frequency, alphabetise(fList.get(0).name + fList.get(1).name)));
			fList.remove(0);
			fList.remove(0);
			fList = sort(fList);
			for (int i = 0 ; i < fList.size(); i++) {
				if(fList.get(i).frequency == freq) {
					output = output + fList.get(i).name;
				}
			}
		}
		
		output = alphabetise(output);
		
		if (output.equals("")) {
			System.out.println("NONE");
		} else {
			System.out.println(output);
		}
	}

	private static void getInput() throws IOException {
		File file = new File("/Users/acsltest/Desktop/as2-test.txt");
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

	private static String alphabetise(String n) {
		
		ArrayList<Character> c = new ArrayList<Character>();
		for (int i = 0 ; i < n.length(); i++) {
			boolean repeat = false;
			for (int x = 0 ; x < c.size(); x++) {
				if (c.get(x) == n.charAt(i)) {
					repeat = true;
				}
			}
			if (repeat == false) {
				c.add(n.charAt(i));
			}
		}
		
		String s = "";
		for (int i = 0 ; i < c.size(); i++) {
			s = s + c.get(i);
		}

		char[] temp = s.toCharArray();

		for (int i = 0; i < temp.length; i++) {
			int minVal = 100000;
			int minId = 10000;
			for (int x = i; x < temp.length; x++) {
				if (temp[x] < minVal) {
					minId = x;
					minVal = temp[x];
				}
			}
			char t = temp[i];
			temp[i] = (char) minVal;
			temp[minId] = t;
		}

		String output = "";
		for (int i = 0; i < temp.length; i++) {
			output = output + temp[i];
		}
		return output;
	}

	private static ArrayList<Element> sort(ArrayList<Element> a) {
		for (int i = 0; i < a.size(); i++) {
			int minVal = 100000;
			int minId = 100000;
			for (int x = i; x < a.size(); x++) {
				if (a.get(x).frequency < minVal) {
					minVal = a.get(x).frequency;
					minId = x;
				}
			}
			Element e = a.get(i);
			a.set(i, a.get(minId));
			a.set(minId, e);
		}

		ArrayList<Integer> frequencies = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++) {
			boolean repeat = false;
			for (int x = 0; x < frequencies.size(); x++) {
				if (a.get(i).frequency == frequencies.get(x)) {
					repeat = true;
				}
			}
			if (repeat == false) {
				frequencies.add(a.get(i).frequency);
			}
		}

		for (int i = 0; i < frequencies.size(); i++) {
			int beg = 0;
			int end = 0;
			for (int x = 0; x < a.size(); x++) {
				if (a.get(x).frequency == frequencies.get(i)) {
					beg = x;
					break;
				}
			}
			for (int x = beg;; x++) {
				if (a.get(beg).frequency != a.get(x).frequency) {
					end = x - 1;
					break;
				}
				if (x == a.size() - 1) {
					end = x;
					break;
				}
			}

			for (int x = beg; x <= end; x++) {
				int minId = 10000;
				int minVal = 10000;
				for (int y = x; y <= end; y++) {
					if ((int) a.get(y).name.charAt(0) < minVal) {
						minId = y;
						minVal = (int) a.get(y).name.charAt(0);
					}
				}
				Element e = a.get(x);
				a.set(x, a.get(minId));
				a.set(minId, e);
			}
		}
		return a;
	}

}
