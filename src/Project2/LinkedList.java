package Project2;

public class LinkedList<T> {
	
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
	 * Sorts the linked list in ascending order
	 */
	public void sortList() {
		// TODO: Sort list
	}
	
	/**
	 * adds given node to front of list
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
	 * adds given node to rear of list
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
	
	/* Getters */
	
	public Node<T> getFirstNode() {
		return m_head;
	}
	
	public Node<T> getLastNode() {
		return m_tail;
	}
	
	/**
	 * @return the data contained in the list
	 */
	@Override public String toString() {
		return "not implemented";
	}
	
	// Attempts to add node if list is empty. returns false if 
	
	/**
	 * Attempts to add node if list is empty.
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
