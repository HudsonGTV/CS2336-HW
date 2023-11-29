/**
 * Author: Hudson Green
 * NetID: HKG230000
 */

package Project3;

import java.util.ArrayList;

public class Vertex {
	
	private String m_value = "";
	
	private ArrayList<Edge> m_edges = null;
	
	public Vertex(String value) {
		m_edges = new ArrayList<Edge>();
		m_value = value;
	}
	
	/**
	 * Attempts to get the edge that contains the given neighboring vertex
	 * @param vertex the vertex that the desired edge points to
	 * @return the edge showing the relationship, or null if not found
	 */
	public Edge getNeighborEdge(String vertex) {
		
		// Search for edge with given destination vertex
		for(Edge e : m_edges) {
			// Check if we have a match
			if(e.getDestination().toString().equals(vertex))
				return e;
		}
		
		// Not found
		return null;
		
	}
	
	/**
	 * Adds a neighbor to the vertex
	 * @param edge the edge that represents the weight and neighbor
	 */
	public void addEdge(Edge edge) {
		m_edges.add(edge);
	}
	
	/**
	 * Debug function. Lists all neighbors
	 * @return a string of all neighbors
	 */
	public String listEdgesStr() {
		
		String out = "";
		
		for(Edge e : m_edges) {
			out += "(" + e.getDestination() + ", " + e.getWeight() + ") ";
		}
		
		return out;
		
	}
	
	/**
	 * Gets the name of the vertex
	 * @return the name of the vertex
	 */
	@Override public String toString() {
		return m_value;
	}
	
}
