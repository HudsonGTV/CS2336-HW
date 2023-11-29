/**
 * Author: Hudson Green
 * NetID: HKG230000
 */

package Project3;

public class Pilot implements Comparable<Pilot> {
	
	private String m_name = "";
	
	private int m_pathWeight = 0;
	
	private boolean m_isValidPath = false;
	
	public Pilot(String name, int pathWeight, boolean isValidPath) {
		m_name = name;
		m_pathWeight = pathWeight;
		m_isValidPath = isValidPath;
	}
	
	/**
	 * Gets the pilot's name
	 * @return pilot's name
	 */
	public String getName() {
		return m_name;
	}
	
	/**
	 * Gets the pilot's patrol path weight
	 * @return total path weight or 0 if invalid path
	 */
	public int getPathWeight() {
		return this.checkIfPathValid() ? m_pathWeight : 0;
	}
	
	/**
	 * Checks if the pilot's path is valid
	 * @return if path is valid
	 */
	public boolean checkIfPathValid() {
		return m_isValidPath;
	}

	/**
	 * Compares using path weight, or name if weights are same
	 * @param other the other pilot to compare to
	 * @return distance apart from each other
	 */
	@Override public int compareTo(Pilot other) {
		
		// Compare via path weight
		int compareWeight = this.getPathWeight() - other.getPathWeight();
		
		// Compare via pilot name if same path weight
		if(compareWeight == 0)
			compareWeight = this.getName().compareTo(other.getName());
		
		// Return the final compare weight
		return compareWeight;
		
	}
	
	/**
	 * The pilot in the specified format
	 * @return the formatted pilot data
	 */
	@Override public String toString() {
		return this.getName() + "	" + this.getPathWeight() + "	" + (this.checkIfPathValid() ? "valid" : "invalid");
	}

}
