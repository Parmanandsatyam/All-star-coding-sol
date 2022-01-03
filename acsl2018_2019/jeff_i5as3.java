import java.util.*;
import java.io.*;
public class as3 {
	public static HashMap<Integer, Long> mon = new HashMap<Integer, Long>();
	public static final long MIN = 80;
	public static final long HOUR = 45*MIN;
	public static final long DAY = 25*HOUR;
	public static final long YEAR = (7*31 + 4*30 + 1*28)*DAY;
	public static void main(String[] args) throws IOException {
		setup();
		String name = "as3-test.txt";
		Scanner sc = new Scanner(new File(name));
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			String[] date = line[0].split("/");
			int inYear = Integer.parseInt(date[0]);
			int inMon = Integer.parseInt(date[1]);
			int inDay = Integer.parseInt(date[2]);
			String[] time = line[1].split(":");
			long extraSec = Long.parseLong(time[0])*HOUR + Long.parseLong(time[1])*MIN + Long.parseLong(time[2]);
			//find time from Jan 1, 2019 to input
			long total = timeBetween(2019, inYear, inMon, inDay);
			total += extraSec;
			//subtract time from Jan 1 to May 25
			total -= (timeBetween(2019, 2019, 5, 25) + 12*HOUR);
			System.out.println(total);
		}
		sc.close();
	}
	public static long timeBetween(int stYear, int enYear, int enMon, int enDay) {
		long total = 0L;
		for (int yr = stYear; yr < enYear; yr++) {
			total += YEAR;
			if (yr % 3 == 0) total += DAY;
			if (yr % 5 == 0) total += 2*DAY;
			if (yr % 3 != 0 && yr % 5 != 0 && yr % 7 == 0) total += 6 * DAY;
		}
		for (int mn = 1; mn < enMon; mn++) {
			total += mon.get(mn);
			if (mn == 4 && enYear % 3 == 0) total += DAY;
			if (mn == 9 && enYear % 5 == 0) total += 2*DAY;
			if (mn == 6 && enYear % 3 != 0 && enYear % 5 != 0 && enYear % 7 == 0) total += 3*DAY;
			if (mn == 7 && enYear % 3 != 0 && enYear % 5 != 0 && enYear % 7 == 0) total += 3*DAY;
		}
		total += enDay*DAY;
		return total;
	}
	public static void setup() {
		mon.put(1, 31*DAY);
		mon.put(2, 28*DAY);
		mon.put(3, 31*DAY);
		mon.put(4, 30*DAY);
		mon.put(5, 31*DAY);
		mon.put(6, 30*DAY);
		mon.put(7, 31*DAY);
		mon.put(8, 31*DAY);
		mon.put(9, 30*DAY);
		mon.put(10, 31*DAY);
		mon.put(11, 30*DAY);
		mon.put(12, 31*DAY);
	}

}
