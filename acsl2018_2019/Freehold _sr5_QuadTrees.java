import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QuadTrees {

	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner (new File ("as10-test.txt"));
		for (int l = 0; l < 10; l++)
		{
			int search = scan.nextInt();
			int n = scan.nextInt();
			int x = scan.nextInt();
			int y = scan.nextInt();
			Node root = new Node (x,y,1);
			for (int i = 1; i < n; i++)
			{
				x = scan.nextInt();
				y = scan.nextInt();
				root.addNode(new Node(x,y,i+1));
			}
			Node result = traverse(root, search);
			Node[] resultChildren = result.getChildren();
			for (int i = 0; i < 4; i++)
			{
				if (resultChildren[i] == null)
					System.out.print(0);
				else
					System.out.print(resultChildren[i].getId());
			}
			System.out.println();
		}

	}

	public static Node traverse (Node tempNode, int search)
	{

		Node[] tempChildren = tempNode.getChildren();
		for (int i = 0; i < 4; i++)
		{
			if (tempChildren[i] == null)
				continue;
			Node newNode = traverse(tempChildren[i], search);
			if(newNode.getId() == search) return newNode;
		}
		return tempNode;

	}

}
