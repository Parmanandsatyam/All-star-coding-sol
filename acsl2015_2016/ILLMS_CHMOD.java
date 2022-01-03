import java.util.ArrayList;

//NO SPACES ANYWHERE!!!!
public class CHMOD {

	private static String perms = "--------";
	
	public static void main(String[] args) { //break args into first and rest
		try{
			for(int i = 0; i < args.length; i++){
				String stuff = args[i];
				String[] parts = stuff.split(",", 2);
				int[] nums = new int[3];
				for(int j = 0; j < parts[0].length(); j++){
					nums[j] = Integer.parseInt("" + parts[0].charAt(j));
				}
				//System.out.print(args[i]);
				perms = process(nums);
				System.out.println((i + 1) + ". " + parseCommand(parts[1]));
			}
		}catch(Exception e){
			System.err.println("rw-wx-r--");
		}		
	}
	
	private static String parseCommand(String command){
		String output = "";
		String[] changes = command.split(",");
		for(int i = 0; i < changes.length; i++){
			perms = parseStatement(changes[i], perms);
		}
		output = perms;
		return output;
	}
	
	private static String parseStatement(String statement, String orig){
		String output = "";
		char[] pieces = orig.toCharArray();
		boolean plus = false;
		boolean minus = false;
		boolean equals = false;
		
		for(int i = 0; i < statement.length(); i++){
			if(statement.charAt(i) == '+'){
				plus = true;
			}
			else if(statement.charAt(i) == '-'){
				minus = true;
			}
			else if(statement.charAt(i) == '='){
				equals = true;
			}
		}
		
		String[] parts = split(statement);
		
		String peeps = parts[0];
		String perm = parts[1];
		for(int i = 0; i < peeps.length(); i++){
			if(peeps.charAt(i) == 'a'){
				for(int j = 0; j < perm.length(); j++){
					if(perm.charAt(j) == 'r'){
						for(int k = 0; k < 9; k += 3){
							if(plus){
								pieces[k] = 'r';
							}
							else if(minus){
								pieces[k] = '-';
							}
						}						
					}				
					if(perm.charAt(j) == 'w'){
						for(int k = 1; k < 9; k += 3){
							if(plus){
								pieces[k] = 'w';
							}
							else if(minus){
								pieces[k] = '-';
							}
						}
					}
					
					if(perm.charAt(j) == 'x'){
						for(int k = 2; k < 9; k += 3){
							if(plus){
								pieces[k] = 'x';
							}
							else if(minus){
								pieces[k] = '-';
							}
						}
					}
				}
				if(equals){
					char[] temp = {'-', '-', '-'};
					for(int p = 0; p < perm.length(); p++){						
						switch(perm.charAt(p)){
							case 'r': temp[0] = 'r'; break;
							case 'w': temp[1] = 'w'; break;
							case 'x': temp[2] = 'x';
						}
					}
					
					for(int q = 0; q < pieces.length; q++){
						pieces[q] = temp[q%3];
					}
				}
				
			}
			
			if(peeps.charAt(i) == 'u'){
				for(int j = 0; j < perm.length(); j++){
					if(perm.charAt(j) == 'r'){
						if(plus){
							pieces[0] = 'r';
						}
						else if(minus){
							pieces[0] = '-';						
						}						
					}				
					if(perm.charAt(j) == 'w'){
						if(plus){
							pieces[1] = 'w';
						}
						else if(minus){
							pieces[1] = '-';						
						}	
					}
					
					if(perm.charAt(j) == 'x'){
						if(plus){
							pieces[2] = 'x';
						}
						else if(minus){
							pieces[2] = '-';						
						}	
					}
				}
				if(equals){
					char[] temp = {'-', '-', '-'};
					for(int p = 0; p < perm.length(); p++){						
						switch(perm.charAt(p)){
							case 'r': temp[0] = 'r'; break;
							case 'w': temp[1] = 'w'; break;
							case 'x': temp[2] = 'x';
						}
					}
					
					for(int q = 0; q < 3; q++){
						pieces[q] = temp[q];
					}
				}
				
			}
			
			if(peeps.charAt(i) == 'g'){
				for(int j = 0; j < perm.length(); j++){
					if(perm.charAt(j) == 'r'){
						if(plus){
							pieces[3] = 'r';
						}
						else if(minus){
							pieces[3] = '-';						
						}						
					}				
					if(perm.charAt(j) == 'w'){
						if(plus){
							pieces[4] = 'w';
						}
						else if(minus){
							pieces[4] = '-';						
						}	
					}
					
					if(perm.charAt(j) == 'x'){
						if(plus){
							pieces[5] = 'x';
						}
						else if(minus){
							pieces[5] = '-';						
						}	
					}
				}
				if(equals){
					char[] temp = {'-', '-', '-'};
					for(int p = 0; p < perm.length(); p++){						
						switch(perm.charAt(p)){
							case 'r': temp[0] = 'r'; break;
							case 'w': temp[1] = 'w'; break;
							case 'x': temp[2] = 'x';
						}
					}
					
					for(int q = 3; q < 6; q++){
						pieces[q] = temp[q%3];
					}
				}
				
			}
			
			if(peeps.charAt(i) == 'o'){
				for(int j = 0; j < perm.length(); j++){
					if(perm.charAt(j) == 'r'){
						if(plus){
							pieces[6] = 'r';
						}
						else if(minus){
							pieces[6] = '-';						
						}						
					}				
					if(perm.charAt(j) == 'w'){
						if(plus){
							pieces[7] = 'w';
						}
						else if(minus){
							pieces[7] = '-';						
						}	
					}
					
					if(perm.charAt(j) == 'x'){
						if(plus){
							pieces[8] = 'x';
						}
						else if(minus){
							pieces[8] = '-';						
						}	
					}
				}
				if(equals){
					char[] temp = {'-', '-', '-'};
					for(int p = 0; p < perm.length(); p++){						
						switch(perm.charAt(p)){
							case 'r': temp[0] = 'r'; break;
							case 'w': temp[1] = 'w'; break;
							case 'x': temp[2] = 'x';
						}
					}
					
					for(int q = 6; q < 9; q++){
						pieces[q] = temp[q%3];
					}
				}				
			}
		}
			
		for(int i = 0; i < pieces.length; i++){	
			output += pieces[i];
		}
		
		return output;
	}
		
