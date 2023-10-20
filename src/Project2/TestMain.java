package Project2;

import java.util.Random;

public class TestMain {
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		
		LinkedList list = new LinkedList();
		
		// fill with rand data
		for(int i = 0; i < 8; ++i) {
			list.addRear(new Node<Driver>(new Driver(rand.nextInt(0, 50))));
		}
		
		System.out.println(list.toString());
		
		//list.swap(5);
		
		list.sortList();
		
		System.out.println(list.toString());
		
	}
	
	public static void sortArray(int[] array) {
		
		boolean didSort = false;
		do {
			// Loop thru entire array
			for(int i = 0; i < array.length - 1; ++i) {
				// check if we should sort
				if(array[i] > array[i + 1]) {
					
				}
			}
		} while(didSort);
		
	}

}
