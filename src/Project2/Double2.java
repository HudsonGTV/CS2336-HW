/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

public class Double2 {
	
	public double x = 0.0;
	public double y = 0.0;
	
	Double2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	Double2(String xy) {
		setXYFromString(xy);
	}
	
	/**
	 * 
	 * @param xy xy coords should be in following format: "#,#"
	 * @return true if valid string, false if invalid (does not modify x,y if returning false)
	 */
	public boolean setXYFromString(String xy) {
		
		// Determine if valid coord string
		boolean isValidStr = validateXYString(xy);
		
		// Check if valid
		if(isValidStr) {
			
			// Set coords
			String[] coords = xy.split(",");
			this.x = Double.parseDouble(coords[0]);
			this.y = Double.parseDouble(coords[1]);
			
			// Success
			return true;
			
		}
		
		// Invalid coords
		return false;
		
	}
	
	public static boolean validateXYString(String xy) {
		
		String[] coord = xy.split(",");
		
		// Ensure valid coord length & Ensure x/y are doubles
		if(coord.length != 2 || !checkIfDouble(coord[0]) || !checkIfDouble(coord[1]))
			return false;
		
		// Valid coord if passed prev if statement
		return true;
		
	}
	
	private static boolean checkIfDouble(String str) {
		
		// Ensure a valid double
		try {
			Double.parseDouble(str);
		} catch(NumberFormatException e) {
			return false;
		}
		
		// Ensure no whitespace
		return !str.contains(" ");
		
	}
	
}
