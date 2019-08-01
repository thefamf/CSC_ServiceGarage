package edu.ncsu.csc216.garage.model.vehicle;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * Concrete class to maintain the linked list of Vehicles waiting for service.
 * Includes private inner classes to implement the linked list and maintain a cursor that iterates through the list.
 * @author mlee25 Michael Lee
 *
 */
public class VehicleList {

	/** Reference to the first list node */
	private static Node head;

	/**
	 * Constructor to create a list of vehicles from a Scanner.
	 * Scanner will read in each Vehicle from the file input and add the Vehicle to the linked list of Nodes.
	 * @param s the Scanner to read in file input
	 * @throws IllegalArgumentException if s is null
	 */
	public VehicleList(Scanner s) throws IllegalArgumentException {
		this();
		if (s == null) {
			throw new IllegalArgumentException("null scanner");
		}
		while (s.hasNextLine()) {
			Vehicle v = null;
			int type = -1;
			int tier = 0;
			String info = null;
			String line = s.nextLine();
			Scanner linescanner = new Scanner(line);
			String next = linescanner.next();
			if (next.toLowerCase().equals("r")) {
				type = 0;
			}
			if (next.toLowerCase().equals("e")) {
				type = 1;
			}
			tier = linescanner.nextInt();
			info = linescanner.useDelimiter("\\Z").next();
			try {
				if (type == 0) {
					v = new RegularCar(info, tier);
				}
				if (type == 1) {
					v = new HybridElectricCar(info, tier);
				}
				add(v);
			} catch (BadVehicleInformationException e) {
				// skip line
			}
		}
		
	}
	
	/**
	 * Parameterless constructor.
	 * Creates an empty list of Vehicles.
	 */	
	public VehicleList() {
		head = null;
	}
	
	/**
	 * Returns a SimpleIterator initialized to point to the first element in the list.
	 * Used to process Vehicles for ServiceManager.fillServiceBays(). [UC7, S1]
	 * @return a Cursor pointing to the first element in the VehicleList
	 */
	public SimpleIterator<Vehicle> iterator() {
		Cursor cursor = new Cursor();
			return cursor;
	}
	
	/**
	 * Adds the given Vehicle to the VehicleList in the proper order based on first, priority, and then order of assignment to that priority.
	 * @param vehicle the Vehicle to add
	 */
	public void add(Vehicle vehicle) {
//		SimpleIterator<Vehicle> cursor = new Cursor();
//		cursor = iterator();
		Node current = head;
		Node previous = null;

		while (current != null && current.v.compareToTier(vehicle) >= 0) {
			previous = current;
			current = current.next;
		}
		if (current == head) {
			head = new Node(vehicle, head);
		} else {
			previous.next = new Node(vehicle, current);
		}
		
	}	
	
	
	/**
	 * Getter for the Vehicle that appears in the filtered list in the given position.
	 * Calls Vehicle.meetsFilter().  [UC5]
	 * @param filter the prefix for the owner's last name
	 * @param position the position to get
	 * @return the Vehicle at the given position
	 */
	public Vehicle get(String filter, int position) {
//		SimpleIterator<Vehicle> cursor = new Cursor();
//		cursor = iterator();
		Node current = head;
		Vehicle result = null;

		if (position == 0) {
			while (current != null && !current.v.meetsFilter(filter)) {
				current = current.next;
			}
			if (current != null && current.v.meetsFilter(filter)) {
				return current.v;
			}
		}

		while (current != null && position > 0) {
			if (current.v.meetsFilter(filter)) {
				position--;
			} 
			current = current.next;					
		}
		if (current != null && current.v.meetsFilter(filter)) {
			result = current.v;
		}
		return result;
	}

	/**
	 * Removes the Vehicle that appears in the filtered list in the given position.
	 * Calls Vehicle.meetsFilter().  [UC5]
	 * @param filter the prefix for the owner's last name
	 * @param position the position to remove
	 * @return the Vehicle that was removed
	 */
	public Vehicle remove(String filter, int position) {
		Node current = head;
		Node previous = null;
		Vehicle result = null;

		if (position == 0) {
			while (current != null && !current.v.meetsFilter(filter)) {
				previous = current;
				current = current.next;
			}
			if (current != null && current.v.meetsFilter(filter)) {
				result = current.v;
				head = head.next;
				return result;
			}
		}

		while (current != null && position > 0) {
			if (current.v.meetsFilter(filter)) {
				position--;
			} 
			previous = current;
			current = current.next;					
		}
		if (current != null && current.v.meetsFilter(filter)) {
			result = current.v;
			previous.next = current.next;
		}
		return result;
	}
	
//	/**
//	 * private method to get the Node trailing a given Node in the filtered list of Vehicles.
//	 * @param filter the prefix for the owner's last name
//	 * @param position the position of the referenced Node
//	 * @return the trailing Node that references the Node at the given position
//	 */
//	private Node findTrailingNode(String filter, int position) {
//		//TODO
//		return null;
//	}

	/**
	 * Returns a String representation of the Vehicles that meet the filter. [UC5, E1]
	 * Each substring for a Vehicle is terminated by a newline. [UC1, S3]
	 * @param filter the prefix of the owner's last name
	 * @return the String representation of Vehicles
	 */
	public String filteredList(String filter) {
		String result = "";
		Node current = head;

		if (current == null) {
			return null;
		}
		while (current != null) {
			if (current.v.meetsFilter(filter)) {
				result += current.v.toString() + "\n";
			}
			current = current.next;
		}
		return result;
	}
	
	/**
	 * Private inner class Node to implement a linked list.
	 * Each Node contains a Vehicle element and a reference to the next Node in the list.
	 * @author mlee25 Michael Lee
	 */
	private class Node {	
		/** data member */
		public Vehicle v;
		/** reference to next list element */
		public Node next;
		
		/**
		 * Constructor for Node inner class for VehicleList.
		 * @param v the Vehicle element
		 * @param node the reference to the next node in the list
		 */
		public Node(Vehicle v, Node node) {
			this.v = v;
			this.next = node;
		}
	}
	
	/**
	 * Private inner class to represent the Cursor for the current location of the iterator within the list.
	 * Implements the SimpleIterator interface.
	 * @author mlee25 Michael Lee
	 *
	 */
	private class Cursor implements SimpleIterator<Vehicle> {
		
		/** private instance of Node to serve as cursor */
		private Node cursorNode;
		
		/** Private constructor */
		private Cursor() {
			cursorNode = head;
		}
		
		/**
		 * Checks if the iteration has more elements.
		 * @return true if the iteration has more elements
		 * @throws NoSuchElementException if the iteration is empty
		 */
		@Override
		public boolean hasNext() throws NoSuchElementException {
			if (cursorNode == null) {
				throw new NoSuchElementException();
			}
			return !(cursorNode.next == null);
		}

		/**
		 * Getter for the vehicle in the current node.
		 * Advances cursorNode to next.
		 * @return the next element in the iteration
		 */
		@Override
		public Vehicle next() {
			Vehicle result = null;
			result = cursorNode.v;
			if (cursorNode.next != null) {
				cursorNode = cursorNode.next;
			}
			return result;
		}
	}
}
