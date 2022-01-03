import java.util.Arrays;
import java.util.Scanner;


public class tomography {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		for (int k = 0; k < 10; k++){
			String s1 = scan.next();
			String s2 = scan.next();
			int n = s1.length();
			int[] v = new int[n];
			for (int i = 0; i < n; i++){
				v[i] = Integer.parseInt(s1.charAt(i) + "");
			}
			int[] h = new int[n];
			for (int i = 0; i < n; i++){
				h[i] = Integer.parseInt(s2.charAt(i) + "");
			}
			float[][] matrix = new float[n][n];
			for (int i = 0; i < n; i++){
				float fill = (float)v[i] / (float)n;
				for (int j = 0; j < n; j++){
					matrix[i][j] = fill;
				}
			}
			for (int i = 0; i < n; i++){
				float fill = (float)h[i] / (float)n;
				for (int j = 0; j < n; j++){
					matrix[j][i] += fill;
				}
			}
			int[][] matrix1 = new int[n][n];
			
			for (int i = 0; i < n; i++){
				matrix1[i] = getRow(matrix[i], v[i]);
			}
			/*for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					System.out.print(matrix[i][j] + ",");
				}
				System.out.println();
			}*/
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					System.out.print(matrix1[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	public static int[] getRow(float[] row, int n){
		int[] greatest = new int[n];
		float[] rowC = row.clone();
		/*for (int i = 0; i < row.length; i++){
			System.out.print(row[i] + ",");
		}
		System.out.print(n + "\n");*/
		/*for (int j = 0; j < row.length; j++){
			if (row[j] >= 1){
				int leastIndex = 0;
				for (int l = 1; l < greatest.length; l++){
					if (row[greatest[l]] < row[greatest[leastIndex]]){
						leastIndex = l;
					}
				}
				if (row[j] > row[greatest[leastIndex]]){
					greatest[leastIndex] = j;
				}
			}
		}*/
		int[] index = new int[row.length];
		for (int i = 0; i < row.length; i++) {
			index[i] = i;
		}
		for (int j = 0; j < row.length; j++) {
			for (int i = 0; i < row.length-1; i++) {
				if (rowC[i] < rowC[i+1]) {
					float t = rowC[i];
					rowC[i] = rowC[i+1];
					rowC[i+1] = t;
					t = index[i];
					index[i] = index[i+1];
					index[i+1] = (int)t;
				}
			}
		}
		int[] out = new int[row.length];
		for (int i = 0; i < n; i++) {
			out[index[i]] = 1;
		}
		/*for (int i = 0; i < greatest.length; i++){
			System.out.print(greatest[i]);
		}
		System.out.println();
		int[] output = new int[row.length];
		for (int i : greatest){
			output[i] = 1;
		}*/
		return out;
	}
}
