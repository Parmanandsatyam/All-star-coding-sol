import java.util.*;

public class ProblemOne
{
		public static boolean or(int a, int b)
		{
			if(a+b>=1)
				return true;
			else
				return false;
		}
		public static void main(String[] args)
		{
			Scanner scan=new Scanner(System.in);
			for(int i=0; i<5; i++)
			{
            try {
				String[] input=scan.nextLine().split("[, ]+");
				each(input);
				} catch (Throwable t) {
            System.out.println("Please reenter the input");
            i--;
            }
				
			}
			scan.close();
		}
		public static boolean calculate(String[][] mat, int r, int c)
		{
			char op=mat[r][c].charAt(0);
			boolean ex=mat[r][c].charAt(1)=='1';
			boolean a=(mat[r+1][c].length()==1)?mat[r+1][c].charAt(0)=='1':mat[r+1][c].charAt(1)=='1';
			boolean b=(mat[r+1][c+1].length()==1)?mat[r+1][c+1].charAt(0)=='1':mat[r+1][c+1].charAt(1)=='1';
			if (op=='A') return (a&&b)==ex;
			else if (op=='R') return (a||b)==ex;
			else return (a!=b)==ex;
		}
		static String toGate(String s) {
			if (s.charAt(0)=='A') return "AND";
			else if (s.charAt(0)=='R') return "OR";
			else return "XOR";
		
		}
		static void each(String[] input) {
			int baseL = Integer.parseInt(input[0]);
			String[][] mat = new String[baseL][baseL];
			int index = 1; 
			for(int r = baseL-1; r>=0; r--)
			{
				for(int c = 0; c <= r; c++)
				{
					mat[r][c]=input[index];
					index++; 
				}
			}
			
			int operStartRow = baseL-2;
			for (int r=operStartRow; r>=0; r--) {
				for (int c=0; c<r+1; c++) {
					if (!calculate(mat,r,c)) {
						System.out.println(toGate(mat[r][c])+", "+(baseL-r)+", "+(c+1));
						return;
					}
				}
			}
			System.out.println("TRUE");
		}
}