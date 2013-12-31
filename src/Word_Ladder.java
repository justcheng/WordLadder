import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * 
 */

/**
 * @author Justin
 * 
 */
public class Word_Ladder {
	
	private Node<String> root;
	public Word_Ladder()
	{
		root = new Node<String>(source);
	}
	
	static ArrayList<String> wordBank = new ArrayList<String>();
	static ArrayList<Node<String>> nodeArray = new ArrayList<Node<String>>();
	static ArrayList<String> ladder = new ArrayList<String>();
	static Queue<Node<String>> queue = new LinkedList<Node<String>>();

	static String source;// = new String(s.next().toLowerCase());
	static String destination;// = new String(s.next().toLowerCase());
	static String current;// = new String(source.getWord());
	
	public static void main(String[] args) throws IOException {
		
		//File inputFile = new File("commands.txt");
		//File inputDictionary = new File("long+dictionary.txt");
		File inputFile = new File("commands.txt");
		File inputDictionary = new File("long+dictionary.txt");
		Scanner input = new Scanner(inputFile);
		Scanner dictionary = new Scanner(inputDictionary);
		PrintWriter out = new PrintWriter("output.txt");
		//PrintWriter out = new PrintWriter("output.txt");
		
		
		
		while(input.hasNext()){
		source = input.next().toLowerCase();
		destination = input.next().toLowerCase();
		current = source;
		}
		setDictionary(dictionary);
		
		Word_Ladder wLadder = new Word_Ladder();
		Node<String> theRoot = wLadder.getRoot();
		
		createNodes();
		cleanNodes();
		connectNodes();
		
		
		for(int x = 0; x < nodeArray.size(); x++)
		{
			for(int y = 0; y < nodeArray.size(); y++)
			{
				if(x!=y)
				if(nodeArray.get(x).getWord().equals(nodeArray.get(y).getWord()))
				{
					out.print(nodeArray.get(x).getWord() + "    ");
					out.println(nodeArray.get(y).getWord());
				}
			}
			
			if(nodeArray.get(x).getWord().equals(theRoot.getWord()))
			{
				theRoot = nodeArray.get(x);				
			}
		}
		
		theRoot.setParent(null);
		Node<String> finished = search(theRoot);			//Where the breadth first serach is done.    Creates a shortest path using .setParent()
		out.println("input.txt");
		if(finished.getParent() == null)
		{
			out.println("No word ladder found");
		}
		ArrayList<String> output = new ArrayList<String>();
		while(finished.getParent() != null)
		{
			output.add(finished.getWord());
			finished = finished.getParent();
			if(finished.getWord().equals(source))
			{
				output.add(finished.getWord());
			}
		}
		for(int x = 0; x < output.size(); x++)
		{
			if(x == output.size()-1)
				out.print(output.get(x) + ".");
			else
			out.print(output.get(x) + ", ");
			
		}	
		input.close();
		dictionary.close();
		out.close();

	}
	
	public static Node<String> search(Node<String> start)
	{
		queue.offer(start);
		start.setIsvisited(true);
		while(queue.size() > 0)
		{
			Node<String> node = queue.poll();
			node.setColor('g');
			int numChildren = node.getNumberOfChildren();
			int z = 0;
			while(!node.allVisited() && numChildren > 0)
			{
				if(z == numChildren)
					break;
				if(node.getChildAt(z).getColor() != 'b')//allows search to go to white or gray. (unexplored)
				{
					if(node.getChildAt(z).getWord().equals("stone")||node.getChildAt(z).getWord().equals("shone") || node.getChildAt(z).getWord().equals("shine")||node.getChildAt(z).getWord().equals("chine")||node.getChildAt(z).getWord().equals("chins")||node.getChildAt(z).getWord().equals("coins")|| node.getChildAt(z).getWord().equals("corns")||node.getChildAt(z).getWord().equals("cones")|| node.getChildAt(z).getWord().equals("coney")||node.getChildAt(z).getWord().equals("money"))
					{
						System.out.println(node.getWord() + "    "+ node.getChildAt(z).getWord());
					}
					node.getChildAt(z).setIsvisited(true);
					queue.add(node.getChildAt(z));
					if(!node.getWord().equals(node.getChildAt(z).getWord()))
						{
							node.getChildAt(z).setParent(node);
							System.out.println(node.getChildAt(z).getParent().getWord());
						}
					node.getChildAt(z).setColor('g');
					System.out.println(node.getChildAt(z).getWord() +"  "+ queue.size());
					if(node.getChildAt(z).getWord().equals(destination))
					{
						System.out.println("You are done!");
						return node.getChildAt(z);
					}
				}
				z++;
			}
		
			node.setColor('b');//If all children of this node is visited, don't go here anymore
		}
		return start;
	}
	public static void createNodes()
	{

		for(int x = 0; x < wordBank.size(); x++)
		{
			String tempWord = new String(wordBank.get(x).toLowerCase());
			Node<String> tempNode = new Node<String>(tempWord);
			nodeArray.add(tempNode);			
		}
	}
	public static void connectNodes()
	{
		for(int x = 0; x < nodeArray.size(); x++)
		{
			for(int y = 0; y < nodeArray.size(); y++)
			{
				if(validate(nodeArray.get(x).getWord(),nodeArray.get(y).getWord()))		//if one letter apart.
				{
					nodeArray.get(x).addChild(nodeArray.get(y));
				}
			}
		}
	}
	public static void cleanNodes()
	{
		for(int x = 0; x < nodeArray.size(); x ++)
		{
			for(int y = 0; y < nodeArray.size(); y++)
			{
				if(x!=y)
					if(nodeArray.get(x).getWord().equals(nodeArray.get(y).getWord()))
					{
						nodeArray.remove(y);
					}
			}
		}
	}
	public static boolean validate(String compare1, String compare2)		//This will compare if there's only 1 letter difference between the two strings.
	{
		int counter = 0;
		for(int x = 0; x < compare1.length(); x++)
		{
			if(compare1.charAt(x) == compare2.charAt(x))
			{
				counter++;
			}
		}
		if(counter == compare1.length() - 1)
		{
			return true;
		}
		return false;
	}


	public static void setDictionary(Scanner file)//Puts the words from the dictionary into an arrayList
	{
		ArrayList<String> everything = new ArrayList<String>();
		while(file.hasNext())
		{
			everything.add(file.next().toLowerCase());
		}
		
		for(int x = 0; x < everything.size(); x++)
		{
			if(everything.get(x).length() == source.length())
			{
				wordBank.add(everything.get(x));
			}
		}
	}	
	
	public void setRoot(Node<String> root)
	{
		this.root = root;
	}
	public Node<String> getRoot()
	{
		return this.root;
	}

	

	
	
	
	

}
