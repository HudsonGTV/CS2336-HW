/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

public class LinkedList<T extends Comparable<T>> {
	
	private Node<T> m_head;
	private Node<T> m_tail;
	
	public LinkedList() {
		m_head = null;
		m_tail = null;
	}
	
	public LinkedList(Node<T> head) {
		m_head = head;
		m_tail = head;
	}
	
	/**
	 * Sorts the linked list in ascending order (bubble sort)
	 */
	public void sort() {
		
		int size = size();
		
		// Bubble sort
		for(int i = 0; i < size - 1; ++i) {
			
			boolean didSwap = false;
			
			// Run thru entire list
			for(int j = 0; j < size - 1; ++j) {
				Node<T> curr = getAt(j);
				// if curr node > next node then swap
				if(curr.getValue().compareTo(curr.getNextNode().getValue()) > 0) {
					didSwap = true;
					swap(j);
				}
			}
			
			// if we didnt swap in above loop then it is already in order
			if(!didSwap) break;
			
		}
		
	}
	
	/**
	 * Swaps node at current index with the node to the right (node.m_next) of it
	 * @param index index of original 1st node
	 */
	public void swap(int index) {
		
		// Get ref to first node
		Node<T> n1 = getAt(index);
		
		// Ensure a swap is possible
		if(n1 == null || n1.getNextNode() == null) return;
		
		// Get reference to second node
		Node<T> n2 = n1.getNextNode();
		
		// Temp node
		Node<T> tmp = null;
		
		/* Check if head and tail need to point to other node */
		if(m_head == n1)
			m_head = n2;
		if(m_tail == n2)
			m_tail = n1;
		
		/* Make "n2" and "n1's prev (tmp)" node point to each other */
		
		// get node in front of n1
		tmp = n1.getPrevNode();
		
		// make "n1's prev node (tmp)"'s next node point to n2 if n1 was not the first node
		if(tmp != null)
			tmp.setNextNode(n2);
		
		// make n2's prev ref point to n1's old prev
		n2.setPrevNode(tmp);
		
		/* Make "n1" and "n2's next (tmp)" node point to each other */
		
		// update tmp to be n2's old next
		tmp = n2.getNextNode();
		
		// make "n2's next node (tmp)"'s prev node point to n1 if n2 was not the last node
		if(tmp != null)
			tmp.setPrevNode(n1);
		
		// make n1's next ref point to n2's old next
		n1.setNextNode(tmp);
		
		/* Update n1 and n2 to point to each other in reverse */
		n1.setPrevNode(n2);
		n2.setNextNode(n1);
		
	}
	
	/**
	 * adds given node to front of list - acts as setter for m_head
	 * @param node the node to add
	 */
	public void addFront(Node<T> node) {
		
		// Guard against null node
		if(node == null) return;
		
		// Check if list empty and run different function is so
		if(addNodeToEmptyList(node)) return;
		
		// Normal add
		Node<T> prevHead = m_head;
		
		// Set old head's prev to point to new node
		prevHead.setPrevNode(node);
		
		// Set new node to point to prev head
		node.setNextNode(prevHead);
		
		// Set head to equal new node
		m_head = node;
		
	}
	
	/**
	 * adds given node to rear of list - acts as setter for m_tail
	 * @param node the node to add
	 */
	public void addRear(Node<T> node) {
		
		// Guard against null node
		if(node == null) return;
		
		// Check if list empty and run different function is so
		if(addNodeToEmptyList(node)) return;
		
		// Normal add
		Node<T> prevTail = m_tail;
		
		// Set old tail's next to point to new node
		prevTail.setNextNode(node);
		
		// Set new node to point to prev tail
		node.setPrevNode(prevTail);
		
		// Set new tail to node
		m_tail = node;
		
	}
	
	/**
	 * inserts a node at a given index
	 * @param node the node to insert
	 * @param index location to insert at
	 * @return if was able to add at location or if it was an invalid location/the node didn't exist
	 */
	/*public boolean insertAt(Node<Driver> node, int index) {
		
		// Guard against null node or invalid index
		if(node == null || index < 0) return false;
		
		// Check if head node or tail node
		if(index == 0) {
			addFront(node);
			return true;
		} else if(index == size() - 1) {
			addRear(node);
			return true;
		}
		
		// Current node for loop
		Node<Driver> oldCurrNode = getFirstNode();
		
		// Loop until at index
		for(int i = 0; i < index; ++i) {
			if(oldCurrNode == null) return false;
			oldCurrNode = oldCurrNode.getNextNode();
		}
		
		// Store ref to prev and next node
		Node<Driver> prevNode = oldCurrNode.getPrevNode();
		Node<Driver> nextNode = oldCurrNode.getNextNode();
		
		// Set correct values on given node
		node.setPrevNode(prevNode);
		node.setNextNode(oldCurrNode);
		
		// Update prev node ref
		prevNode.setNextNode(node);
		
		// Update oldCurrNode's refs
		oldCurrNode.setPrevNode(node);
		
		// Show that function added node successfully
		return true;
		
	}*/
	
	/* Getters */
	
	/**
	 * Get a node at a given index
	 * @param index index of the node
	 * @return the node at the given index
	 */
	public Node<T> getAt(int index) {
		
		Node<T> currRef = getFirstNode();
		
		// Ensure valid index
		if(index < 0) return null;
		
		// Loop until at desired node
		for(int i = 0; i < index; ++i) {
			currRef = currRef.getNextNode();
			// ensure we are still going to be in bounds
			if(currRef.getNextNode() == null) return null;
		}
		
		// Curr Ref
		return currRef;
		
	}
	
	/**
	 * Gets head node
	 * @return the head node
	 */
	public Node<T> getFirstNode() {
		return m_head;
	}
	
	/**
	 * Gets tail node
	 * @returnthe tail node
	 */
	public Node<T> getLastNode() {
		return m_tail;
	}
	
	/**
	 * The size of the list
	 * @return size
	 */
	public int size() {
		
		int size = 0;
		
		// Iterate thru list
		for(Node<T> currNode = getFirstNode(); currNode != null; currNode = currNode.getNextNode(), ++size);
		
		return size;
		
	}
	
	/**
	 * Checks if the linked list contains no nodes -
	 * More efficient than running size() to use to check if empty
	 * @return If list is empty or not
	 */
	public boolean isEmpty() {
		return m_head == null;
	}
	
	/**
	 * @return the data contained in the list
	 */
	@Override public String toString() {
		
		// String output
		String out = "";
		
		// Current node for loop
		Node<T> currNode = null;
		
		// Determine sort dir
		if(Main.sortDirection == ComparisonDirection.ASCENDING)
			currNode = getFirstNode();
		else
			currNode = getLastNode();
		
		// Loop thru each node
		while(currNode != null) {
			
			// Print the current node's data
			out += currNode.toString() + "\n";
			
			// Select next/prev node
			if(Main.sortDirection == ComparisonDirection.ASCENDING)
				currNode = currNode.getNextNode();
			else
				currNode = currNode.getPrevNode();
			
		}
		
		// returns all the nodes as 1 string
		return out;
		
	}
	
	/**
	 * Attempts to add node if list is empty - helper function
	 * @param node the node to add
	 * @return false if list is not empty, true if successfully adds node
	 */
	private boolean addNodeToEmptyList(Node<T> node) {
		
		// Dont do anything if list is not actually empty
		if(m_head != null || m_tail != null) return false;
		
		// Set head and tail to the node
		m_head = node;
		m_tail = node;
		
		// Show that the head/tail were set successfully
		return true;
		
	}
	
}
