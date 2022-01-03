import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ACSLKenKen {
	static Scanner sc; static void pl(){System.out.println();} static void pl(Object o){System.out.println(o);} static int pi(String s){return Integer.parseInt(s);} static String nl(){return sc.nextLine();} static void pt(Object o){System.out.print(o);}
	public static void main(String[] args) throws Exception{
		sc = new Scanner(new File("kenken.dat"));
		
		permute("123", ""); permuteGrid3(); perms.clear();
		permute("1234", ""); permuteGrid4(); perms.clear();
		
		String input = "";
		ArrayList<String> plus = new ArrayList<>();
		ArrayList<String> hash = new ArrayList<>();
		int start = 0;
		String[] index = null;
		
		// 3x3
		
		try{
			input = nl().substring(3).replaceAll(",", "");
			input = input.substring(input.indexOf(" "));
			index = nl().substring(3).split(", ");

			for(int i=0; i<input.length(); i++){
				if(input.charAt(i) == '+'){
					plus.add(input.substring(start, i+1).trim());
					start = i+1;
				}else if(input.charAt(i) == '#'){
					hash.add(input.substring(start, i+1).trim());
					start = i+1;
				}
			}
//			System.out.println("plus: "+plus);
//			System.out.println("hash: "+hash);
			
			for(int i=grid3.size()-1; i>=0; i--){
				for(int j=0; j<hash.size(); j++){
					int left = pi(hash.get(j).split("\\s+")[0])-1;
					char right = hash.get(j).split("\\s+")[1].charAt(0);
					if(grid3.get(i).charAt(left) != right){
						grid3.remove(i);
						break;
					}
				}
			}

			for(int i=grid3.size()-1; i>=0; i--){
				boolean flag = true;
				for(int j=0; j<plus.size(); j++){
					String[] arr = plus.get(j).split("\\s+");
					adds( pi(arr[arr.length-1].substring(0, arr[arr.length-1].length()-1)), arr.length-1, "" );
					boolean yay = false;
					for(String a : adds){
						boolean work = true;
						for(int b=0; b<arr.length-1; b++){
							if( grid3.get(i).charAt(pi(arr[b])-1) != a.charAt(b) ){
								work = false;
							}
						}
						if(work){
							yay = true;
							break;
						}
					}
					if(!yay){
						flag = false;
					}
					adds.clear();
				}
				if(!flag){
					grid3.remove(i);
				}
			}
//			pl(grid3);
			
			for(int i=0; i<index.length; i++){
				pl(i+1+". " + grid3.get(0).charAt( pi(index[i])-1 ));
			}
		}catch(Exception e){}
		
		
		//4x4
		
		input = nl().substring(3).replaceAll(",", "");
		input = input.substring(input.indexOf(" "));
		index = nl().substring(3).split(", ");
		
		plus.clear();
		hash.clear();
		
		start = 0;
		for(int i=0; i<input.length(); i++){
			if(input.charAt(i) == '+'){
				plus.add(input.substring(start, i+1).trim());
				start = i+1;
			}else if(input.charAt(i) == '#'){
				hash.add(input.substring(start, i+1).trim());
				start = i+1;
			}
		}
//		System.out.println("plus: "+plus);
//		System.out.println("hash: "+hash);
		
		

		for(int i=grid4.size()-1; i>=0; i--){
			for(int j=0; j<hash.size(); j++){
				int left = pi(hash.get(j).split("\\s+")[0])-1;
				char right = hash.get(j).split("\\s+")[1].charAt(0);
				if(grid4.get(i).charAt(left) != right){
					grid4.remove(i);
					break;
				}
			}
		}

		for(int i=grid4.size()-1; i>=0; i--){
			boolean flag = true;
			for(int j=0; j<plus.size(); j++){
				String[] arr = plus.get(j).split("\\s+");
				adds( pi(arr[arr.length-1].substring(0, arr[arr.length-1].length()-1)), arr.length-1, "" );
				boolean yay = false;
				for(String a : adds){
					boolean work = true;
					for(int b=0; b<arr.length-1; b++){
						if( grid4.get(i).charAt(pi(arr[b])-1) != a.charAt(b) ){
							work = false;
						}
					}
					if(work){
						yay = true;
						break;
					}
				}
				if(!yay){
					flag = false;
				}
				adds.clear();
			}
			if(!flag){
				grid4.remove(i);
			}
		}
//		pl(grid4);
		
		for(int i=0; i<index.length; i++){
			pl(i+6+". " + grid4.get(0).charAt( pi(index[i])-1 ));
		}
		
		
	}
	
	static TreeSet<String> perms = new TreeSet<>();
	static ArrayList<String> grid3 = new ArrayList<>();
	static ArrayList<String> grid4 = new ArrayList<>();
	static TreeSet<String> adds = new TreeSet<>();
	
	static void adds(int n, int size, String s){
		if(n<0){
			return;
		}
		if(n==0 && s.length()==size){
			adds.add(s);
			return;
		}
		for(int i=1; i<=n; i++){
			adds(n-i, size, s+i);
		}
	}
	
	static void permute(String s, String t){
		if(s.length()==0){
			perms.add(t);
			return;
		}
		for(int i=0; i<s.length(); i++){
			permute(s.substring(0,i) + s.substring(i+1), t + s.charAt(i));
		}	
	}
	
	static void permuteGrid3(){
		for(String a : perms){
			for(String b : perms){
				for(String c : perms){
					boolean unique = true;
					for(int i=0; i<a.length(); i++){
						if( a.charAt(i)==b.charAt(i) || a.charAt(i)==c.charAt(i) || b.charAt(i)==c.charAt(i) ) unique = false;
					}
					if(unique) grid3.add(a+b+c);
				}	
			}
		}
	}
	
	static void permuteGrid4(){
		for(String a : perms){
			for(String b : perms){
				for(String c : perms){
					for(String d : perms){
						boolean unique = true;
						for(int i=0; i<a.length(); i++){
							if( a.charAt(i)==b.charAt(i) || a.charAt(i)==c.charAt(i) || a.charAt(i)==d.charAt(i) || b.charAt(i)==c.charAt(i) || b.charAt(i)==d.charAt(i) || c.charAt(i)==d.charAt(i) ) unique = false;
						}
						if(unique) grid4.add(a+b+c+d);
					}
				}	
			}
		}
	}
	
	static void plg(String s){
		System.out.println(s.substring(0, 3));
		System.out.println(s.substring(3, 6));
		System.out.println(s.substring(6, 9)+"\n");
	}
}


/*
1. 5, 1, 4, 3+, 2, 3#, 3, 6, 5+, 5, 8, 9, 4+, 7, 3#
2. 1, 4, 5, 6, 9
3. 8, 1, 5, 9, 9+, 13, 1#, 2, 1#, 6, 10, 14, 9+, 3, 7, 8, 6+, 4, 2#, 12, 4#, 11, 15, 16, 8+
4. 1, 3, 10, 11, 16
*/
