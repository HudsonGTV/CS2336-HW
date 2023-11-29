/**
 * Author: Hudson Green
 * NetID: HKG230000
 */

package Project3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// Scanner for file names
		Scanner scannerIn = new Scanner(System.in);
		
		// Store file names
		String galaxyFile = "";
		String pilotFile = "";
		
		// Get galaxy file name
		System.out.println("Enter galaxy file name: ");
		galaxyFile = scannerIn.nextLine();
		//galaxyFile = "g.txt";
		
		// Get pilot file name
		System.out.println("Enter pilot file name: ");
		pilotFile = scannerIn.nextLine();
		//pilotFile = "p.txt";
		
		// Close scanner
		scannerIn.close();
		
		// Generate a graph and pilot data
		Graph galaxy = generateGalaxyGraph(galaxyFile);
		ArrayList<Pilot> pilots = generatePilots(pilotFile, galaxy);
		
		// Exit if invalid files
		if(galaxy == null || pilots == null) return;
		
		// Output pilot data using graph
		//System.out.println(galaxy.toString());
		
		// Print out pilots
		//System.out.println("ORIGINAL OUT:");
		//printPilots(pilots);
		
		// Sort pilots
		sortPilots(pilots);
		
		// Print pilots
		//System.out.println("\nFINAL SORTED OUT:");
		//printPilots(pilots);
		
		// Output pilots to patrols.txt
		outputPilotPatrolData(pilots, "patrols.txt");
		
	}
	
	/**
	 * Outputs the pilot data to a file
	 * @param pilots The pilot list
	 * @param outputFile The file to output to
	 */
	public static void outputPilotPatrolData(ArrayList<Pilot> pilots, String outputFile) {
		
		PrintWriter fWriter = null;
		
		// Attempt to write to file
		try {
			fWriter = new PrintWriter(outputFile, "UTF-8");
		} catch(IOException e) {
			System.out.println("Error: IOException. Printing stack trace...");
			e.printStackTrace();
			return;
		}
		
		// Output pilot data to file
		for(Pilot p : pilots) {
			// Format: <name><tab char><path weight><tab char><valid/invalid>
			fWriter.println(p.toString());
		}
		
		// Close file writer
		fWriter.close();
		
	}
	
	/**
	 * Generates a galaxy map from given file
	 * @param galaxyFile the name of the file containing galaxy data
	 * @return a graph with the galaxy data in it
	 */
	public static Graph generateGalaxyGraph(String galaxyFile) {
		
		Scanner fileScanner = null;
		
		// Attempt to open file
		try {
			fileScanner = new Scanner(new FileReader(galaxyFile));
		} catch(FileNotFoundException e) {
			System.out.println("Error: galaxy file not found!");
			return null;
		}
		
		// Planet relationships
		ArrayList<String> planetRelationships = new ArrayList<String>();
		// Create graph
		Graph out = new Graph();
		
		// Loop thru entire file and generate vertices
		while(fileScanner.hasNextLine()) {
			
			String line = fileScanner.nextLine();
			
			// Ensure line isn't blank
			if(line.length() == 0) continue;
			
			// Create a vertex with pilot name and add it to list of vertices
			out.addVertex(new Vertex(line.split(" ")[0]));
			
			// Store planet relationships
			planetRelationships.add(line);
			
		}
		
		// Close file
		fileScanner.close();
		
		// Add relationships to vertices
		for(int i = 0; i < planetRelationships.size(); ++i) {
			
			// Example: {"v1Name", "v2Name,#", "v2.1Name,#", ...}
			String[] planetRelations = planetRelationships.get(i).split(" ");
			
			// Our main planet
			String v1Name = planetRelations[0];
			
			// Loop thru each planet and link them together
			for(int j = 1; j < planetRelations.length; ++j) {
				// Example: {"v2Name", "weight#"}
				String[] currPlanet = planetRelations[j].split(",");
				// Add edge b/t both planets with given weight
				boolean debugRel = out.addRelationship(v1Name, currPlanet[0], Integer.parseInt(currPlanet[1]), true);
				if(!debugRel) System.out.println("error");
			}
			
		}
		
		// Return the generated graph
		return out;
		
	}
	
	/**
	 * Generates a list of pilots
	 * @param pilotFile the file the pilot data is in
	 * @param galaxy the gaph of the galaxy
	 * @return a list of pilots
	 */
	public static ArrayList<Pilot> generatePilots(String pilotFile, Graph galaxy) {
		
		Scanner fileScanner = null;
		
		// Attempt to open pilot file
		try {
			fileScanner = new Scanner(new FileReader(pilotFile));
		} catch(FileNotFoundException e) {
			System.out.println("Error: pilot file not found!");
			return null;
		}
		
		ArrayList<Pilot> out = new ArrayList<Pilot>();
		
		// Read pilot file
		while(fileScanner.hasNextLine()) {
			// Generate a pilot
			Pilot p = generatePilot(fileScanner.nextLine(), galaxy);
			// Add a pilot with data if pilot exists
			if(p != null) out.add(p);
		}
		
		// Close file scanner
		fileScanner.close();
		
		// Return the pilot list
		return out;
		
	}
	
	/**
	 * This is a helper function for generatePilots
	 * @param pilotLine The line in the file containing a pilot
	 * @param galaxy the galaxy map to use to check if valid path
	 * @return a Pilot with total path weight and if path is valid or not
	 */
	private static Pilot generatePilot(String pilotLine, Graph galaxy) {
		
		// Check if line is blank
		if(pilotLine.length() == 0) return null;
		
		// Pilot line: Name p1 p2 ... pn
		
		// Split line into multiple datafields
		String[] pilotData = pilotLine.split(" ");
		int totalWeight = 0;
		boolean isValidPath = true;
		
		// Check if there are not planets to traverse
		if(pilotData.length < 2) return new Pilot(pilotData[0], 0, true);
		
		// Get starting planet
		Vertex currPlanet = galaxy.getVertex(pilotData[1]);
		
		// Check if the starting planet exists
		if(currPlanet == null) isValidPath = false;
		
		// Traverse the galaxy to ensure it is a valid path and add total weight
		for(int i = 2; i < pilotData.length; ++i) {
			
			// Check in case invalid planet is passed
			if(currPlanet == null) {
				isValidPath = false;
				break;
			}
			
			// Attempt to get next planet according to file line
			Edge neighborPlanet = currPlanet.getNeighborEdge(pilotData[i]);
			
			// Check if invalid path
			if(neighborPlanet == null) {
				isValidPath = false;
				break;
			}
			
			// Valid path beyond this point
			// Add path weight and set curr planet to planet in edge
			totalWeight += neighborPlanet.getWeight();
			currPlanet = neighborPlanet.getDestination();
			
		}
		
		// Set weight to 0 if invalid (redundant, but unsure if will lose points if internal weight is not 0)
		if(!isValidPath) totalWeight = 0;
		
		// Generate pilot with data
		return new Pilot(pilotData[0], totalWeight, isValidPath);
				
	}
	
	/**
	 * This function sorts the pilots by weight (or name if weight is same) using a bubble sort
	 * @param pilots The list of pilots to be sorted
	 */
	public static void sortPilots(ArrayList<Pilot> pilots) {
		
		// Bubble sort
		for(int i = 0; i < pilots.size() - 1; ++i) {
			
			boolean didSwap = false;
			
			// Loop thru entire list
			for(int j = 0; j < pilots.size() - 1; ++j) {
				// Check if next node is smaller than current
				if(pilots.get(j).compareTo(pilots.get(j + 1)) > 0) {
					// Swap
					Collections.swap(pilots, j, j + 1);
					didSwap = true;
				}
			}
			
			// If we never swapped, then its already in order
			if(!didSwap) break;
			
		}
		
	}
	
	/**
	 * Debug function. Prints out the pilots
	 * @param pilots the list of pilots to print
	 */
	public static void printPilots(ArrayList<Pilot> pilots) {
		for(Pilot p : pilots) {
			System.out.println(p);
		}
	}

}
