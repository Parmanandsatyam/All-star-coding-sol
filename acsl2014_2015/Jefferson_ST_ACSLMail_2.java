import java.util.*;

public class ACSLMail
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
	 System.out.print("1.");
	 String in = scan.nextLine().trim();
	 C2 c = new C2(in);
	 for(int x = 2; x <= 6; x++)
	 {
	   System.out.print(x + ".");
	   in = scan.nextLine().trim();
	   System.out.println((x-1) + ". " + c.add(in));
	 }
  }
}

class C2
{
  String start;
  ArrayList<House> arr;
  public C2(String b)
  {
	 arr = new ArrayList<House>();
	 String[] a = b.split(",");
	 start = a[0].trim();
	 
	 for(int x = 1; x < a.length; x++)
	 {
	   House temp = new House(a[x].trim());
		temp.setStart(start);
	   arr.add(temp);
	 }
  }
  
  public String add(String a)
  {
    String[] ar = a.split(",");
	 start = ar[ar.length-2].trim();
	 for(int x = 0; x < arr.size(); x++)
	 {
	   arr.get(x).setStart(start);
		arr.get(x).orders(arr.get(x).start);
	 }
	 for(int x = 1; x < ar.length-2; x++)
	 {
	   House temp = new House(ar[x].trim());
		temp.setStart(start);
	   arr.add(temp);
	 }
	 return find(Integer.parseInt(ar[ar.length-1].trim()));
  }
  
  public String find(int b)
  {
    PriorityQueue<House> pq = new PriorityQueue<House>();
	 for(int x = 0; x < arr.size(); x++)
	 {
	   pq.add(arr.get(x));
	 }
	 for(int x = 0; x < b-1; x++)
	 {
	   pq.remove();
	 }
	 
	 if(pq.isEmpty())
	 {
	   return "No more houses.";
	 }
	 else
	 {
	   return pq.remove().toString();
	 }
  }
}

class House implements Comparable<House>
{
  String order;
  
  String letter;
  int number = 0;
  
  String start;
  
  public House(String a)
  {
    letter = a.substring(0,1);
	 number = Integer.parseInt(a.substring(1));
	 start = "";
	 order = "";
  }
  
  public String toString()
  {
    return letter+number;
  }
  
  void setStart(String s)
  {
    start = s;
  }
  
  public int compareTo(House h)
  {
    order = orders(start);
	 if(number%2 < h.number%2)
	 {
	   return 1;
	 }
	 else if(number%2 == h.number%2)
	 {
	   if(order.indexOf(letter) < order.indexOf(h.letter))
		{
		  return -1;
		}
		else if(order.indexOf(letter) == order.indexOf(h.letter))
		{
		  return this.number - h.number;
		}
		else
		{
		  return 1;
		}
	 }
	 else
	 {
	   return -1;
	 }
  }
  
  String orders(String start)
  {
    String t = "ABCD";  
	 for(int x = 0; x < t.length(); x++)
	 {
	   if(start.equals(t.substring(0, 1)))
		{
		  break;
		}
		else
		{
		  t = t.substring(1) + t.substring(0,1);
		}
	 }
	 order = t;
	 return order;
  }
  
}