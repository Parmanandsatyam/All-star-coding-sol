
/*
School: Neuqua Valley High School
Division: 5-person Intermediate
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LookAndSay 
{

	public static void main(String[] args) 
	{
		File file = new File("as5-test.txt");
		
		try 
		{
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) 
			{
				try 
				{
					num = "1";
					String[] splits = sc.nextLine().split(" ");
					digits = Integer.parseInt(splits[0]);
					n = Integer.parseInt(splits[1]);
					p = Integer.parseInt(splits[2]);
					for(int x = 0; x < digits - 1; x++)
					{
						run();
					}
					String mth = num;
					//System.out.println(mth);
					System.out.println(mth.substring(n - 1, n + p));
				}
				catch(Exception e) 
				{
					System.out.println(0);
				}
			}
			sc.close();
		} 
		catch (Exception e) 
		{
			System.out.println("file not found");
		}
		
		/*
		num = "1"; 
		digits = 11; 
		n = 15; 
		p = 6;
		
		for(int x = 0; x < digits - 1; x++)
		{
			run();
		}
		String mth = num;
		//System.out.println(mth);
		System.out.println(mth.substring(n - 1, n + p));
		*/

	}
	
	static String num; 
	static String term; 
	static int digits; 
	static int n; 
	static int p;
	
	public static String findTerm(int input)
	{
		int index = 0;
		int length = num.length();
		String retString = "";
		
		for(int x = 1; x < input; x++)
		{
			//System.out.println(x);
			//System.out.println();
			length = num.length();
			index = 0;
			int acc = 1;
			int value = Integer.parseInt(num.substring(index, index+1));
			retString = "";
			
			//System.out.println(value);
			while(index < length - 1)
			{
				while(index < length - 1 && value == Integer.parseInt(num.substring(index + 1, index + 2)))
				{
					System.out.println("ran");
					acc++;
					//System.out.println("acc=" + acc);
					index++;
					//System.out.println("index=" + index);
				}
				retString += acc + "" + value;
				System.out.println("***");
				System.out.println(index);
				if(index < length - 1)
				{
					value = Integer.parseInt(num.substring(index + 1, index + 2));
					acc = 1;
					index++;
				}
			}
			retString += acc + "" + value;
			num = retString;
			
			//System.out.println(acc);
			//System.out.println(num);
			
		}
		//num = retString;
		
		return num; 
	}
	
	public static ArrayList<String> breakPoints()
	{
		//String term = num.substring(0, 1);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int x = 0; x < num.length()-1; x++)
		{
			if(!num.substring(x, x + 1).equals(num.substring(x+1, x+2))) 
			{
				//System.out.println("true");
				//System.out.println(x+1);
				arr.add(x+1);
			}
		}
		ArrayList<String> nums = new ArrayList<String>();
		int start = 0; 
		for(int x = 0; x < arr.size(); x++)
		{
			nums.add(num.substring(start, arr.get(x)));
			start = arr.get(x);
			//System.out.println(start);
		}
		nums.add(num.substring(start));
		return nums; 
	}
	 public static void run()
	 {
		 ArrayList<String> splits = breakPoints();
		 String str = "";
		 for(int x = 0; x < splits.size(); x++)
		 {
			 str += splits.get(x).length() + "" + splits.get(x).substring(0, 1);
		 }
		 num = str;
	 }
	
	
}
