import java.util.ArrayList;
import java.util.Scanner;


public class Ternary {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		for (int k=0;k<5;k++){
			String raw=s.next();
			String[] input=raw.split(",");
			if (input[0].compareTo("4")==0){		//4 start
				ArrayList<Gate> gates=new ArrayList<Gate>();
				String Ainput="";
				String Binput="";
				String Cinput="";
				String Dinput="";
				for (int i=0;i<4;i++){
					if(input[i*2+1].substring(0,1).compareTo("A")==0){
						gates.add(new AGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("B")==0){
						gates.add(new BGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("C")==0){
						gates.add(new CGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("D")==0){
						gates.add(new DGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
				}

				for(int i=0;i<gates.size();i++){
					Ainput=Ainput+gates.get(i).fillA();
					Binput=Binput+gates.get(i).fillB();
					Cinput=Cinput+gates.get(i).fillC();
					Dinput=Dinput+gates.get(i).fillD();
				}
				AGate SecondAGate=new AGate(Ainput,"");
				BGate SecondBGate=new BGate(Binput,"");
				CGate SecondCGate=new CGate(Cinput,"");
				DGate SecondDGate=new DGate(Dinput,"");
				String finalInput=SecondAGate.returnValue+SecondBGate.returnValue+SecondCGate.returnValue+SecondDGate.returnValue;
				Gate FinalGate;
				if (input[9].compareTo("A")==0){
					FinalGate=new AGate(finalInput,"");
				}
				else if (input[9].compareTo("B")==0){
					FinalGate=new BGate(finalInput,"");
				}
				else if (input[9].compareTo("C")==0){
					FinalGate=new CGate(finalInput,"");
				}
				else{
					FinalGate=new DGate(finalInput,"");
				}
				System.out.println(finalInput);
				System.out.println(FinalGate.returnValue);
			}
			else if ((input[0].compareTo("3")==0) && (input[2].length()>1)){
				ArrayList<Gate> gates=new ArrayList<Gate>();
				String Ainput="";
				String Binput="";
				String Cinput="";
				String Dinput="";
				for (int i=0;i<3;i++){
					if(input[i*2+1].substring(0,1).compareTo("A")==0){
						gates.add(new AGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("B")==0){
						gates.add(new BGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("C")==0){
						gates.add(new CGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("D")==0){
						gates.add(new DGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
				}

				for(int i=0;i<gates.size();i++){
					Ainput=Ainput+gates.get(i).fillA();
					Binput=Binput+gates.get(i).fillB();
					Cinput=Cinput+gates.get(i).fillC();
					Dinput=Dinput+gates.get(i).fillD();
				}
				AGate SecondAGate=new AGate(Ainput,"");
				BGate SecondBGate=new BGate(Binput,"");
				CGate SecondCGate=new CGate(Cinput,"");
				DGate SecondDGate=new DGate(Dinput,"");
				String finalInput=SecondAGate.returnValue+SecondBGate.returnValue+SecondCGate.returnValue+SecondDGate.returnValue;
				Gate FinalGate;
				if (input[7].compareTo("A")==0){
					FinalGate=new AGate(finalInput,"");
				}
				else if (input[7].compareTo("B")==0){
					FinalGate=new BGate(finalInput,"");
				}
				else if (input[7].compareTo("C")==0){
					FinalGate=new CGate(finalInput,"");
				}
				else{
					FinalGate=new DGate(finalInput,"");
				}
				System.out.println(finalInput);
				System.out.println(FinalGate.returnValue);
			}
			else{		//3 start	4,A7,B,B3,BCD,C5,BCD,D0,CD,C   3,A7,ACD,B6,ACD,C5,ACD,B   3,A7,D,B3,D,C5,D,D
				ArrayList<Gate> gates=new ArrayList<Gate>();
				String Ainput="";
				String Binput="";
				String Cinput="";
				String Dinput="";
				for (int i=0;i<3;i++){
					if(input[i*2+1].substring(0,1).compareTo("A")==0){
						gates.add(new AGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("B")==0){
						gates.add(new BGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("C")==0){
						gates.add(new CGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
					else if(input[i*2+1].substring(0,1).compareTo("D")==0){
						gates.add(new DGate(OctToBin(input[i*2+1].substring(1)),input[i*2+2]));
					}
				}
				//
				//			for(int i=0;i<gates.size();i++){
				//				Ainput=Ainput+gates.get(i).fillA();
				//				Binput=Binput+gates.get(i).fillB();
				//				Cinput=Cinput+gates.get(i).fillC();
				//				Dinput=Dinput+gates.get(i).fillD();
				//			}
				//			AGate SecondAGate=new AGate(Ainput,"");
				//			BGate SecondBGate=new BGate(Binput,"");
				//			CGate SecondCGate=new CGate(Cinput,"");
				//			DGate SecondDGate=new DGate(Dinput,"");
				String finalInput=gates.get(0).returnValue+gates.get(1).returnValue+gates.get(2).returnValue;//+SecondDGate.returnValue;
				Gate FinalGate;
				if (input[7].compareTo("A")==0){
					FinalGate=new AGate(finalInput,"");
				}
				else if (input[7].compareTo("B")==0){
					FinalGate=new BGate(finalInput,"");
				}
				else if (input[7].compareTo("C")==0){
					FinalGate=new CGate(finalInput,"");
				}
				else{
					FinalGate=new DGate(finalInput,"");
				}
				System.out.println(finalInput);
				System.out.println(FinalGate.returnValue);
			}
		}

	}
	
	public static String OctToBin(String octal){
		if (octal.compareTo("0")==0){
			return("000");
		}
		else if (octal.compareTo("1")==0){
			return("001");
		}
		else if (octal.compareTo("2")==0){
			return("010");
		}
		else if (octal.compareTo("3")==0){
			return("011");
		}
		else if (octal.compareTo("4")==0){
			return("100");
		}
		else if (octal.compareTo("5")==0){
			return("101");
		}
		else if (octal.compareTo("6")==0){
			return("110");
		}
		return("111");
	}
	
	

}
