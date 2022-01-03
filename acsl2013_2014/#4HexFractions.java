import java.io.File;
import java.util.*;
import static java.lang.System.*;
public class HexFractions {
	static Scanner sc; static void pl(){System.out.println();} static void pl(Object o){System.out.println(o);} static int pi(String s){return Integer.parseInt(s);} static String nl(){return sc.nextLine();} static void pt(Object o){System.out.print(o);}
	public static void main(String[] args) throws Exception{
		sc = new Scanner(new File("Hex.dat"));
//		sc = new Scanner(in);
		int set = 1;
		while(sc.hasNextLine()){
			out.printf("%2d. ",set);
		try{
			double num = Double.parseDouble(nl());
			int plc = 1;
			String hex = "";
			while(num > 0 && hex.length() < 15){
				int quot = (int)(num*Math.pow(16, plc));
				if(quot < 10)	hex += String.valueOf(quot);
				else			hex += ""+(char)(65+(quot%10));
				num -= quot/Math.pow(16, plc);
				plc++;
			}
			if(set <= 5) 
			{
				if(hex.length() >= 10)
					pl("."+hex.substring(0,10));
				else
					pl("."+hex);
			}
				
			if(set>=6){
				TreeSet<String> hi = new TreeSet<String>();
				o:for(int i = 0; i < hex.length(); i++){
					for(int j = hex.length()-1; j >= i+1; j--){
						String check = hex.substring(i, j);
						String omg = hex.substring(0,i);
						int count = 0;
						while(omg.length() < hex.length()){
							omg+= check;
							count++;
						}
						if(count == 2) continue;
						String stuff = omg.substring(0,omg.length()-check.length());
						if( stuff.length() > check.length() && stuff.equals(hex.substring(0,stuff.length()))){
							hi.add(hex.substring(0,i)+check);					
						}
					}
				}
				if(!hi.isEmpty())
					pl("."+hi.first());
				else if(hex.length() <= 10)
					pl("."+hex);
				else pl("."+hex.substring(0,10));
			}
		}
		catch(Exception e){
			out.println("Exception.");
		}set++;
		}

	}
}