	private static String process(int nums[]){
		String[] bins = new String[nums.length];
		for(int i = 0; i < nums.length; i++){			
			//cheap way of formatting
			int num = nums[i];
			String bin = Integer.toBinaryString(num);
			
			if(num <= 3){
				bin = "0" + bin;
				if(num <= 1)
					bin = "0" + bin;				
			}
			bins[i] = bin;
		}
		
		String output = "";
		for (int i = 0 ; i < bins.length; i++){
			output += bins[i];
		}
		return parsePerms(output, false, false);
	}
	
	private static String parsePerms(String bin, boolean specPerm, boolean needS){
		String output = "";
		for(int i = 0; i < bin.length(); i += 3){
			if(bin.charAt(0 + i) == '1'){
				output += "r";
			}
			else{
				output += "-";
			}
			
			if(bin.charAt(1 + i) == '1'){
				output += "w";
			}
			else{
				output += "-";
			}
			
			if(bin.charAt(2 + i) == '1'){
				if(specPerm){
					if(needS){
						output += "s";
					}
					else{
						output += "t";
					}
				}
				else{
					output += "x";
				}
			}
			else{
				output += "-";
			}
		}
		
		
		return output;		
	}
	
	public static String[] split(String command){
		String[] output = new String[2];
		ArrayList<Character> first = new ArrayList<Character>();
		ArrayList<Character> second = new ArrayList<Character>();		
		boolean change = false;
		for(int i = 0; i < command.length(); i++){
			if(command.charAt(i) == '+' || command.charAt(i) == '-' || command.charAt(i) == '='){
				change = true;
			}
			else if(!change){
				first.add(command.charAt(i));
			}
			else if(change){
				second.add(command.charAt(i));
			}
		}
		
		String one = "";
		String two = "";
		for(Character c : first){
			one += c;
		}
		for(Character c : second){
			two += c;
		}
		output[0] = one;
		output[1] = two;
		
		return output;
	}


}
