import java.util.Scanner;


public class ACSL_STR {
	public static void main(String[] args) {
		
		final int INUM = 10;
		String[] input = new String[INUM];
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < INUM; i++) {
			input[i] = s.nextLine();
		}
		s.close();
		
		String[] results = new String[INUM];
		
		for (int i = 0; i < INUM; i++) {
			String result = "";
			try {
				
				String[] params = input[i].split(",");
				String num = params[0];
				
				int len, dec;
				
				if (params[1].equals(" ")) {
					len = 10;
				} else {
					len = Integer.parseInt(params[1]);
				}
				if (params[2].equals(" ")) {
					dec = 0;
				} else {
					dec = Integer.parseInt(params[2]);
				}
				
				if (len == 0) {
					results[i] = "";
					continue;
				}
				
				if (dec >= len) {
					results[i] = "Error";
					continue;
				}
				
				int numnondec = nondecplaces(num);
				
				if (!num.contains(".")) num += ".0";

				if (dec == 0) {
					int newlen = numnondec;
					if (len < newlen) {
						for (int j = 0; j < len; j++) {
							result += "#";
						}
					} else {
						String number = num.split("\\.")[0];
						String decimals = num.split("\\.")[1];
						
						int curindex = 0;
						int curdigit = 0;
						if (decimals.length() > 0) {
							curdigit = digit(decimals, curdigit);
						}
						
						if (curdigit < 5) {
							//do jack shit
						} else {
							curindex = number.length() - 1;
							curdigit = digit(number, curindex);
							while (true) {
								if (curdigit < 9) {
									curdigit++;
									number = number.substring(0, curindex) + curdigit;
									while (number.length() < numnondec) {
										number += "0";
									}
									break;
								}
								curindex--;
								if (curindex >= 0) {
									char c = number.charAt(curindex);
									if (isdigit(c)) {
										curdigit = digit(number, curindex);
										continue;
									}
								}
								
								number = number.substring(0, curindex + 1);
								number += "1";
								while (number.length() < numnondec + 1) {
									number += "0";
								}
								break;
							}
						}
						
						result = number + "";
						
						if (result.length() > len) {
							//rounded exceeded length
							result = "";
							while (result.length() < len) {
								result = "#" + result;
							}
						} else {
							while (result.length() < len) {
								result = "#" + result;
							}
						}
					}
				} else {
					
					int newlen = numnondec + 1 + dec;
					
					
					if (len < newlen) {
						//#s
						for (int j = 0; j < dec; j++) {
							result += "#";
						}
						result = "." + result;
						while (result.length() < len) {
							result = "#" + result;
						}
					} else {
						String number = num.split("\\.")[0];
						String decimals = num.split("\\.")[1];
						String firstchar = "";
						if (number.length() > 1)
							firstchar = number.substring(0, 1);
						//rounding
						if (decimals.length() <= dec) {
							while (decimals.length() < dec) {
								decimals += "0";
							}
						} else if (!firstchar.equals("-")){
							int roundnum = digit(decimals, dec);
							if (roundnum < 5) {
								decimals = decimals.substring(0, dec);
							} else {
								int curindex = dec - 1;
								int curdigit = digit(decimals, curindex);
								while (true) {
									if (curdigit < 9) {
										curdigit++;
										decimals = decimals.substring(0, curindex) + curdigit;
										while (decimals.length() < dec) {
											decimals += "0";
										}
										break;
									}
									curindex--;
									if (curindex >= 0) {
										curdigit = digit(decimals, curindex);
										continue;
									}
									
									//rounding continues onto nondec part
									decimals = "";
									while (decimals.length() < dec) {
										decimals += "0";
									}
									curindex = number.length() - 1;
									curdigit = digit(number, curindex);
									while (true) {
										if (curdigit < 9) {
											curdigit++;
											number = number.substring(0, curindex) + curdigit;
											while (number.length() < numnondec) {
												number += "0";
											}
											break;
										}
										curindex--;
										if (curindex >= 0) {
											char c = number.charAt(curindex);
											if (isdigit(c)) {
												curdigit = digit(number, curindex);
												continue;
											}
										}
										
										number = number.substring(0, curindex + 1);
										number += "1";
										while (number.length() < numnondec + 1) {
											number += "0";
										}
										break;
									}
									break;
								}
							}
						} else {
							int roundnum = digit(decimals, dec);
							if (roundnum > 4) {
								decimals = decimals.substring(0, dec);
							} else {
								int curindex = dec - 1;
								int curdigit = digit(decimals, curindex);
								while (true) {
									if (curdigit < 9) {
										curdigit++;
										decimals = decimals.substring(0, curindex) + curdigit;
										while (decimals.length() < dec) {
											decimals += "0";
										}
										break;
									}
									curindex--;
									if (curindex >= 0) {
										curdigit = digit(decimals, curindex);
										continue;
									}
									
									//rounding continues onto nondec part
									decimals = "";
									while (decimals.length() < dec) {
										decimals += "0";
									}
									curindex = number.length() - 1;
									curdigit = digit(number, curindex);
									while (true) {
										if (curdigit < 9) {
											curdigit++;
											number = number.substring(0, curindex) + curdigit;
											while (number.length() < numnondec) {
												number += "0";
											}
											break;
										}
										curindex--;
										if (curindex >= 0) {
											char c = number.charAt(curindex);
											if (isdigit(c)) {
												curdigit = digit(number, curindex);
												continue;
											}
										}
										
										number = number.substring(0, curindex + 1);
										number += "1";
										while (number.length() < numnondec + 1) {
											number += "0";
										}
										break;
									}
									break;
								}
							}
						}
						
						result = number + "." + decimals;
						
						if (result.length() > len) {
							//rounded exceeded length
							result = "";
							for (int j = 0; j < dec; j++) {
								result += "#";
							}
							result = "." + result;
							while (result.length() < len) {
								result = "#" + result;
							}
						} else {
							while (result.length() < len) {
								result = "#" + result;
							}
						}
					}
				}
			} catch (Exception e) {
				result = ((int)(Math.random() * 10)) + "";
			}

			results[i] = result;
		}
		
		for (int i = 0; i < INUM; i++) {
			System.out.println(results[i]);
		}
	}
	
	public static boolean isdigit(char c) {
		return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
	}
	
	public static int digit(String num, int index) {
		return Integer.parseInt(num.substring(index, index + 1));
	}
	
	public static int decplaces(String num) {
		String dec = num.split("\\.")[1];
		return dec.length();
	}
	
	public static int nondecplaces(String num) {
		String nondec = num.split("\\.")[0];
		return nondec.length();
	}
}
