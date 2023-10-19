package Project2;

public class LinkedList {
	
	private Node<Driver> m_head;
	private Node<Driver> m_tail;
	
	public LinkedList() {
		m_head = null;
		m_tail = null;
	}
	
	public LinkedList(Node<Driver> head) {
		m_head = head;
		m_tail = head;
	}
	
	/**
	 * Sorts the linked list in ascending order
	 */
	public void sortList() {
		
		
		
	}
	
	private void swap(int i1, int i2) {
		
		// Get the nodes at specified index
		
		// If i2 is only 1 apart, then get second node from first node
		
	}
	
	/**
	 * adds given node to front of list
	 * @param node the node to add
	 */
	public void addFront(Node<Driver> node) {
		
		// Guard against null node
		if(node == null) return;
		
		// Check if list empty and run different function is so
		if(addNodeToEmptyList(node)) return;
		
		// Normal add
		Node<Driver> prevHead = m_head;
		
		// Set old head's prev to point to new node
		prevHead.setPrevNode(node);
		
		// Set new node to point to prev head
		node.setNextNode(prevHead);
		
		// Set head to equal new node
		m_head = node;
		
	}
	
	/**
	 * adds given node to rear of list
	 * @param node the node to add
	 */
	public void addRear(Node<Driver> node) {
		
		// Guard against null node
		if(node == null) return;
		
		// Check if list empty and run different function is so
		if(addNodeToEmptyList(node)) return;
		
		// Normal add
		Node<Driver> prevTail = m_tail;
		
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
	public boolean insertAt(Node<Driver> node, int index) {
		
		// Guard against null node or invalid index
		if(node == null || index < 0) return false;
		
		// Check if head node
		if(index == 0) {
			addFront(node);
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
		
	}
	
	/* Getters */
	
	public Node<Driver> getFirstNode() {
		return m_head;
	}
	
	public Node<Driver> getLastNode() {
		return m_tail;
	}
	
	public int size() {
		
		int size = 0;
		
		// Iterate thru list
		for(Node<Driver> currNode = getFirstNode(); currNode != null; currNode = currNode.getNextNode(), ++size);
		
		return size;
		
	}
	
	/**
	 * @return the data contained in the list
	 */
	@Override public String toString() {
		
		// String output
		String out = "";
		
		// Current node for loop
		Node<Driver> currNode = getFirstNode();
		
		// Loop thru each node
		while(currNode != null) {
			
			// Print the current node's data
			out += currNode.getValue().getName() + "	" + currNode.getValue().getArea() + "\n";
			
			// Select next node
			currNode = currNode.getNextNode();
			
		}
		
		return out;
		
	}
	
	// Attempts to add node if list is empty. returns false if 
	
	/**
	 * Attempts to add node if list is empty.
	 * @param node the node to add
	 * @return false if list is not empty, true if successfully adds node
	 */
	private boolean addNodeToEmptyList(Node<Driver> node) {
		
		// Dont do anything if list is not actually empty
		if(m_head != null || m_tail != null) return false;
		
		// Set head and tail to the node
		m_head = node;
		m_tail = node;
		
		// Show that the head/tail were set successfully
		return true;
		
	}
	
}
