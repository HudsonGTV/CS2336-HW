/**
 * Author: Hudson Green
 * NetID: HKG230000
**/

package Project2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//For sorting
enum ComparisonDirection {
	ASCENDING,
	DESCENDING
}

public class Main {
	
	public static ComparisonDirection sortDirection = ComparisonDirection.ASCENDING;
	
	public static void main(String[] args) {
		
		// Driver and command file names
		String driverFile = null;
		String cmdFile = null;
		
		// Stores driver route data
		LinkedList<Driver> driverData = null;
		
		// Scanner to read input
		Scanner scannerIn = new Scanner(System.in);
		
		// Prompt for driver data file
		System.out.println("Enter Driver Routes File: ");
		
		// Store data file name
		driverFile = scannerIn.nextLine();
		//driverFile = "pilot_data.txt";
		
		// Prompt for command file name
		System.out.println("Enter Search/Sort Commands File: ");
		
		// Store data file name
		cmdFile = scannerIn.nextLine();
		//cmdFile = "cmd-o.txt";
		
		// Close input scanner
		scannerIn.close();
		
		// Load all driver data
		driverData = processDriverData(driverFile);
		
		// Catch in case function was unable to generate a linked list
		if(driverData == null) {
			System.out.println("Error: CANNOT CONTINUE AS UNABLE TO GERNERATE LINKED LIST");
			return;
		}
		
		// Print out initial data
		System.out.println("\nDebug: Original data format:\n" + driverData.toString() + "\nCOMMANDS:");
		
		// Process commands
		processCommandData(cmdFile, driverData);
		
	}
	
	/**
	 * Checks if a number is a double (more strict: whitespace or ending with d makes it not a double)
	 * @param str the string to check if double
	 * @return true if valid double, false if not
	 */
	public static boolean checkIfDouble(String str) {
		
		// Ensure a valid double
		try {
			Double.parseDouble(str);
		} catch(NumberFormatException e) {
			return false;
		}
		
		// Ensure no whitespace or double marker
		return !str.contains(" ") && !str.endsWith("d");
		
	}
	
