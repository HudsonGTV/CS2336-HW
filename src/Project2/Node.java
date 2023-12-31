/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

public class Node<T extends Comparable<T>> implements Comparable<T> {
	
	// Payload
	private T m_value;
	
	// Ref to prev and next nodes
	private Node<T> m_next;
	private Node<T> m_prev;
	
	public Node() {
		m_value = null;
		m_next = null;
		m_prev = null;
	}
	
	public Node(T value) {
		this();
		m_value = value;
	}
	
	/* GETTERS */
	
	/**
	 * Gets the value contained in the node
	 * @return the value
	 */
	public T getValue() {
		return m_value;
	}
	
	/**
	 *  Gets a reference to the next node
	 * @return reference to next node
	 */
	public Node<T> getNextNode() {
		return m_next;
	}
	
	/**
	 * Gets a reference to the previous node
	 * @return reference to prev node
	 */
	public Node<T> getPrevNode() {
		return m_prev;
	}
	
	/* SETTERS */
	
	// node payloads should not be writable generally, so commenting this out
	/*public void setValue(T value) {
		m_value = value;
	}*/
	
	/**
	 * Sets next node to specified value
	 * @param node the node's ref to set to
	 */
	public void setNextNode(Node<T> node) {
		m_next = node;
	}
	
	/**
	 * Sets prev node to specified value
	 * @param node the node's ref to set to
	 */
	public void setPrevNode(Node<T> node) {
		m_prev = node;
	}
	
	/* MISC */

	@Override public int compareTo(T other) {
		return getValue().compareTo(other);
	}
	
	@Override public String toString() {
		return m_value.toString();
	}
	
}
