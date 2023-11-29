/**
 * Author: Hudson Green
 * NetID: HKG230000
 */

package Project3;

import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Vertex> m_vertices = null;
	
	// Note: edges are stored inside vertices (Vertex.java)
	
	public Graph() {
		m_vertices = new ArrayList<Vertex>();
	}
	
	/**
	 * Links 2 vertices together with given weight (undirected)
	 * @param v1 the 1st vertex
	 * @param v2 the 2nd vertex
	 * @param weight the path weight
	 */
	public void addRelationship(Vertex v1, Vertex v2, int weight) {
		// Link v1 to v2
		v1.addEdge(new Edge(weight, v2));
		// Link v2 to v1
		v2.addEdge(new Edge(weight, v1));
	}
	
	/**
	 * Attempts to add a relationship using the given vertex names (undirected)
	 * @param v1Name name of 1st vertex
	 * @param v2Name name of 2nd vertex
	 * @param weight the path weight
	 * @param createUnfoundVertex if v2 is not found, then create it
	 * @return if we were able to locate vertices with given names and add the relationship
	 */
	public boolean addRelationship(String v1Name, String v2Name, int weight, boolean createUnfoundVertex) {
		
		// Get the vertices with given name
		Vertex v1 = this.getVertex(v1Name);
		Vertex v2 = this.getVertex(v2Name);
		
		// Check if the vertices are valid
		if(v1 == null || (v2 == null && !createUnfoundVertex)) return false;
		
		// Create v2 if we reach this point and its null
		if(v2 == null) {
			v2 = new Vertex(v2Name);
			this.addVertex(v2);
		}
		
		// Generate a relationship
		this.addRelationship(v1, v2, weight);
		
		// Function ran successfully
		return true;
		
	}
	
	/**
	 * Adds a vertex to the graph (insert Vertex into graph)
	 * @param v the vertex to add to the graph
	 */
	public void addVertex(Vertex v) {
		m_vertices.add(v);
	}
	
	/**
	 * Attempts to get the vertex with given name
	 * @param vertexName the vertex to search for
	 * @return the vertex or null if not found
	 */
	public Vertex getVertex(String vertexName) {
		
		// Loop thru each vertex until we have a match
		for(Vertex v : m_vertices) {
			// Check if we have a match
			if(v.toString().equals(vertexName))
				return v;
		}
		
		// Vertex not in graph
		return null;
		
	}
	
	/**
	 * Prints all the vertices and their relations
	 * @return a string containing all vertices and relations line by line
	 */
	@Override public String toString() {
		
		String out = "";
		
		// Iterate thru all vertices and print planet name & relations
		for(Vertex v : m_vertices) {
			out += v.toString() + " " + v.listEdgesStr() + "\n";
		}
		
		return out;
		
	}

}
