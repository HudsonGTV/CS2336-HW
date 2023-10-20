/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

public class Driver implements Comparable<Driver> {
	
	private String m_name;
	private double m_area;
	private int m_comparisonVar = 0;
	
	public Driver() {
		m_name = null;
		setArea(0);
	}
	
	public Driver(String name) {
		this();
		m_name = name;
	}
	
	// tempt for debugging
	public Driver(int area) {
		this("foo");
		setArea(area);
	}
	
	public String getName() {
		return m_name;
	}
	
	public double getArea() {
		return m_area;
	}
	
	public int getComparisonVariable() {
		return m_comparisonVar;
	}
	
	// TODO: Implement getter for comparison variable
	
	public void setName(String name) {
		m_name = name;
	}
	
	public void setArea(double area) {
		m_area = area;
		updateComparisonVar();
	}
	
	public void updateComparisonVar() {
		m_comparisonVar = (int)Math.round(getArea());
	}
	
	// TODO: Implement setter for comparison variable

	/**
	 * @param other the other Driver to compare against
	 * @return 0 if equal, negative if other is greater, and positive if other is less
	 */
	@Override public int compareTo(Driver other) {
		return getComparisonVariable() - other.getComparisonVariable();
	}
	
	@Override public String toString() {
		return m_name;
	}

}
