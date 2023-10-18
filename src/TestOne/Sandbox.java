// Lab19_2B
// Eric Lewellen
// The program creates an array of Item objects, loops through the file to create each object, prints all the objects with the toString method, and then prints the average with the PrintAverage Method. 
package TestOne;

import java.io.File;
import java.util.Scanner;
// E added into class declaration to allow generic type
public class Sandbox {

    public static void main(String[] args) throws Exception {
        // Declares array of 5 Item objects
        Item[] labObjects = new Item[5];
        Scanner readinput;
        
            File input = new File("TestOne/Lab.txt");
            Scanner readInput = new Scanner(input);

        // Loops through file and calls FileToObject
        for (int i = 0; i < labObjects.length; i++) {
            // Declared vars, these are intialized to null to prevent problems below as it relies on readInput(during runtime)
            String n = null;
            String u = null; 
            Integer a = null;
            Double ab = 0.0;
            Double p = null;
                n = readInput.nextLine();
                u = readInput.nextLine();
                if (readInput.hasNextDouble()) {
                    ab = readInput.nextDouble();
                    readInput.nextLine();
                }
                else if (readInput.hasNextInt()) {
                    a = readInput.nextInt();
                    readInput.nextLine();
                }                
                p = readInput.nextDouble();
                if ( i < labObjects.length - 1) {
                    readInput.nextLine();

                }
            if (ab == 0.0) {
                labObjects[i] = new Item<Integer>(n, u, a, p);
            }
            else {
                labObjects[i] = new Item<Double>(n, u, ab, p);
            }

        }
    
        for (Integer x = 0; x < 5; x++) {
            System.out.println();
            System.out.println("Item " + (x).toString());
            System.out.println(labObjects[x].toString());
        }

        System.out.println("\nAverage price for all items: ");
        System.out.println(PrintAverage(labObjects));
    }

  
    public static Double PrintAverage(Item[] items) {
        Double average = 0.0;
        for (int i = 0; i < items.length; i++) {
            average += items[i].pricePerUnit;
        }
        Double size = Double.valueOf(items.length);
        return average / size;
    }
}


/* 
    public static <E extends Number> Item<E> FileToObject( obj) {

    }
*/  






class Item <E extends Number> {
    private String name;
    private String unit;
    private E amount;
    Double price;
    Double pricePerUnit;

    // Default constructor
    public Item(String n, String u, E a, Double p) {
        name = n;
        unit = u;
        amount = a;
        price = p;
        // NOTE: DoubleValue does not work unles E has extends Number in class declaration
        pricePerUnit = price / amount.doubleValue();
    }
// Getter, returns price per unit
    public Double pricePerUnit() {
        return pricePerUnit;
    }

    public String toString() {       
        return "Name: " + name + "\nUnit: " + unit + "\nAmount: " + amount + "\nPrice: " + price + "\nPrice Per Unit " + pricePerUnit;
    }
}