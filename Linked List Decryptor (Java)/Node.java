/**
 * This class is responsible for creating Node objects.
 *
 * The nodes will have a char data type and a "pointer"
 * to the next node as data members.
 *
 * This class has the usual methods; constructor, getters
 * and setters
 * 
 * @author Kierson & Britley
 * 
 */

public class Node {
	
	//Data Members
	//----------------
	private char data;
	private Node next;

	//Constructor
	//--------------
	public Node () {
	}

	//Getters and Setters
	//---------------------
	public char getData() {
		return data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setData(char d) {
		data = d;
	}
	public void setNext(Node n) {
		next = n;
	}

}// end class
