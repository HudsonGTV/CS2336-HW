/**
 * Author: Hudson Green
 * NetID: HKG230000
 */

package Project3;

public class Edge {
	
	private int m_weight = 0;
	
	private Vertex m_destination = null;
	
	public Edge(int weight, Vertex destination) {
		m_weight = weight;
		m_destination = destination;
	}
	
	/**
	 * Gets the weight of the path
	 * @return the weight
	 */
	public int getWeight() {
		return m_weight;
	}
	
	/**
	 * Gets the destination vertex
	 * @return the destination vertex
	 */
	public Vertex getDestination() {
		return m_destination;
	}

}
