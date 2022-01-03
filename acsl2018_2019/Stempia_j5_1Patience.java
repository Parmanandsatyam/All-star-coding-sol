import java.util.*;
import java.io.*;

public class Patience {

	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(new File("as1-test.txt"));
		
		for(int q = 0; q < 10; q++) {
			
			String c = in.nextLine();
			String[] input = c.split(" ");
			//System.out.println(Arrays.toString(input));
			ArrayList<Stack<cards>> arr = new ArrayList();
			arr.add(new Stack<cards>());
			arr.get(0).add(new cards(input[0]));
			for(int i = 1; i < input.length; i++) {
				cards temp = new cards(input[i]);
				for(int j = 0;j<arr.size();j++)
				{
					if(arr.get(j).peek().suit.equals(temp.suit))
					{
						if(j==arr.size()-1)
						{
							arr.add(new Stack<cards>());
							arr.get(j+1).add(temp);
							break;
						}
					}
					else
					{
						if(letters(arr.get(j).peek().value)>letters(temp.value)||(temp.value.equals("K")&&arr.get(j).peek().value.equals("A")))
						{
							arr.get(j).add(temp);
							break;
						}
						else if(j==arr.size()-1)
						{
							
							arr.add(new Stack<cards>());
							arr.get(j+1).add(temp);
							break;
						}
					}
					
				}
				
			}
			/*
			for(int i =0;i<arr.size();i++)
			{
				while(!arr.get(i).isEmpty())
				{
					System.out.print(arr.get(i).pop().whole+",");
				}
				System.out.println();
			}
			*/
			int maxlength = 0;
			int ans = Integer.MAX_VALUE;
			for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i).size()>maxlength) {
					maxlength = arr.get(i).size();
					int sum = 0;
					while(!arr.get(i).isEmpty()) {
						sum += letters(arr.get(i).pop().value);
					}
					ans = sum;
				}
				else if(arr.get(i).size()==maxlength)
				{
					int sum = 0;
					while(!arr.get(i).isEmpty()) {
						sum += letters(arr.get(i).pop().value);
					}
					ans = Math.min(sum,ans);
				}
			}
			
			System.out.println(ans);
		}
		
	}
	
	public static int letters(String str) {
		if(str.replaceAll("[^0-9]", "").equals(str)) {
			return Integer.parseInt(str);
		}
		else {
			if(str.equals("A")) {
				return 1;
			}
			else if(str.equals("T")) {
				return 10;
			}
			else if(str.equals("J")) {
				return 11;
			}
			else if(str.equals("Q")) {
				return 12;
			}
			else if(str.equals("K")) {
				return 13;
			}
		}
		return 0;
	}

}

class cards implements Comparable<cards>{
	
	String whole;
	String value;
	String suit;
	
	cards(String w){
		whole = w;
		value = whole.charAt(0)+"";
		suit = whole.charAt(1)+"";
	}
	
	public String toString(String whole) {
		return whole;
	}
	
	@Override
	public int compareTo(cards arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
