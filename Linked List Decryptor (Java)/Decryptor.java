/**
 * CMS-250 Linked List Cipher 
 * @author Kierson Baxley & Britley Desir
 *
 * This application utilizes a .txt file of enciphered text and deciphers the input. 
 * Each line of text must fall on a new line of and each word separated by an 
 * asterisk. The application stores each letter in a node and deciphers while storing
 * each node in a LinkedList with the LinkedList containing one word.
 *--------------------------------------------------------------------------------------
 */

// Import required libraries
//-----------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// Main Driver Class
//----------------------
public class Decryptor {
	
	// Main Method of Driver Class
	// -------------------------------------
	public static void main(String[] args) {
		
		// Initialize scanner
		Scanner input = null;

		// Verify file input
		try {
			input = new Scanner(new File("texts.txt"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Begin Reading Input
		while(input.hasNext()) {
			
			// Store each line as a string and add
			// an asterisk for simpler delimiting
			// later in the program
			String line = input.nextLine().strip();
			line = line.concat("*");
			
			// Initialize ArrayList of Nodes
			ArrayList<Node> a = new ArrayList<Node>();
			
			// Begin iterate over each character in
			// the line
			for(int i = 0; i < line.length(); i++)  {
				
				// Store character as a node
				// if it not an asterisk
				if(line.charAt(i) != '*') {
					Node node = new Node();
					node.setData(line.charAt(i));
					a.add(node);
				}
				
				// If character is an asterisk then begin
				// building LinkedList
				else {
					
					// Initialize an empty ArrayList of Nodes
					ArrayList<Node> b = new ArrayList<Node>();
					
					// If ArrayList size of one
					// no decipher is required
					// immediately print
					if(a.size() == 1) {
						b.add(a.get(0));
						LinkedList n = new LinkedList(a);
						n.setHead(a.get(0));
						a.get(0).setNext(null);
						n.printLinkedList();
					}
					
					// If ArrayList is greater than one
					// then create LinkedList and decipher
					// each character accordingly
					else {
						b.add(a.get(0));
						LinkedList n = new LinkedList(b);
						n.setHead(a.get(0));
						a.get(0).setNext(null);
						for(int j = 1; j < a.size(); j++) {
							if(j % 2 != 0) {
								n.setHead(a.get(j));
							}
							else {
								n.addToEnd(a.get(j));
							}
						}
						
						// print list after sorting
						n.printLinkedList();
						}
					
					// reset a for next input
					a.clear();
					
				}// end else
				
			}// end for loop
			
			// print new line
			System.out.println();
		}// end while loop	
	}// end main
}// end driver
