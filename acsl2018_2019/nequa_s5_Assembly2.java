import java.util.*;
import java.io.*;
public class Assembly2 {

	static ArrayList<Command> program = new ArrayList<Command>();
	static ArrayList<Memory> memory = new ArrayList<Memory>();
	static final String commands = "load store add sub mult div be bg bl bu end print dc read";
	static int ACC;
	static int[] input;
	static int counter = 0;

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(new File("Mod Data.txt"));
		String[] in = sc.nextLine().split(" ");
		input = new int[in.length];
		for (int i = 0; i < in.length; i++) input[i] = Integer.parseInt(in[i]);
		while(sc.hasNextLine()) 
		{
			program.add(new Command(sc.nextLine().split(" ")));
		}
		sc.close();
		for (int i = 0; i >= 0 && i < program.size(); )
		{
			if (execute(program.get(i)) == -2) i++;
			else if (execute(program.get(i)) == -1) break;
			else i = execute(program.get(i));
		}
		System.out.println(ACC);
	}

	public static int execute(Command c) throws Exception
	{
		switch (c.getCommand())
		{
		case "load":
			if (c.getLocation() == null) ACC = Integer.parseInt(c.getComment());
			else ACC = c.getLocation().getValue();
			break;
		case "store":
			c.getLocation().setValue(ACC);
			break;
		case "add":
			if (c.getLocation() == null) ACC += Integer.parseInt(c.getComment());
			else ACC += c.getLocation().getValue();
			break;
		case "sub":
			if (c.getLocation() == null) ACC -= Integer.parseInt(c.getComment());
			else ACC -= c.getLocation().getValue();
			break;
		case "mult":
			if (c.getLocation() == null) ACC *= Integer.parseInt(c.getComment());
			else ACC *= c.getLocation().getValue();
			break;
		case "div":
			if (c.getLocation() == null) ACC /= Integer.parseInt(c.getComment());
			else ACC /= c.getLocation().getValue();
			break;
		case "be":
			if (ACC == 0) return getIndex(c);
			break;
		case "bg":
			if (ACC > 0) return getIndex(c);
			break;
		case "bl":
			if (ACC < 0) return getIndex(c);
			break;
		case "bu":
			return getIndex(c);
		case "end":
			return -1;
		case "print":
			if (c.getLocation() == null) System.out.println(c.getComment());
			else System.out.println((c.getLocation()).getValue());
			break;
		case "dc":
			memory.get(getMemory(c.getLocation().getLocation())).setValue(Integer.parseInt(c.getComment()));
			break;
		case "read":
			memory.get(getMemory(c.getLocation().getLocation())).setValue(input[Assembly2.counter]);
			Assembly2.counter++;
			break;
		}
		return -2;
	}

	public static int getIndex(Command c) throws Exception
	{
		try 
		{
			for (int i = 0; ; i++)
				if (program.get(i).getLabel() == null) continue;
				else if (program.get(i).getLabel().equals(c.getComment())) return i;
		}
		catch (IndexOutOfBoundsException e)
		{
			throw new LabelNotFoundException("Label '" + c.getComment() + "' not found.");
		}
	}

	public static int getMemory(String location) throws Exception
	{
		for (int i = 0; i < memory.size() ; i++)
			if (memory.get(i).getLocation().equals(location)) return i;
		return -1;
	}
}

class Command {
	private String label;
	private String command;
	private Memory location;
	private String comment;
	static final String comments = "be bg bl bu";

	public Command(String[] input) throws Exception
	{
		if ((input.length == 1 && input[0].toLowerCase().equals("end")))
		{
			command = "end";
			return;
		}
		else if (input.length >= 2 && (input[1].toLowerCase().equals("end") || input[0].toLowerCase().equals("end")))
		{
			command = "end";
			if (input[0].toLowerCase().equals("end")) comment = input[1];
			else label = input[0];
			return;
		}
		if (input.length < 2) throw new CommandNotFoundException("Command must contain OPCODE and LOC.");
		if (input[1].toLowerCase().equals("dc"))
		{
			command = input[1].toLowerCase();
			if (Assembly2.commands.indexOf(command) == -1) throw new CommandNotFoundException("Command '" + command + "' not found.");
			location = new Memory(input[0]);
			Assembly2.memory.add(location);
			comment = input[2];
		}
		else if (input.length == 2)
		{
			command = input[0].toLowerCase(); //
			if (Assembly2.commands.indexOf(command) == -1) throw new CommandNotFoundException("Command '" + command + "' not found.");
			if (comments.indexOf(command) == -1)
			{
				if (input[1].indexOf("=") != -1) 
				{
					location = null;
					comment = input[1].substring(1);
				}
				else if (Assembly2.getMemory(input[1]) == -1) //
				{
					location = new Memory(input[1]);
					Assembly2.memory.add(location);
				}
				else
				{
					location = Assembly2.memory.get(Assembly2.getMemory(input[1]));
				}
			}
			else comment = input[1].toLowerCase();

		}
		else
		{
			label = input[0].toLowerCase();
			command = input[1].toLowerCase();
			if (Assembly2.commands.indexOf(command) == -1) throw new CommandNotFoundException("Command '" + command + "' not found.");
			if (comments.indexOf(command) == -1)
			{
				if (input[2].indexOf("=") != -1) 
				{
					location = null;
					comment = input[2].substring(1);
				}
				else if (Assembly2.getMemory(input[2]) == -1)
				{
					location = new Memory(input[2]);
					Assembly2.memory.add(location);
				}
				else
				{
					location = Assembly2.memory.get(Assembly2.getMemory(input[2]));
				}
			}
			else comment = input[2].toLowerCase();
		}
	}

	public String getLabel()
	{
		return label;
	}

	public String getCommand()
	{
		return command;
	}

	public Memory getLocation()
	{
		return location;
	}

	public String getComment()
	{
		return comment;
	}
}

class Memory {

	private String location;
	private int value;

	public Memory(String location)
	{
		this.location = location;
	}

	public String getLocation()
	{
		return location;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

}

class InvalidMemoryLocationException extends Exception {

	public InvalidMemoryLocationException(String message)
	{
		super(message);
	}

}

class LabelNotFoundException extends Exception {

	public LabelNotFoundException(String message)
	{
		super(message);
	}

}

class CommandNotFoundException extends Exception {

	public CommandNotFoundException(String message)
	{
		super(message);
	}

}
