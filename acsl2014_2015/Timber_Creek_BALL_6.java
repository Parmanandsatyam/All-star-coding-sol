import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BALL {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0");
		
		Player[] teamX = new Player[5];
		for (int i = 0; i < 5; i++) {
			String[] parts = scan.nextLine().split("\\,\\s");
			int shots = (parts[1].charAt(4) > '9' ? parts[1].charAt(4) - 55 : parts[1].charAt(4)- 48);
			teamX[i] = new Player(parts[0],
					parts[1].charAt(0)- 48,
					parts[1].charAt(1)- 48,
					parts[1].charAt(2)- 48,
					parts[1].charAt(3)- 48,
					shots);
		}
		int teamXScore = teamX[0].points + teamX[1].points + teamX[2].points + teamX[3].points + teamX[4].points;
		
		Player[] teamY = new Player[5];
		for (int i = 0; i < 5; i++) {
			String[] parts = scan.nextLine().split("\\,\\s");
			int shots = (parts[1].charAt(4) > '9' ? parts[1].charAt(4) - 55 : parts[1].charAt(4)- 48);
			teamY[i] = new Player(parts[0],
					parts[1].charAt(0)- 48,
					parts[1].charAt(1)- 48,
					parts[1].charAt(2)- 48,
					parts[1].charAt(3)- 48,
					shots);
		}
		int teamYScore = teamY[0].points + teamY[1].points + teamY[2].points + teamY[3].points + teamY[4].points;
		
		System.out.println(df.format(mean(teamXScore, teamYScore)));
		System.out.println(df.format(median(teamX, teamY)));
		System.out.println(highestScorers(teamX, teamY));
		System.out.println(twoHighest(teamX, teamY));
		System.out.println(gameScore(teamXScore, teamYScore));
		System.out.println(fewestPoints(teamX, teamY));
		System.out.println(xHighestPercentage(teamX));
		System.out.println(yLowestPercentage(teamY));
		System.out.println(mostZoneTwoPoints(teamX, teamY));
		System.out.println(mostZoneOnePoints(teamX, teamY));
	}
	
	public static double mean(int teamXScore, int teamYScore) {
		return (teamXScore + teamYScore) / 10;
	}
	
	public static double median(Player[] teamX, Player[] teamY) {
		int[] points = new int[10];
		for (int i = 0; i < 5; i++) {
			points[i] = teamX[i].points;
		}
		for (int i = 0; i < 5; i++) {
			points[i+5] = teamY[i].points;
		}
		Arrays.sort(points);
		return (points[4] + points[5]) / 2;
	}
	
	public static String highestScorers(Player[] teamX, Player[] teamY) {
		int xPoints = 0; int xIndex = 0;
		for (int i = 0; i < 5; i++) {
			if (teamX[i].points > xPoints) {
				xPoints = teamX[i].points;
				xIndex = i;
			}
		}
		int yPoints = 0; int yIndex = 0;
		for (int i = 0; i < 5; i++) {
			if (teamY[i].points > yPoints) {
				yPoints = teamY[i].points;
				yIndex = i;
			}
		}
		return teamX[xIndex].name + " " + teamY[yIndex].name;
	}
	
	public static String twoHighest(Player[] teamX, Player[] teamY) {
		String highestName = "";
		int highestPoints = -1;
		int highestIndex = -1;
		for (int i = 0; i < 5; i++) {
			if (teamX[i].points > highestPoints) {
				highestName = teamX[i].name;
				highestPoints = teamX[i].points;
				highestIndex = i;
			}
		}
		for (int i = 5; i < 10; i++) {
			if (teamY[i-5].points > highestPoints) {
				highestName = teamY[i-5].name;
				highestPoints = teamY[i-5].points;
				highestIndex = i;
			}
		}

		String secondHighestName = "";
		int secondHighestPoints = -1;
		for (int i = 0; i < 5; i++) {
			if (teamX[i].points > secondHighestPoints && i != highestIndex) {
				secondHighestName = teamX[i].name;
				secondHighestPoints = teamX[i].points;
			}
		}
		for (int i = 5; i < 10; i++) {
			if (teamY[i-5].points > secondHighestPoints && i != highestIndex) {
				secondHighestName = teamY[i-5].name;
				secondHighestPoints = teamY[i-5].points;
			}
		}
		
		return highestName + " " + secondHighestName;
	}
	
	public static String gameScore(int teamXScore, int teamYScore) {
		return (teamXScore > teamYScore ? teamXScore + " " + teamYScore : teamYScore + " " + teamXScore);
	}
	
	public static String fewestPoints(Player[] teamX, Player[] teamY) {
		String fewestName = "";
		int fewestPoints = 65;
		for (int i = 0; i < 5; i++) {
			if (teamX[i].points < fewestPoints) {
				fewestName = teamX[i].name;
				fewestPoints = teamX[i].points;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].points < fewestPoints) {
				fewestName = teamY[i].name;
				fewestPoints = teamY[i].points;
			}
		}
		return fewestName;
	}
	
	public static String xHighestPercentage(Player[] teamX) {
		double highestPercent = 0.0;
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			if (teamX[i].percent > highestPercent) {
				highestPercent = teamX[i].percent;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamX[i].percent == highestPercent) {
				names.add(teamX[i].name);
			}
		}
		String output = "";
		for (int i = 0; i < names.size(); i++) {
			output += names.get(i) + " ";
		}
		output = output.substring(0, output.length()-1);
		return output;
	}
	
	public static String yLowestPercentage(Player[] teamY) {
		double lowestPercent = 101.0;
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			if (teamY[i].percent < lowestPercent) {
				lowestPercent = teamY[i].percent;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].percent == lowestPercent) {
				names.add(teamY[i].name);
			}
		}
		String output = "";
		for (int i = 0; i < names.size(); i++) {
			output += names.get(i) + " ";
		}
		output = output.substring(0, output.length()-1);
		return output;
	}
	
	public static String mostZoneTwoPoints(Player[] teamX, Player[] teamY) {
		int mostPoints = 0;
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			if (teamX[i].zone2 > mostPoints) {
				mostPoints = teamX[i].zone2;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].zone2 > mostPoints) {
				mostPoints = teamY[i].zone2;
			}
		}
		//
		for (int i = 0; i < 5; i++) {
			if (teamX[i].zone2 == mostPoints) {
				names.add(teamX[i].name);
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].zone2 == mostPoints) {
				names.add(teamY[i].name);
			}
		}
		String output = "";
		for (int i = 0; i < names.size(); i++) {
			output += names.get(i) + " ";
		}
		output = output.substring(0, output.length()-1);
		return output;
	}
	
	public static String mostZoneOnePoints(Player[] teamX, Player[] teamY) {
		int mostPoints = 0;
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			if (teamX[i].zone1 > mostPoints) {
				mostPoints = teamX[i].zone1;
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].zone1 > mostPoints) {
				mostPoints = teamY[i].zone1;
			}
		}
		//
		for (int i = 0; i < 5; i++) {
			if (teamX[i].zone1 == mostPoints) {
				names.add(teamX[i].name);
			}
		}
		for (int i = 0; i < 5; i++) {
			if (teamY[i].zone1 == mostPoints) {
				names.add(teamY[i].name);
			}
		}
		String output = "";
		for (int i = 0; i < names.size(); i++) {
			output += names.get(i) + " ";
		}
		output = output.substring(0, output.length()-1);
		return output;
	}
}

class Player{
	String name;
	int zone1;
	int zone2;
	int zone3;
	int zone4;
	int points;
	int shots;
	double percent;
	
	public Player(String name, int zone1, int zone2, int zone3, int zone4,int shots) {
		this.name = name;
		this.zone1 = zone1 * 1;
		this.zone2 = zone2 * 2;
		this.zone3 = zone3 * 3;
		this.zone4 = zone4 * 4;
		this.points = this.zone1 + this.zone2 + this.zone3 + this.zone4;
		this.shots = shots;
		this.percent = (double)this.points / shots;
	}
}