//ACSL All-Stars Contest 2015
//TJHSST Junior Team
//ACSL Spelling

import java.io.*;

public class Spelling {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		
		for(int k = 0; k < 5; k++) {
			System.out.println((k+1)+". "+plural(f.readLine().toLowerCase()));
		}
		
		for(int k = 0; k < 5; k++) {
			String[] s = f.readLine().split(",");
			System.out.println((k+6)+". "+suffix(s[0].trim(), s[1].trim()));
		}
	}
	
	private static String suffix(String s, String suffix) {
		if(endsWith(s, "e") && startsWith(suffix, true, ""))
			return s.substring(0, s.length() - 1) + suffix;
		if(endsWith(s, "y") && startsWith(suffix, "i"))
			return s + suffix;
		if(endsWith(s, "y"))
			return s.substring(0, s.length() - 1) + "i" + suffix;
		if(endsWith(s.substring(0, s.length() - 2), false, "") && endsWith(s.substring(0, s.length() - 1), true, "") && endsWith(s, false, ""))
			return s + s.substring(s.length() -1) + suffix;
		return s + suffix;
	}
	
	private static boolean startsWith(String s, String start) {
		return s.substring(0, start.length()).equals(start);
	}
	
	private static boolean startsWith(String s, String[] starts) {
		for(int k = 0; k < starts.length; k++) {
			if(startsWith(s, starts[k])) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean startsWith(String s, boolean b, String start) {
		if(b) {
			return startsWith(s, new String[] {"a" + start, "e" + start, "i" + start, "o" + start, "u" + start});
		} else {
			return startsWith(s, new String[] {"b" + start, "c" + start, "d" + start, "f" + start, "g" + start, "h" + start, "j" + start, "k" + start, "l" + start, "m" + start, "n" + start, "p" + start, "q" + start, "r" + start, "s" + start, "t" + start, "v" + start, "w" + start, "x" + start, "y" + start, "z" + start});
		}
	}
	
	private static String plural(String s) {
		if(endsWith(s, new String[] {"ch", "sh", "s", "x", "z"}))
			return s + "es";
		if(endsWith(s, true, "y"))
			return s + "s";
		if(endsWith(s, false, "y"))
			return s.substring(0, s.length() - 1) + "ies";
		if(endsWith(s, "f"))
			return s.substring(0, s.length() - 1) + "ves";
		if(endsWith(s, "fe"))
			return s.substring(0, s.length() - 2) + "ves";
		if(endsWith(s, true, "o"))
			return s + "s";
		if(endsWith(s, false, "o"))
			return s + "es";
		return s + "s";
	}
	
	private static boolean endsWith(String s, String end) {
		return s.substring(s.length() - end.length()).equals(end);
	}
	
	private static boolean endsWith(String s, String[] ends) {
		for(int k = 0; k < ends.length; k++) {
			if(endsWith(s, ends[k])) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean endsWith(String s, boolean b, String end) {
		if(b) {
			return endsWith(s, new String[] {"a" + end, "e" + end, "i" + end, "o" + end, "u" + end});
		} else {
			return endsWith(s, new String[] {"b" + end, "c" + end, "d" + end, "f" + end, "g" + end, "h" + end, "j" + end, "k" + end, "l" + end, "m" + end, "n" + end, "p" + end, "q" + end, "r" + end, "s" + end, "t" + end, "v" + end, "w" + end, "x" + end, "y" + end, "z" + end});
		}
	}
}
