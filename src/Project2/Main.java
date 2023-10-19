/**
 * Author: Hudson Green
 * NetID: HKG230000
 * Summary: This program calculates the area of an n-sided shape given the coordinates for that shape via the summation equation given in the project 1 instructions
**/

// For teacher: You may have to remove this line if not running in Eclipse under a package name Project1
package Project2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	
	// Output file
	private static final String OUTPUT_FILE = "pilot_areas.txt";
	
	// Config options
	private static final int MAX_PILOT_COUNT = 20;
	private static final int MAX_COORD_COUNT = 16;
	
	/*public static void main(String[] args) throws Exception {
		
		// Variables to store pilot info
		String[] pilotNames = new String[MAX_PILOT_COUNT];
		// [pilot][coord#][coord (0=x, 1=y)]
		double[][][] pilotData = new double[MAX_PILOT_COUNT][MAX_COORD_COUNT][2];
		
		// Prompt user for file name
		System.out.println("Enter input file name: ");
		
		// Get file input data
		Scanner scannerIn = new Scanner(System.in);
		
		// Get name and close scanner
		String fileName = scannerIn.nextLine();
		scannerIn.close();
		
		// Load file data
		LoadFileData(fileName, pilotNames, pilotData);
		
		// Calculate area of each pilot and output it to a file
		OutputCalculatedData(pilotNames, pilotData);
		
		// Notify user that program has completed
		System.out.println("Done!");
		
	}*/
	
	/**
	 * Loads data from file and stores it in passed array args - assume file is in correct format
	 * 
	 * @param fileName - the name of the file to load
	 * @param pilotNames - loads the pilot names from the specified file into this array
	 * @param pilotData - loads the pilot data from the specified file into this array
	 * @throws IOException 
	**/
	public static void LoadFileData(String fileName, String[] pilotNames, double[][][] pilotData) throws IOException {
		
		// File reader
		Scanner fileScanner = null;
		
		// Attempt to open the file
		try {
			fileScanner = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found. Printing stack trace...");
			e.printStackTrace();
			return;
		}
		
		for(int i = 0; fileScanner.hasNextLine(); ++i) {
			
			String nextLine = fileScanner.nextLine();
			if(nextLine.length() < 2) break;
			
			// Split line into substrings
			String[] lineStr = nextLine.split(" ");
			
			// 1st element is the pilot name
			pilotNames[i] = lineStr[0];
			
			// Parse coords
			for(int j = 1; j < lineStr.length; ++j) {
				// current pilot
				// pilotData[i][j - 1][0/1]
				
				// Coords
				String[] coords = lineStr[j].split(",");
				// Put coords in data
				pilotData[i][j - 1][0] = Double.parseDouble(coords[0]);
				pilotData[i][j - 1][1] = Double.parseDouble(coords[1]);
				
			}
			
		}
		
		// Close file when we are done
		fileScanner.close();
		
	}
	
	/**
	 * Calculates shape area and outputs it to a file
	 * 
	 * @param pilotNames - an array of the pilot names
	 * @param pilotData - an array of the pilot coord data
	**/
	public static void OutputCalculatedData(String[] pilotNames, double[][][] pilotData) {
		
		PrintWriter fwriter = null;
		
		// Attempt to write to file
		try {
			fwriter = new PrintWriter(OUTPUT_FILE, "UTF-8");
		} catch(IOException e) {
			System.out.println("Error: IOException. Printing stack trace...");
			e.printStackTrace();
			return;
		}
		
		// Print pilot name and data
		for(int i = 0; pilotNames[i] != null && i < pilotNames.length; ++i) {
			// Format: <name><tab char><area>
			fwriter.println(pilotNames[i] + "	" + shapeArea(pilotData[i]));
		}
		
		// Close the file
		fwriter.close();
		
	}
	
	/**
	 * Returns total area of a given shape with n sides.
	 * Last coord must match starting coord
	 * 
	 * @param coords - an array of coords for the shape
	 * @return area of given coords (rounded to 2 decimal places)
	**/
	public static double shapeArea(double[][] coords) {
		
		// Store area
		double sigmaVal = 0;
		
		// Iterate thru each coord and calculate sigma
		// Checks if coords ahead are starting coords (inclusive of n - 2, hence i + 1)
		for(
			int i = 0; 
			!(
				coords[0][0] == coords[i + 1][0] && 
				coords[0][1] == coords[i + 1][1]		// checks if we've reached the end (when the curr coords are equal to the first coords)
			) && i <= MAX_COORD_COUNT - 1; 				// ensure that we do not crash if user fails to have a matching start and end point
			++i
		) {
			// Calculate sigmaVal (x_i+1 + x_i) * (y_i+1 - y_i)
			sigmaVal += (coords[i + 1][0] + coords[i][0]) * (coords[i + 1][1] - coords[i][1]);
		}
		
		// Calculate area of shape rounded to 2 decimal places
		return Math.round((Math.abs(sigmaVal) / 2.0) * 100.0) / 100.0;
		//return Math.abs(sigmaVal) / 2.0;	// non-rounded area
		
	}

}
