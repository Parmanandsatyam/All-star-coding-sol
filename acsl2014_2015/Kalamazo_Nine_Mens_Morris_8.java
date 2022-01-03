import java.util.*;
import java.io.*;
public class Nine_Mens_Morris
{
	public static void print(int x, int y)
	{
		String output="";
		output+=(char)('a'+x);
		output+=7-y;
		System.out.println(output);
	}
	public static void main(String[] args) throws IOException
	{
		//Scanner in = new Scanner(new File("file1.txt"));
		Scanner in = new Scanner(System.in);
		for (int nr=0;nr<2;nr++)
		{
			
			boolean[][] connected = {{true, false, false, true, false, false, true},
									{false, true, false, true, false, true, false},
									{false, false, true, true, true, false, false},
									{true, true, true, false, true, true, true},
									{false, false, true, true, true, false, false},
									{false, true, false, true, false, true, false},
									{true, false, false, true, false, false, true}};
			
			//input
			String[] input = in.nextLine().split(", ");
			int numPieces = Integer.parseInt(input[0]);
			char[][] board = new char[7][7];
			
			for (int j=0; j<numPieces; j++)
				board[7-Integer.parseInt(input[j+1].charAt(1)+"")][input[j+1].charAt(0)-'a']='b';
			
			input = in.nextLine().split(", ");
			numPieces = Integer.parseInt(input[0]);
			for (int j=0; j<numPieces; j++)
				board[7-Integer.parseInt(input[j+1].charAt(1)+"")][input[j+1].charAt(0)-'a']='w';
	
			//five pieces to check
			for (int i=0; i<5; i++)
			{
				String piece=in.nextLine();
				int startX=piece.charAt(0)-'a';
				int startY=7-piece.charAt(1)+'0';
				char curColor=board[startY][startX];
				//System.out.println(startX+"  "+startY);
				int bestX=7, bestY=-1;
				boolean postMid=false;
				board[startY][startX]='0'-'0';
				boolean done=false;
				
				//find if make a new mill
				for(int j=6; j>=0; j--)
				{
					int curRowCount=0;
					for (int k=0; k<7; k++)
					{
						//check rows
						if (board[j][k]==curColor)
							curRowCount++;
						if (j==3 && k==3)
						{
							postMid=true;
							curRowCount=0;
						}
						//System.out.println(j+" "+k+" "+curRowCount+"  "+board[j][k]+"  ");
						if (curRowCount==2)
						{
							//find teh open slot
							for (int l=(postMid && j==3 ? 4 : 0); l< (!postMid && j==3 ? 3 : 7); l++)
							{
								if (connected[j][l] && board[j][l]==0)
								{
									if (j!=startY || l!=startX)
									{
										if (j>bestY || j==bestY && l<bestX)
										{
											
											bestY=j;bestX=l;done=true;
											//System.out.print(":");print(bestX, bestY);
										}
									}
								}
							}
						}	
					}
				}
				postMid=false;
				//find if make a new mill-col
				for(int j=6; j>=0; j--)
				{
					
					int curColCount=0;
					for (int k=0; k<7; k++)
					{
						//check cols
						
						//System.out.print(curColCount+" ");print(j, k);
						if (board[k][j]==curColor)
							curColCount++;
						if (j==3 && k==3)
						{
							curColCount=0;
							postMid=true;
						}
						//System.out.print(curColCount+" ");print(j, k);

						
						if (curColCount==2)
						{
							//find teh open slot
							for (int l=(postMid && j==3 ? 4 : 0); l< (!postMid && j==3 ? 3 : 7); l++)
							{
								//System.out.println(l);
								if (l==3 && j==3)//{System.out.println("tst");
									break;//}
								if (connected[l][j] && board[l][j]==0)
								{
									if (j!=startX || l!=startY)
									{
										if (l>bestY || l==bestY && j<bestX)
										{
											
											bestY=l;bestX=j;done=true;
											//System.out.print("::");print(bestX, bestY);
										}
									}
								}
							}
						}
					}
				}
					
				if(!done)
				{
					postMid=false;
					//find if BLOCK a new mill
					for(int j=6; j>=0; j--)
					{
						int curRowCount=0;
						for (int k=0; k<7; k++)
						{
							//check rows
							if (board[j][k]!=curColor && board[j][k]!=0)
								curRowCount++;
							if (j==3 && k==3)
							{
								postMid=true;
								curRowCount=0;
							}
							//System.out.println(j+" "+k+" "+curRowCount+"  "+board[j][k]+"  ");
							if (curRowCount==2)
							{
								//find teh open slot
								for (int l=(postMid && j==3 ? 4 : 0); l< (!postMid && j==3 ? 3 : 7); l++)
								{
									if (connected[j][l] && board[j][l]==0)
									{
										if (j!=startY || l!=startX)
										{
											if (j>bestY || j==bestY && l<bestX)
											{
												
												bestY=j;bestX=l;done=true;//System.out.print(":");print(bestX, bestY);
											}
										}
									}
								}
							}	
						}
					}
					
					//find if BLOCK a mill-col
					postMid=false;
					for(int j=6; j>=0; j--)
					{
						int curColCount=0;
						for (int k=0; k<7; k++)
						{
							//check cols
							//System.out.println(":"+curColor);
							if (board[k][j]!=curColor && board[k][j]!=0)
								curColCount++;
							if (j==3 && k==3)
							{
								curColCount=0;
								postMid=true;
							}
							
							if (curColCount==2)
							{
								//find teh open slot
								for (int l=(postMid && j==3 ? 4 : 0); l< (!postMid && j==3 ? 3 : 7); l++)
								{
									if (connected[l][j] && board[l][j]==0)
									{
										if (j!=startX || l!=startY)
										{
											if (l>bestY || l==bestY && j<bestX)
											{
												//System.out.print(":");print(bestX, bestY);
												bestY=l;bestX=j;done=true;
											}
										}
									}
								}
							}
						}
					}
				}
			
				
				//find the empty intersection
				if (!done)
				{
					postMid=false;
					for (int j=6; j>=0; j--)
					{
						for (int k=0; k<7; k++)
						{
							if (connected[j][k] && board[j][k]==0)
							{
								if (j!=startY || k!=startX)
								{
									if (j>bestY || j==bestY && k<bestX)
									{
										//System.out.print(":");print(bestX, bestY);
										bestY=j;bestX=k;done=true;
									}
								}
							}
						}
					}
				}
				
				//output
				board[startY][startX]=curColor;
				print(bestX, bestY);
			}
		}
	}
}
