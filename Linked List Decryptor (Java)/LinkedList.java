/**
 * This class is responsible for creating Linked Lists.
 *
 * This class has a reference to the head of the linked list
 * and an Array List of Nodes as data members
 *
 * This class has the usual methods; constructor, getters
 * and setters
 * 
 * In addition to these methods it has methods that manipulate
 * the linked list in order to achieve the goals of this application.
 *
 * @author Kierson & Britley
 */
import java.util.ArrayList;

public class LinkedList {
  //Data members
  //------------------------------
	private Node head;
	private ArrayList<Node> nodes;

  //Constructor
  //--------------------------------------
	public LinkedList(ArrayList<Node> n) {
		nodes = n;
	}

  //Getters and Setters
  //-----------------------
	public Node getHead() {
		return nodes.get(0);
	}
	
	public void setHead(Node h) {
		h.setNext(head);
		head = h;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(ArrayList<Node> n) {
		nodes = n;
	}

  /**
	 * Adds a node to the end of the linked list
	 * 
	 * @param j - Node to be added to the end of the 
	 *            linked list
	 * @return void
	 * ---------------------------------------------
	 */
	public void addToEnd(Node j) {
		Node temp = new Node();
		if (head != null) {
			temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(j);
			j.setNext(null);
		}
	}
  	/**
	 * Prints the data values of the nodes from the linked list to the screen 
	 * by traversing over the linked list one by one via getNext()
	 * 
	 * @param
	 * @return void
	 * ----------------------------------------------------------------------
	 */
	public void printLinkedList() {
		Node temp = new Node();
		temp = head;

		while(temp != null) {
			System.out.print(temp.getData());
			temp = temp.getNext();
		}
		System.out.print(" ");
		
	}

}// end class