	/**
	 * 
	 * @param fileName the name of the driver route data file
	 * @return A linked list of the drivers and their areas
	 */
	public static LinkedList<Driver> processDriverData(String fileName) {
		
		// File reader and driver data
		Scanner fileScanner = null;
		LinkedList<Driver> driverData = null;
		
		// Attempt to open file
		try {
			fileScanner = new Scanner(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Error: Data file not found.");
			return null;
		}
		
		// Create a linked list to store driver data
		driverData = new LinkedList<Driver>();
		
		// Read file line by line
		while(fileScanner.hasNextLine()) {
			
			// Current line in file
			String currLine = fileScanner.nextLine();
			
			// Create node with driver data
			Driver driver = generateDriverFromData(currLine);
			
			// If input was valid and node is not null, then put that node in the linked list
			if(driver != null) driverData.addRear(new Node<Driver>(driver));
			
		}
		
		// Close file scanner
		fileScanner.close();
		
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
		
		// Extract name from line
		String driverName = line.split("[0-9]")[0];
		
		// Check that we are not grabbing a negative sign from the first number
		if((driverName.endsWith(" -") || driverName.endsWith(" -.")) && String.valueOf(line.charAt(driverName.length())).matches("[0-9]")) {
			driverName = driverName.substring(0, driverName.length() - (driverName.endsWith(" -") ? 1 : 2));
		}
		
		// There MUST be whitespace before the first coords (aka after the name) so check for that
		if(driverName.length() > 0 && driverName.charAt(driverName.length() - 1) == ' ') {
			driverName = driverName.substring(0, driverName.length() - 1);
		} else return null;
		
		// Remove name from line
		line = line.substring(driverName.length());
		
		// Ensure name is valid (alphas, hyphens, and apostrophes), otherwise skip line (also check for double or more spaces in a row 
		// which is invalid, or trailing/leading spaces)
		// !driverName.replaceAll(" ", "").matches("[a-zA-Z'-]+") || driverName.contains("  ") || !driverName.equals(driverName.trim())
		if(!checkIfValidName(driverName)) {
			//System.out.println("[Error] invalid driver name " + driverName);
			return null;
		}
		
		// Split line into substrings
		String[] lineElements = line.split(" ");
		
		// Store driver coords
		Double2[] coords = new Double2[lineElements.length - 1];
		
		// Loop thru each set of coords and store them in above var
		for(int i = 1; i < lineElements.length; ++i) {
			
			// Check if invalid coords
			if(!Double2.validateXYString(lineElements[i])) return null;
			
			// Add coords to list
			coords[i - 1] = new Double2(lineElements[i]);
			
		}
		
		// Ensure last coord matches first otherwise return null;
		if(!coords[0].matches(coords[coords.length - 1])) return null;
		
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
	
	/**
	 * Processes commands to filter/rearrange LinkedList
	 * @param file command file name
	 * @param data LinkedList containing driver data
	 */
	public static void processCommandData(String file, LinkedList<Driver> data) {
		
		// File reader
		Scanner fileScanner = null;
		
		try {
			fileScanner = new Scanner(new FileReader(file));
		} catch(FileNotFoundException e) {
			System.out.println("Error: Command file not found.");
			return;
		}
		
		// Read each line in file
		while(fileScanner.hasNextLine()) {
			
			String line = fileScanner.nextLine();
			
			// Ensure not just whitespace
			if(line.trim().equals("")) continue;
			
			// Command data
			String[] fullCommand = line.split(" ");
			
			// Command name
			String command = fullCommand[0];
			
			// Search for command (case insensitive since it was not specified)
			switch(command.toLowerCase()) {
			case "sort":	// Sort
				commandSort(fullCommand, data);
				break;
			//case "filter":
			default:		// Filter
				commandFilter(line, data);
				break;
			}
			
		}
		
		// Close file scanner
		fileScanner.close();
		
	}
	
	/**
	 * The sort command to be run by the processCommandData command handler
	 * @param fullCmd the entire command split into its args
	 * @param data the driver data linked list
	 */
	private static void commandSort(String[] fullCmd, LinkedList<Driver> data) {
		
		// Ensure valid number of params
		if(fullCmd.length != 3) return;
		
		// Args
		String argSortBy = fullCmd[1],
				argOrder = fullCmd[2];
		
		// Get sort type
		// was not specified if case sensitive or not so i assume case insensitive
		switch(argSortBy.toLowerCase()) {
		case "area":	// area arg
			Driver.setComparisonType(ComparisonType.AREA);
			break;
		case "driver":	// driver arg
			Driver.setComparisonType(ComparisonType.NAME);
			break;
		default:		// invalid sort by argument
			return;
		}
		
		// Get sort order
		switch(argOrder.toLowerCase()) {
		case "asc":		// sort in ascending order
			sortDirection = ComparisonDirection.ASCENDING;
			break;
		case "des":		// sort in descending order
			sortDirection = ComparisonDirection.DESCENDING;
			break;
		default:		// invalid sorting argument
			return;
		}
		
		// If at this point, then command was valid - sort list
		data.sort();
		
		// Print out data (data.toString() already adds extra needed newline)
		System.out.println(data.toString());
		
	}
	
	/**
	 * The filter command (can filter either by area or name)
	 * @param command the entire command line as one string
	 * @param data the driver data linked list
	 */
	private static void commandFilter(String command, LinkedList<Driver> data) {
		
		// Ensure valid number of params
		if(command.split(" ").length == 0) return;
		
		// Check if searching for name
		if(checkIfValidName(command)) {
			// Search for name
			_cmdFilterSearchName(command, data);
		} else if(checkIfDouble(command)) {		// Check if searching for area
			// Search for area
			_cmdFilterSearchArea(Double.parseDouble(command), data);
		}
		
	}
	
	/**
	 * Helper for commandFilter() function.
	 * Filters by name
	 * @param name the name to filter by
	 * @param data linked list containing user data
	 */
	private static void _cmdFilterSearchName(String name, LinkedList<Driver> data) {
		
		// Store # of results in case nothing is found
		int results = 0;
		
		// Loop thru data
		Node<Driver> currNode = data.getFirstNode();
		while(currNode != null) {
			
			// Check if matches our search (case insensitive because not specified)
			if(currNode.getValue().getName().toLowerCase().equals(name.toLowerCase())) {
				System.out.println(currNode.toString());
				++results;
			}
			
			// Go to next node
			currNode = currNode.getNextNode();
			
		}
		
		// Check if no hits
		if(results == 0)
			System.out.println(name + " not found");
		
		// Blank line between outputs as mentioned in instructions
		System.out.println();
		
	}
	
	/**
	 * Helper for commandFilter() function.
	 * Filters by area
	 * @param area the area to filter by
	 * @param data linked list containing user data
	 */
	private static void _cmdFilterSearchArea(double area, LinkedList<Driver> data) {
		
		// Store # of results in case nothing is found
		int results = 0;
		
		// Loop thru data
		Node<Driver> currNode = data.getFirstNode();
		while(currNode != null) {
			
			// Check if matches our search
			if(currNode.getValue().getArea() == area) {
				System.out.println(currNode.toString());
				++results;
			}
			
			// Go to next node
			currNode = currNode.getNextNode();
			
		}
		
		// Check if no hits
		if(results == 0)
			System.out.println(area + " not found");
		
		// Blank line between outputs as mentioned in instructions
		System.out.println();
		
	}
	
	/**
	 * Ensures that the given name is valid.
	 * A valid name may contain spaces (but not in front or at the end), hyphens, and/or apostrophes
	 * @param name the name to check if valid
	 * @return if name is valid or not
	 */
	private static boolean checkIfValidName(String name) {
		// Check if name is valid
		return name.replaceAll(" ", "").matches("[a-zA-Z'-]+") && 	// strip spaces in between words for comparison and see if matches vaid chars
				!name.contains("  ") && 							// disallow double or more spaces to separate words as this indicates invalid format for file
				name.equals(name.trim()); 							// ensure that the name does not have any whitespace before or after itself
	}

}
