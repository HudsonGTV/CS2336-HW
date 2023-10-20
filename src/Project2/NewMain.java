/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NewMain {
	
	public static void main(String[] args) {
		
		// Driver and command file names
		String driverFile = null;
		String cmdFile = null;
		
		// Stores driver route data
		LinkedList driverData = null;
		
		// Scanner to read input
		Scanner scannerIn = new Scanner(System.in);
		
		// Prompt for driver data file
		System.out.println("Enter Driver Routes File: ");
		
		// Store data file name
		driverFile = scannerIn.nextLine();
		
		// Prompt for command file name
		System.out.println("Enter Search/Sort Commands File: ");
		
		// Store data file name
		cmdFile = scannerIn.nextLine();
		
		// Close input scanner
		scannerIn.close();
		
		// Load all driver data
		driverData = processDriverData(driverFile);
		
		// Catch in case function was unable to generate a linked list
		if(driverData == null) {
			System.out.println("ERROR: CANNOT CONTINUE AS UNABLE TO PARSE FILE");
			return;
		}
		
		// Print out data
		System.out.println(driverData.toString());
		
	}
	
	/**
	 * 
	 * @param fileName the name of the driver route data file
	 * @return A linked list of the drivers and their areas
	 */
	public static LinkedList processDriverData(String fileName) {
		
		// File reader and driver data
		Scanner fileScanner = null;
		LinkedList driverData = null;
		
		// Attempt to open file
		try {
			fileScanner = new Scanner(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Error: File not found. Printing stack trace...");
			e.printStackTrace();
			return null;
		}
		
		// Create a linked list to store driver data
		driverData = new LinkedList();
		
		// Read file line by line
		for(int i = 0; fileScanner.hasNextLine(); ++i) {
			
			// Current line in file
			String currLine = fileScanner.nextLine();
			
			// Create node with driver data
			Driver driver = generateDriverFromData(currLine);
			
			// If input was valid and node is not null, then put that node in the linked list
			if(driver != null) driverData.addRear(new Node<Driver>(driver));
			
		}
		
		// Returns the driver data linked list
		return driverData;
		
	}
	
	/**
	 * Generates a Driver object if line data is valid
	 * @param line the data to generate a Driver object with a name and area
	 * @return Driver object with area if valid data, otherwise null
	 */
	public static Driver generateDriverFromData(String line) {
		
		// Driver object
		Driver out = null;
		
		// Min valid line length is 5 (s #,#) otherwise skip line
		if(line.length() < 5) return null;
		
		// Split line into substrings
		String[] lineElements = line.split(" ");
		
		// Store name of driver
		String driverName = lineElements[0];
		
		// Store driver coords
		Double2[] coords = new Double2[lineElements.length - 1];
		
		// Ensure name is valid (alphas, hyphens, and apostrophes), otherwise skip line
		if(!driverName.matches("[a-zA-Z'-]+")) {
			System.out.println("[Error] invalid driver name " + driverName);
			return null;
		}
		
		// Loop thru each set of coords
		for(int i = 1; i < lineElements.length; ++i) {
			
			// Check if invalid coords
			if(!Double2.validateXYString(lineElements[i])) return null;
			
			// Add coords to list
			coords[i - 1] = new Double2(lineElements[i]);
			
		}
		
		// Calculate area of coords for new driver object
		out = new Driver(driverName, shapeArea(coords));
		
		// Return the driver object
		return out;
		
	}
	
	/**
	 * Returns total area of a given shape with n sides.
	 * Last coord must match starting coord
	 * 
	 * @param coords - an array of coords for the shape
	 * @return area of given coords (rounded to 2 decimal places)
	**/
	public static double shapeArea(Double2[] coords) {
		
		// Store area
		double sigmaVal = 0;
		
		// Iterate thru each coord and calculate sigma
		// Checks if coords ahead are starting coords (inclusive of n - 2, hence i + 1)
		for(
			int i = 0; 
			!(
				coords[0].x == coords[i + 1].x && 
				coords[0].y == coords[i + 1].y		// checks if we've reached the end (when the curr coords are equal to the first coords)
			) && i <= coords.length - 1; 			// ensure that we do not crash if user fails to have a matching start and end point
			++i
		) {
			// Calculate sigmaVal (x_i+1 + x_i) * (y_i+1 - y_i)
			sigmaVal += (coords[i + 1].x + coords[i].x) * (coords[i + 1].y - coords[i].y);
		}
		
		// Calculate area of shape rounded to 2 decimal places as instructions say
		return Math.round((Math.abs(sigmaVal) / 2.0) * 100.0) / 100.0;
		//return Math.abs(sigmaVal) / 2.0;	// non-rounded area
		
	}
	

}
