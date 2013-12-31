import java.util.ArrayList;
import java.util.List;


public class Node<String>  
{	
	private boolean visited = false;
	private char color;
	private String word;
	private ArrayList<Node<String>> children;
	private Node<String> parent;
	private ArrayList<String> wordBranch = new ArrayList<String>();
	public Node() {												//I don't think this is ever called.
	    super();
	    children = new ArrayList<Node<String>>();
	}
	public boolean allVisited()
	{
		int count = 0;
		for(int x = 0; x < getNumberOfChildren(); x++)
		{
			if(getChildAt(x).getIsVisited())
			{
				count++;
			}
		}
		if(count == getNumberOfChildren())
		{
			return true;
		}
		return false;
	}
	public char getColor()
	{
		return color;
	}
	public void setColor(char color)	//white gray and black
	{
		this.color = color;
	}
	public boolean getIsVisited()
	{
		return visited;
	}
	public void setIsvisited(boolean TF)
	{
		visited = TF;
	}
	public Node(String newWord) {

		setData(newWord);
		color = 'w';		//white grey and black
	    this.word = newWord;
	    children = new ArrayList<Node<String>>();	   
	    wordBranch.add(newWord);								//Program is unhappy with this... Need to fix and put back in.							Not sure if this is being used.			
	}
	public String getWord()
	{
		return word;
	}
	public ArrayList<String> getWordBranch()
	{
		return wordBranch;
	}
	public Node<String> getParent() {
	    return this.parent;
	}
	public void setParent(Node<String> newParent)
	{
		this.parent = newParent;
	}
	public List<Node<String>> getChildren() {
	    return this.children;
	}
	
	public int getNumberOfChildren() {
	    return getChildren().size();
	}
	
	public boolean hasChildren() {
	    return (getNumberOfChildren() > 0);
	}
	
	public void setChildren(ArrayList<Node<String>> children) {
	    for(Node<String> child : children) {
	       child.parent = this;
	    }
	
	    this.children = children;
	}
	
	public void addChild(Node<String> child) {														
	    child.parent = this;
	    children.add(child);
	}
	
	public void addChildAt(int index, Node<String> child) throws IndexOutOfBoundsException {		//This should take care of the 5 branch/leaf nodes   DON'T THINK THIS IS USED AS WELL.
	    child.parent = this;
	    children.add(index, child);
	}
	
	public void removeChildren() {
	    this.children = new ArrayList<Node<String>>();
	}
	
	public void removeChildAt(int index) throws IndexOutOfBoundsException {
	    children.remove(index);
	}
	
	public Node<String> getChildAt(int index) throws IndexOutOfBoundsException {
	    return children.get(index);
	}
	public String getData() {
	    return this.word;
	}
	
	public void setData(String data) {
	    this.word = data;
	}
	

	
}




