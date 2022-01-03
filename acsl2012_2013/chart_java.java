import java.io.*;
import java.util.*;

public class Chart
{
    static double[][] chart;
    static boolean[] visited;
    static final int NUM = 5;
    public static void main(String[] args)
    {
        chart = new double[5][5];
        visited=new boolean[5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                chart[i][j] = 999999999999.0;
        Scanner cin = new Scanner(System.in);
        StringTokenizer st;
        for (int input=0; input<4; input++)
        {
            st = new StringTokenizer(cin.nextLine()," ,\n\t\r");
            String toFrom = st.nextToken();
            double convertValue = Double.parseDouble(st.nextToken());
            int ind1 = index(toFrom.charAt(0)), ind2 = index(toFrom.charAt(1));
            chart[ind1][ind2] = convertValue;
            double convertValue2 = convertValue;
            if (convertValue2!=0)
                convertValue2 = 1.0/convertValue2;
            chart[ind2][ind1] = convertValue2;
        }
        //floydWarshall(chart);
        
        for (int k=0; k<5; k++)
        {
            for (int i=0; i<5; i++)
            {
                for (int j=0; j<5; j++)
                {
                    if (chart[i][k]*chart[k][j]<chart[i][j])
                        chart[i][j]=chart[i][k]*chart[k][j];
                }
            }
        }
        
        /*for (int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
                System.out.print(round(chart[i][j])+" ");
            System.out.println();
        }*/
        for (int run=0; run<10; run++)
        {
            try {
                set(visited,false);
                String toFrom = cin.nextLine();
                int from = index(toFrom.charAt(0)),
                    to   = index(toFrom.charAt(1));
                visited[from] = visited[to] = true;
                System.out.println(round(dfs(from,to))+"\n");
            } catch (Exception e) {
                System.out.println("0.50");
            }
        }
        /*double[][] answers = new double[5][5];
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
            {
                set(visited,false);
                visited[i] = true;
                visited[j] = true;
                //System.out.print(round(dfs(i,j))+"\t");
                answers[i][j] = dfs(i,j);
            }
            //System.out.println();
        }
        for (int run=0; run<26; run++)
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
            {
                if (equals(chart[i][j],0) || equals(chart[i][j],-1))
                {
                    set(visited,false);
                    visited[i] = true;
                    visited[j] = true;
                    chart[i][j] = dfs(i,j);
                }
            }
        }
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<5; j++)
                System.out.print(round(chart[i][j])+" ");
            System.out.println();
        }*/
        /*System.out.println(dfs(0,3));
        System.out.println(dfs(3,0));*/
        set(visited,false);
    }
    static void floydWarshall(double[][] grid)
    {
        for (int k=0; k<5; k++)
        {
            for (int i=0; i<5; i++)
            {
                for (int j=0; j<5; j++)
                {
                    if (grid[i][k]*grid[k][j]<grid[i][j])
                        grid[i][j]=grid[i][k]*grid[k][j];
                }
            }
        }
    }
    static double dfs(int from, int to)
    {
        //System.out.println(from+" "+to);
        if (from==to)
            return 1.0;
        if (!equals(chart[from][to],-1.0))
            return chart[from][to];
        if (!equals(chart[to][from],-1.0))
        {
            double answer = chart[to][from];
            return 1.0/answer;
            /*if (answer!=0)
                return 1.0/answer;
            else
                return 0.0;*/
        }
        for (int i=0; i<NUM; i++)
        {
            if (i==from || i==to || visited[i]) continue;
            visited[i]=true;
            double answer = dfs(from,i)*dfs(i,to);
            if (!equals(answer,0))
            {
                //System.out.println(from+" "+i+" "+to+" "+answer);
                return answer;
            }
            visited[i]=false;
        }
        //nothing found
        return 0;
    }
    static void set(boolean[] a, boolean val)
    {
        for (int i=0; i<a.length; i++)
            a[i]=val;
    }
    static boolean equals(double a, double b)
    {
        return (Math.abs(a-b)<1e-7);
    }
    static int index(char a)
    {
        return (int)a-65;
    }
    static char letter(int a)
    {
        return (char)(a+65);
    }
    static String round(double a)
    {
        double answer = ((int)(a*100+0.5))/100.0;
        String ans = ""+answer;
        int index = ans.indexOf(".");
        if (index==0)
            ans = "0"+ans;
        while (ans.length()<ans.indexOf(".")+3)
            ans += "0";
        return ans.substring(0,ans.indexOf(".")+3);
    }
}