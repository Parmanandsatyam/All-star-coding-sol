import java.util.*;
import java.io.*;

public class Plateau
{
	static ArrayList<Integer> disperse;
	static ArrayList<Integer> caps;
	static ArrayList<Integer> pins;
	static ArrayList<Integer> stacks;
	static int ROW;
	static int COL;
	static int ROWMAX, ROWMIN, COLMAX, COLMIN;
	static int[][][] grid;
	static void recurse(int num, int loc, boolean isrow)
	{
		if(num == 0)
		{
			test(isrow);
		}
		else if(loc == disperse.size()-1)
		{
			disperse.set(loc, num);
			recurse(num-num, loc+1, isrow);
			disperse.set(loc, 0);
		}
		else
		{
			for(int i = num; i >= 0; i--)
			{
				disperse.set(loc, i);
				recurse(num-i, loc+1, isrow);
			}
		}
	}
	static void test(boolean isrow)
	{
		int cap = 0;
		int pin = 0;
		int lstack = 0;
		if(isrow)
		{
			for(int i = 0; i < disperse.size(); i++)
			{
				int cur1 = disperse.get(i);
				int cur2 = grid[ROWMIN+i][COL][1];
				if(cur1 > cur2 && cur2 > 0)
					return;
				else if(cur1 == cur2)
					cap+=cur2;
				else if(cur1 < cur2 && cur1 > 0)
					pin+=cur2;
				if(cur1>lstack)
					lstack = cur1;
			}
		}
		else
		{
			for(int i = 0; i < disperse.size(); i++)
			{
				int cur1 = disperse.get(i);
				int cur2 = grid[ROW][COLMIN+i][1];
				if(cur1 > cur2 && cur2 > 0)
					return;
				else if(cur1 == cur2)
					cap+=cur2;
				else if(cur1 < cur2 && cur1 > 0)
					pin+=cur2;
				if(cur1>lstack)
					lstack = cur1;
			}
		}
		
		if(cap == 3 && pin == 1 && lstack == 1)
		{
			for(int i = 0; i < disperse.size(); i++)
				System.out.print(disperse.get(i)+" ");
			System.out.println();
		}
		caps.add(cap);
		pins.add(pin);
		stacks.add(lstack);
	}
	static void reduce()
	{
		int max = 0;
		for(int i = caps.size()-1; i >= 0; i--)
			if(caps.get(i) > max)
				max = caps.get(i);
		for(int i = caps.size()-1; i >= 0; i--)
			if(caps.get(i) < max)
			{
				caps.remove(i);
				pins.remove(i);
				stacks.remove(i);
			}
		max = 0;
		for(int i = pins.size()-1; i >= 0; i--)
			if(pins.get(i) > max)
				max = pins.get(i);
		for(int i = pins.size()-1; i >= 0; i--)
			if(pins.get(i) < max)
			{
				caps.remove(i);
				pins.remove(i);
				stacks.remove(i);
			}
		max = 0;
		for(int i = stacks.size()-1; i >= 0; i--)
			if(stacks.get(i) > max)
				max = stacks.get(i);
		for(int i = stacks.size()-1; i >= 0; i--)
			if(stacks.get(i) < max)
			{
				caps.remove(i);
				pins.remove(i);
				stacks.remove(i);
			}
		
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		for(int r = 0; r < 10; r++)
		{
			try
			{
			String[] in = scan.nextLine().split(", ");
			
			grid = new int[5][5][2];
			int[][][] grid2 = new int[5][5][2];
			ArrayList<Integer> black = new ArrayList<Integer>();
			
			for(int i = 0; i < in.length/2; i++)
			{
				int loc = Integer.parseInt(in[i*2])-1;
				int stack = (int)(in[i*2+1].charAt(0)-'0');
				char color = in[i*2+1].charAt(1);
				if(color == 'B')
				{
					black.add(loc);
					grid[loc/5][loc%5][0] = stack;
					grid[loc/5][loc%5][0] = stack;
				}
				else
				{
					grid[loc/5][loc%5][1] = stack;
					grid2[loc/5][loc%5][1] = stack;
				}
			}

			caps = new ArrayList<Integer>();
			pins = new ArrayList<Integer>();
			stacks = new ArrayList<Integer>();
			for(int i = 0; i < black.size(); i++)
			{
				int row = black.get(i)/5;
				int col = black.get(i)%5;
				ROW = row;
				COL = col;
				int stack = grid[row][col][0];
				
				int rowmin = row-stack;
				int rowmax = row+stack;
				int colmin = col-stack;
				int colmax = col+stack;
				if(rowmin < 0)
					rowmin = 0;
				if(rowmax > 4)
					rowmax = 4;
				if(colmin < 0)
					colmin = 0;
				if(colmax > 4)
					colmax = 4;
				ROWMAX = rowmax;
				ROWMIN = rowmin;
				COLMAX = colmax;
				COLMIN = colmin;
				int rowsize = rowmax-rowmin+1;
				int colsize = colmax-colmin+1;
				
				int rowstack = 0;
				for(int j = rowmin; j <= rowmax; j++)
					rowstack += grid[j][col][0];
				int colstack = 0;
				for(int j = colmin; j <= colmax; j++)
					colstack += grid[row][j][0];
				
				
				disperse = new ArrayList<Integer>(0);
				for(int j = 0; j < rowsize; j++)
					disperse.add(0);
				recurse(rowstack, 0, true);
				disperse.clear();
				
				disperse = new ArrayList<Integer>(0);
				for(int j = 0; j < colsize; j++)
					disperse.add(0);
				recurse(colstack, 0, false);
				disperse.clear();
				
			}
			caps.add(0);
			pins.add(0);
			stacks.add(0);
			reduce();
			System.out.println(caps.get(0)+", "+pins.get(0)+", "+stacks.get(0));
			}
			catch(Exception e)
			{
				System.out.println("ERROR: " + e);
			}
		}
	}
}
