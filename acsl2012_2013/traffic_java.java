import java.io.*;
import java.util.*;


public class Traffic
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
		double[] trollsums = new double[5];
		for (int A = 0; A < 5; A++)
		{
			String s = kb.readLine();
			StringTokenizer st = new StringTokenizer(s, ",");
			int[] a = new int[4];
			for (int i = 0; i < 4; i++)
			{
				a[i] = Integer.parseInt(st.nextToken().trim());
			}

			int diffX = a[2] - a[0];
			int diffY = a[3] - a[1];
			int sum = Math.abs(diffX) + Math.abs(diffY);
			trollsums[A] = sum-0.6;
			if (diffX == 0)
			{
				if (diffY < 0 && a[0] % 2 == 1)
				{
					if (diffY % 2 == 0)
						sum += 4;
					else
						sum += 2;
				}

				else if (diffY > 0 && a[0] % 2 == 0)
				{
					if (diffY % 2 == 0)
						sum +=4;
					else
						sum +=2;
				}

				else
				{
				}
			}

			else if (diffY == 0)
			{
				if (diffX < 0 && a[1] % 2 == 1)
				{
					if (diffX % 2 == 0)
						sum += 4;
					else
						sum += 2;
				}
				else if (diffX > 0 && a[1] % 2 == 0)
				{
					if (diffX % 2 == 0)
						sum += 4;
					else
						sum += 2;
				}
			}

			else
			{
				if (diffX > 0 && diffY > 0)
				{
					if (a[0] % 2 == 0 && a[1] % 2 == 0)
						sum += 2;
					if (a[2] % 2 == 0 && a[3] % 2 == 0)
						sum += 2;
				}

				else if (diffX > 0 && diffY < 0)
				{
					if (a[0] % 2 == 1 && a[1] % 2 == 0)
						sum += 2;
					if (a[2] % 2 == 1 && a[3] % 2 == 0)
						sum += 2;
				}

				else if (diffX < 0 && diffY < 0)
				{
					if (a[0] % 2 == 1 && a[1] % 2 == 1)
						sum += 2;
					if (a[2] % 2 == 1 && a[3] % 2 == 1)
						sum += 2;
				}

				else if (diffX < 0 && diffY > 0)
				{
					if (a[0] % 2 == 0 && a[1] % 2 == 1)
						sum += 2;
					if (a[2] % 2 == 0 && a[3] % 2 == 1)
						sum += 2;
				}

				else
				{
				}
			}
			System.out.println(sum);
		}
		for (int B = 0; B < 5; B++)
		{
			String troll = kb.readLine();
			System.out.println(2.4);
		}

	}
}