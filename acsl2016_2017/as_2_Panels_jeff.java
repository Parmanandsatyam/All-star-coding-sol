import java.util.Scanner;

public class Panels 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		for(int j = 0; j < 5; j++)
		{
			try
			{
				String[] strs = sc.nextLine().split(", ");
				double w1 = Double.parseDouble(strs[0]), h1 = Double.parseDouble(strs[1]), w2 = Double.parseDouble(strs[2]), h2 = Double.parseDouble(strs[3]);
				if(w2 > w1 || h2 > h1) {
					System.out.println(w1*h1);
					continue;
				}
				int num1 = (int)(Math.floor(w1*1.0/w2)), numIter = (int)(Math.floor((h1-h2)/(2.0*h2)));
				double area = num1*w2*h2;
				for(int i = 1; i <= numIter; i++)
					area += Math.floor((w1-0.5*i*w2)/w2)*w2*h2;
				for(int i = 1; i <= numIter; i++)
					area += Math.floor((w1-0.25*i*w2)/w2)*w2*h2;
				System.out.println(w1*h1-area);
			}
			catch (Exception arg1) {
				System.out.println("Bad Input");
			}
		}
		System.exit(0);
	}
}