package acsl;

import java.io.*;

public class PatternFinder {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("as1-test.txt"));
//		BufferedReader br = new BufferedReader(new FileReader("as1-sample.txt"));
		
		String a = br.readLine();
		String b [] = a.split(" ");
		String c = "";
		String hex = "0123456789ABCDEF";
		for(int i = 0; i <b.length; i++) {
			int count = 0; 
			count += 16*(hex.indexOf(b[i].substring(0,1)));
			count += hex.indexOf(b[i].substring(1,2));
			String d = Integer.toBinaryString(count);
			while(d.length()<8) {
				d = "0" + d; 
			}
			c+= d;

		}
		int [][] array = new int [8][8]; 
		int count = 0; 
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j++) {
				array[i][j] = Integer.parseInt(c.substring(count,count+1));
				count ++;
			}

		}
		for(int i = 0; i < 10; i++) {
			String e = br.readLine();

			int answer = 0;
			String [] f = e.split(" ");
			int row = Integer.parseInt(f[0]);
			int column = Integer.parseInt(f[1]);

			String g= ""; 
			for(int j = 2; j < f.length; j ++) {
				g += f[j];
			}
			int number = 0; 
			int [][] subset = new int [row][column]; 
			for(int j = 0; j < row; j++) {
				for(int k = 0; k < column; k ++) {
					subset[j][k] = Integer.parseInt(g.substring(number,number+1));
					number ++; 
				}
			}
			for(int j = 0; j < 8-row+1; j ++) {
				for(int k = 0; k < 8- column+1; k++) {
					
					if(array[j][k] == subset[0][0]) {
						String str = ""; 
						for(int l = j; l < row+ j; l++) {
							for(int m = k; m < column+ k ; m ++) {
								str += array[l][m]; 
							}
						}
						if(str.equals(g)) {
							answer ++; 
						}
					}
				}
			}
			System.out.println( answer);
			
		}
		
		br.close(); 
	}

}




