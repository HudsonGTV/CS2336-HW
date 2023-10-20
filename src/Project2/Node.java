/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

public class Node<T extends Comparable<T>> implements Comparable<T> {
	
	private T m_value;
	
	private Node<T> m_next;
	private Node<T> m_prev;
	
	public Node() {
		m_value = null;
		m_next = null;
		m_prev = null;
	}
	
	public Node(T value) {
		m_value = value;
	}
	
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
	
	@Override public String toString() {
		return m_value.toString();
	}

	@Override public int compareTo(T other) {
		return getValue().compareTo(other);
	}
	
}
