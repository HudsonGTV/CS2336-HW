/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

// For sorting
enum ComparisonType {
	AREA,
	NAME
}

public class Driver implements Comparable<Driver> {
	
	// Info about the driver
	private String m_name;
	private double m_area;
	
	// Comparison type/dir for sorting via compareTo
	private static ComparisonType m_comparisonMode = ComparisonType.AREA;
	
	public Driver() {
		m_name = null;
		setArea(0);
	}
	
	public Driver(String name) {
		this();
		m_name = name;
	}
	
	public Driver(String name, double area) {
		this(name);
		setArea(area);
	}
	
	/* GETTERS */
	
	/**
	 * Get name of driver
	 * @return driver name
	 */
	public String getName() {
		return m_name;
	}
	
	/**
	 * Get area of driven spots
	 * @return area
	 */
	public double getArea() {
		return m_area;
	}
	
	/**
	 * Gets the mode we are using for comparison in sorting
	 * @return enum of the comparison mode
	 */
	public static ComparisonType getComparisonType() {
		return m_comparisonMode;
	}
	
	/* SETTERS */
	
	/**
	 * Set driver name
	 * @param name the name to give to driver
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	/**
	 * Set area of driven points
	 * @param area the area to set it to
	 */
	public void setArea(double area) {
		m_area = area;
	}
	
	/**
	 * Changes comparison type for all instances of Driver
	 * @param comp new comparison type (for sorting)
	 */
	public static void setComparisonType(ComparisonType comp) {
		m_comparisonMode = comp;
	}
	
	/* MISC */

	/**
	 * @param other the other Driver to compare against
	 * @return 0 if equal, negative if other is greater, and positive if other is less, or Integer.MIN_VALUE
	 */
	@Override public int compareTo(Driver other) {
		
		switch(getComparisonType()) {
		case AREA:	// sort by area
			return (int)Math.round(this.getArea() - other.getArea());
		case NAME:	// sort by name
			return this.getName().compareTo(other.getName());
		default:	// this should never happen
			return Integer.MIN_VALUE;
		}
		
	}
	
	@Override public String toString() {
		return getName() + "	" + getArea();
	}

}
