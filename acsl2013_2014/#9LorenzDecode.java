//	ACSL 2014 All Star Cherry Creek High School Team Senior 5 Division

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class LorenzDecode
{
	
	public static HashMap< Character, Byte > lookup = new HashMap< Character, Byte >()
	{
		{
			
			put( 'A', (byte) Integer.parseInt( 11000 + "", 2 ) );
			put( 'B', (byte) Integer.parseInt( 10011 + "", 2 ) );
			put( 'C', (byte) Integer.parseInt( 1110 + "", 2 ) );
			put( 'D', (byte) Integer.parseInt( 10010 + "", 2 ) );
			put( 'E', (byte) Integer.parseInt( 10000 + "", 2 ) );
			put( 'F', (byte) Integer.parseInt( 10110 + "", 2 ) );
			put( 'G', (byte) Integer.parseInt( 1011 + "", 2 ) );
			put( 'H', (byte) Integer.parseInt( 101 + "", 2 ) );
			put( 'I', (byte) Integer.parseInt( 1100 + "", 2 ) );
			put( 'J', (byte) Integer.parseInt( 11010 + "", 2 ) );
			put( 'K', (byte) Integer.parseInt( 11110 + "", 2 ) );
			put( 'L', (byte) Integer.parseInt( 1001 + "", 2 ) );
			put( 'M', (byte) Integer.parseInt( 111 + "", 2 ) );
			put( 'N', (byte) Integer.parseInt( 110 + "", 2 ) );
			put( 'O', (byte) Integer.parseInt( 11 + "", 2 ) );
			put( 'P', (byte) Integer.parseInt( 1101 + "", 2 ) );
			put( 'Q', (byte) Integer.parseInt( 11101 + "", 2 ) );
			put( 'R', (byte) Integer.parseInt( 1010 + "", 2 ) );
			put( 'S', (byte) Integer.parseInt( 10100 + "", 2 ) );
			put( 'T', (byte) Integer.parseInt( 1 + "", 2 ) );
			put( 'U', (byte) Integer.parseInt( 11100 + "", 2 ) );
			put( 'V', (byte) Integer.parseInt( 1111 + "", 2 ) );
			put( 'W', (byte) Integer.parseInt( 11001 + "", 2 ) );
			put( 'X', (byte) Integer.parseInt( 10111 + "", 2 ) );
			put( 'Y', (byte) Integer.parseInt( 10101 + "", 2 ) );
			put( 'Z', (byte) Integer.parseInt( 10001 + "", 2 ) );
			put( '+', (byte) Integer.parseInt( 11011 + "", 2 ) );
			put( '/', (byte) 0 );
			put( '9', (byte) Integer.parseInt( 100 + "", 2 ) );
			put( '8', (byte) Integer.parseInt( 11111 + "", 2 ) );
			put( '4', (byte) Integer.parseInt( 1000 + "", 2 ) );
			put( '3', (byte) Integer.parseInt( 10 + "", 2 ) );
			
		}
	};
	
	public static Character[] keyOne = new Character[14], keyTwo = new Character[4];
	
	public static String[] dKs = new String[14];
	
	public void start()
	{
		
		
		Scanner in = new Scanner( System.in );
		for( int linesNums = 0; linesNums < 5; linesNums++ )
		{
			System.out.println("Input line " + linesNums);
			
			String[] inline = in.nextLine().split(", ");
			String z = inline[0];
			int n = Integer.parseInt(inline[1]);
			
			String dZ = delta(z);
			
			int maxSlash = -1;
			int index = 9999;
			
			for(int i=0;i<14;i++){
				String out = "";
				
				for(int j=0;j<dZ.length();j++){
					char c1 = dKs[i].charAt(j);
					char c2 = dZ.charAt(j);
					
					byte b1 = lookup.get(c1);
					byte b2 = lookup.get(c2);
					
					byte bOut = (byte)(b1^b2);
					
					for( Entry<Character, Byte> b : lookup.entrySet() )
						if( b.getValue() == bOut )
							out += b.getKey();

				}
				
				int counter= 0;
				for(char c: out.toCharArray()){
					if(c=='/')
						counter++;
				}
				
				if(counter>maxSlash){
					maxSlash = counter;
					index = i;
				}
				
			}
			
			System.out.println("Output line " + (2*linesNums + 1) + ": " + (index+1));
			
			
			/*
			XWZOFTRZ9, 1
			VD4/9OECN3, 1
			+PATTZ9BXGE+UD, 3
			LKVAHI+VBVXWKIRHEZ, 3
			CIX4BA94TYZVMXHM, 1
			*/
			
			int rotOne = index, rotTwo = n-1;
			String out = "";
			
			for( Character c : z.toCharArray() )
			{
				
				Byte fK1 = lookup.get( keyOne[rotOne] );
				Byte fK2 = lookup.get( keyTwo[rotTwo] );
				Byte bOut = lookup.get( c );
				bOut = (byte) (bOut ^ fK1 ^ fK2);
				
				for( Entry<Character, Byte> b : lookup.entrySet() )
					if( b.getValue() == bOut )
						out += b.getKey();

				rotOne = ( rotOne + 1 ) % 14;
				rotTwo = ( rotTwo + 1 ) % 4;
				
			}
			
			System.out.println("Output line " + (2*linesNums + 2) + ": " +  out );
			
		}
		
	}
	
	
	public String delta(String str){
		String out = "";
		
		for(int i=0;i<str.length()-1;i++){
			char c1 = str.charAt(i);
			char c2 = str.charAt(i+1);
			
			byte b1 = lookup.get(c1);
			byte b2 = lookup.get(c2);
			
			byte bOut = (byte)(b1^b2);
			
			for( Entry<Character, Byte> b : lookup.entrySet() )
				if( b.getValue() == bOut )
					out += b.getKey();

		}
		
		return out;
	}
	
	public static void main( String[] arrgs )
	{
		keyOne[0] = 'A';
		keyOne[1] = 'B';
		keyOne[2] = 'C';
		keyOne[3] = 'D';
		keyOne[4] = 'E';
		keyOne[5] = 'F';
		keyOne[6] = 'G';
		keyOne[7] = 'H';
		keyOne[8] = 'I';
		keyOne[9] = 'J';
		keyOne[10] = 'K';
		keyOne[11] = 'L';
		keyOne[12] = 'M';
		keyOne[13] = 'N';
		keyTwo[0] = 'A';
		keyTwo[1] = 'A';
		keyTwo[2] = 'B';
		keyTwo[3] = 'B';
		
		dKs[0]= "GQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[1]= "QU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[2]= "U3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[3]= "3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[4]= "NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[5]= "QCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[6]= "CLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[7]= "LF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[8]= "F9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[9]= "9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[10]= "XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[11]= "CTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[12]= "TKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		dKs[13]= "KGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQCLF9XCTKGQU3NQ";
		
		new LorenzDecode().start();
	}
	
}
