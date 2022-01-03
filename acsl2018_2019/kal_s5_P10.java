import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class P10
{
    public static class Node
    {
        public int identity;
        public int x, y;
        public Node[] children = new Node[4];
        public Node(int id, int i, int j)
        {
            identity = id;
            x = i;
            y = j;
        }

        public void addChild(Node node)
        {
            int q = quad(x, y, node.x, node.y);
            Node child = children[q];
            if(child == null)
            {
                children[q] = node;
            }
            else
            {
                children[q].addChild(node);
            }
        }

        public String toString()
        {
            String s = "";
            for(int i = 0; i < children.length; i++)
            {
                if(children[i] == null)
                {
                    s += "0";
                }
                else
                {
                    s += (children[i].identity + 1);
                }
            }
            return s;
        }
    }

    public static int quad(int px, int py, int x, int y)
    {
        if(x > px && y > py)
        {
            return 0;
        }
        else if(x <= px && y > py)
        {
            return 1;
        }
        else if(x <= px && y <= py)
        {
            return 2;
        }
        else if(x > px && y <= py)
        {
            return 3;
        }
        return 0;
    }

    public static void main(String... args) throws Exception
    {
        try
        {
            Scanner scan = new Scanner(new File("as10-test.txt"));
            String file = "";
            while (scan.hasNextLine())
            {
                file += scan.nextLine() + " ";
            }

            StringTokenizer st = new StringTokenizer(file);

            for (int run = 0; run < 10; run++)
            {
                int K = Integer.parseInt(st.nextToken());
                int nodes = Integer.parseInt(st.nextToken());
                int[] pointX = new int[nodes];
                int[] pointY = new int[nodes];
                for (int i = 0; i < nodes; i++)
                {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    pointX[i] = x;
                    pointY[i] = y;
                }
                try
                {
                    //System.out.println(K + ", " + nodes + ", " + Arrays.toString(pointX) + ", " + Arrays.toString(pointY));
                    Node[] nodeList = new Node[nodes];
                    Node parent = new Node(0, pointX[0], pointY[0]);
                    nodeList[0] = parent;
                    for(int i = 1; i < nodes; i++)
                    {
                        int x = pointX[i];
                        int y = pointY[i];
                        Node node = new Node(i, x, y);
                        nodeList[i] = node;
                        parent.addChild(node);
                    }

                    Node output = nodeList[K - 1];
                    System.out.println(output.toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
    }
}
