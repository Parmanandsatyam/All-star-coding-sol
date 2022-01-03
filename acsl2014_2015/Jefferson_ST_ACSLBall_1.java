//ACSL All-Stars Contest 2015
//TJHSST Junior Team 
//ACSLBall Junior

import java.util.*;

public class ACSLBall {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TreeSet<Pair> set1 = new TreeSet<>(), set2 = new TreeSet<>();
		int total3 = 0;
		String most = "";
		int most1 = Integer.MIN_VALUE;
		String hScorer = "";
		int team1=0, team2=0;
		
		int hScore = Integer.MIN_VALUE;
		for(int x=0; x<8; x++) {
			String t = sc.nextLine();
			String[] t2 = t.split(", ");
			total3 += Integer.parseInt(t2[3]);
			int total = Integer.parseInt(t2[1])+Integer.parseInt(t2[2])+Integer.parseInt(t2[3]);
			if(total>most1) {
				most = t2[0];
				most1 = total;
			}
			int totalScore = Integer.parseInt(t2[1])+Integer.parseInt(t2[2])*2+Integer.parseInt(t2[3])*3;
			if(totalScore>hScore) {
				hScorer = t2[0];
				hScore = totalScore;
			}
			if(x<4) {
				team1 += totalScore;
				set1.add(new Pair(t2[0], totalScore));
			} else {
				team2 += totalScore;
				set2.add(new Pair(t2[0], totalScore));
			}
		}
		System.out.println("1. "+total3);
		System.out.println("2. "+most);
		System.out.println("3. "+hScorer);
		System.out.println("4. "+((team1>team2)?team1:team2));
		Set<Pair> r;
		if(team1<team2) r=set1;
		else r=set2;
		int n=0;
		for(Pair p : r) {
			if(n==2) {
				System.out.println("5. "+p.n);
				break;
			}
			n++;
		}
	}
}
class Pair implements Comparable {
	String n; Integer t;
	public Pair(String s, Integer s2) {
		n=s; t=s2;
	}
	public int compareTo(Pair p) {
		return t-p.t;
	}
	public int compareTo(Object p) {
		return t - ((Pair)p).t;
	}
}