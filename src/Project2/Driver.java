package Project2;

public class Driver implements Comparable<Driver> {
	
	private String m_name;
	private double m_area;
	private static int comparisonVar;
	
	public Driver() {
		m_name = null;
		m_area = 0;
		
	}
	
	public Driver(String name) {
		m_name = name;
	}
	
	public String getName() {
		return m_name;
	}
	
	public double getArea() {
		return m_area;
	}
	
	// TODO: Implement getter for comparison variable
	
	public void setName(String name) {
		m_name = name;
	}
	
	public void setArea(double area) {
		m_area = area;
	}
	
	// TODO: Implement setter for comparison variable

	@Override public int compareTo(Driver o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override public String toString() {
		return m_name;
	}

}
