/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kenken;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author EricG
 */
public class KenKen {
	public static int[][] board;
	public static ArrayList<Section> secs;
	public static int[][] perm3 = new int[][]{{1,2,3}, {1,3,2}, {2,1,3}, {2,3,1}, {3,1,2}, {3,2,1}};
	public static int[][] perm4 = new int[][]{{1,2,3,4}, {1,2,4,3}, {1,3,2,4}, {1,3,4,2}, {1,4,2,3}, {1,4,3,2},
												{2,1,3,4},{2,1,4,3},{2,3,1,4},{2,3,4,1},{2,4,1,3},{2,4,3,1},
												{3,1,2,4}, {3,1,4,2},{3,2,1,4},{3,2,4,1},{3,4,1,2},{3,4,2,1},
												{4,1,2,3},{4,1,3,2},{4,2,1,3},{4,2,3,1},{4,3,1,2},{4,3,2,1}};
	
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		boolean done = false;
		int count = 0;
		while(count < 2)
		{	
			if(!done)
			{
				System.out.println("INPUT:");
				args = sc.nextLine().replaceAll("\\s+", "").split(",");
				board = new int[3][3];
				secs = new ArrayList<Section>();
				ArrayList<String> arr = new ArrayList<String>();

				for(int i = 0; i < args.length; i++)
				{
					arr.add(args[i]);
				}
				arr.remove(0);
				for(int i = 0; i < arr.size(); i++)
				{
					if(arr.get(i).contains("#"))
					{
						secs.add(new Section(Integer.parseInt(arr.get(i).substring(0, 1)), new int[]{Integer.parseInt(arr.get(i - 1))}));
						arr.remove(i);
						arr.remove(i - 1);
						i -= 2;
					}
				}

				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int i = 0; i < arr.size(); i++)
				{
					if(arr.get(i).contains("+"))
					{
						Object[] temp2 = temp.toArray();
						int[] temp3 = new int[temp2.length];
						for(int j = 0; j < temp2.length; j++)
						{
							temp3[j] = (int) temp2[j];
						}


						secs.add(new Section(Integer.parseInt(arr.get(i).substring(0, 1)), temp3));
						temp.clear();
					}
					else
						temp.add(Integer.parseInt(arr.get(i)));
				}

				boolean check = false;

				for(int i = 0; i < 6; i++)
				{
					for(int j = 0; j < 6; j++)
					{
						for(int k = 0; k < 6; k++)
						{
							board[0] = perm3[i];
							board[1] = perm3[j];
							board[2] = perm3[k];

							boolean sections = true;

							for(int l = 0; l < secs.size(); l++)
							{
								sections &= secs.get(l).check(board);
							}

							boolean col = true;

							for(int m = 0; m < board.length; m++) 
							{
								col &= (board[0][m] != board[1][m] && board[1][m] != board[2][m] && board[2][m] != board[0][m]);
							} 

							check = col && sections; //solution found

							if(check)
								break;
						}
						if(check)
							break;
					}
					if(check)
						break;
				}

				args = sc.nextLine().replaceAll("\\s+", "").split(",");
				int pos = 0;
				System.out.println("OUTPUT:");
				for(int i = 0; i < args.length; i++)
				{
					pos = Integer.parseInt(args[i]);

					System.out.println(board[(pos - 1) / 3][(pos - 1) % 3]);
				}

				done = true;
			}
			else
			{
				System.out.println("INPUT:");
				args = sc.nextLine().replaceAll("\\s+", "").split(",");
				board = new int[4][4];
				secs = new ArrayList<Section>();
				ArrayList<String> arr = new ArrayList<String>();

				for(int i = 0; i < args.length; i++)
				{
					arr.add(args[i]);
				}
				arr.remove(0);
				for(int i = 0; i < arr.size(); i++)
				{
					if(arr.get(i).contains("#"))
					{
						secs.add(new Section(Integer.parseInt(arr.get(i).substring(0, 1)), new int[]{Integer.parseInt(arr.get(i - 1))}));
						arr.remove(i);
						arr.remove(i - 1);
						i -= 2;
					}
				}

				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(int i = 0; i < arr.size(); i++)
				{
					if(arr.get(i).contains("+"))
					{
						Object[] temp2 = temp.toArray();
						int[] temp3 = new int[temp2.length];
						for(int j = 0; j < temp2.length; j++)
						{
							temp3[j] = (int) temp2[j];
						}

						secs.add(new Section(Integer.parseInt(arr.get(i).substring(0, 1)), temp3));
						temp.clear();
					}
					else
						temp.add(Integer.parseInt(arr.get(i)));
				}

				boolean check = false;

				for(int i = 0; i < 24; i++)
				{
					for(int j = 0; j < 24; j++)
					{
						for(int k = 0; k < 24; k++)
						{
							for(int h = 0; h < 24; h++)
							{
								board[0] = perm4[i];
								board[1] = perm4[j];
								board[2] = perm4[k];
								board[3] = perm4[h];

								boolean sections = true;

								for(int l = 0; l < secs.size(); l++)
								{
									sections &= secs.get(l).check(board);
								}

								boolean col = true;

								for(int m = 0; m < board.length; m++) //may have problems
								{
									col &= (board[0][m] != board[1][m] && board[0][m] != board[2][m] && board[0][m] != board[3][m] &&
											board[1][m] != board[2][m] && board[1][m] != board[3][m] &&
											board[2][m] != board[3][m]);
								} 

								check = col && sections; //solution found

								if(check)
									break;
						}
						if(check)
							break;
					}
					if(check)
						break;
					}
					
					if(check)
						break;
				}
				
				args = sc.nextLine().replaceAll("\\s+", "").split(",");
				int pos = 0;
				System.out.println("OUTPUT:");
				for(int i = 0; i < args.length; i++)
				{
					pos = Integer.parseInt(args[i]);

					System.out.println(board[(pos - 1) / 4][(pos - 1) % 4]);
				}

				done = true;
			}
			
			count++;
		}
	}


	static class Section
	{
		int add;
		int[] nums;
		
		public Section(int a, int[] pos)
		{
			add = a;
			nums = pos;
		}
		
		public boolean check(int[][] board)
		{
			int sum = 0;
			for(int i = 0; i < nums.length; i++)
			{
				sum += board[(nums[i] - 1) / board.length][(nums[i] - 1) % board.length];
			}
			
			return sum == add;

			
		}
	}
	
}
