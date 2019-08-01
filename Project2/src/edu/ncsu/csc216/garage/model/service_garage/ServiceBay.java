package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class representing a regular service bay in the Garage, which can handle both regular and hybrid/electric Vehicles.
 * Each ServiceBay has an ID, knows if it is occupied, and if not empty, a Vehicle.
 * @author mlee25 Michael Lee
 *
 */
public class ServiceBay {

	/** field to indicate true if occupied */ 
	private boolean occupied;
	/** the ID value of the ServiceBay, beginning with a '1' for regular bays */
	private String bayID;
	/** instance variable for the Vehicle currently occupying the bay */
	private Vehicle myVehicle;
	/** static field to hold the next available bay number */
	private static int nextNumber;
	
	/**
	 * Static method to reset the ServiceBay numbering from 1.
	 * Called by the Garage constructor to reset the bays.
	 */
	public static void startBayNumberingAt101() {
		nextNumber = 1;
	}
	
	/**
	 * Constructor for a new empty regular ServiceBay according to the current bay numbering.
	 * The prefix for the bayID is "1".
	 * Increments nextNumber after creating a bay. [UC6]
	 */
	public ServiceBay() {
		this("1");
	}
	
	/**
	 * Constructor for a new empty ServiceBay using the first non-whitespace character in the given prefix.
	 * The prefix is "1" if there is no such character.
	 * Increments nextNumber after creating a bay. [UC6, S3]
	 * @param prefix the prefix used to start the bayID.
	 */
	public ServiceBay(String prefix) {
		this.occupied = false;
		this.myVehicle = null;

		char c = firstChar(prefix);
		String numberString = null;
		if (nextNumber < 10) {
			numberString = "0" + nextNumber;
		} else {
			numberString = Integer.toString(nextNumber);
		}
		
		bayID = Character.toString(c) + numberString;
		nextNumber++;
	}
	
	/**
	 * Getter for bayID.
	 * @return the bayID
	 */
	public String getBayID() {
		return this.bayID;
	}
	
	/**
	 * Getter for occupied value.
	 * @return true if occupied
	 */
	public boolean isOccupied() {
		return myVehicle != null;
	}
	
	/**
	 * Occupies the ServiceBay with the given Vehicle.
	 * @param vehicle the Vehicle to put into the bay
	 * @throws BayOccupiedException if the bay is already occupied
	 * @throws BayCarMismatchException if the Vehicle attempts to choose a service bay that is the wrong type for that Vehicle
	 */
	public void occupy(Vehicle vehicle) throws BayOccupiedException, BayCarMismatchException {
		if (occupied) {
			throw new BayOccupiedException("bay occupied");
		} else {			
			myVehicle = vehicle;
			occupied = true;
		}
	}
	
	/**
	 * Removes the Vehicle currently in the ServiceBay and returns it.
	 * @return the Vehicle removed
	 */
	public Vehicle release() {
		this.occupied = false;
		Vehicle result = myVehicle;
		myVehicle = null;
		return result;
	}
	
	/**
	 * Returns the String representation of the ServiceBay. [UC7, S4]
	 * @return the formatted String for the ServiceBay (bayID, license, owner) 
	 */
	public String toString() {
		String result = bayID + ": ";

		//left justified width 9 or "EMPTY"
		if (occupied) {
			result += String.format("%-9s", myVehicle.getLicense());
			result += myVehicle.getName();
		} else {
			result += "EMPTY";
		}
		return result;
	}
	
	/**
	 * Private method to get first non-whitespace character in a String.
	 * @param string the String parameter
	 * @return the first non-whitespace char value
	 */
	private char firstChar(String string) {
		char result = '1';
		string = string.trim();
		if (string != null && !string.equals("")) {
			result = string.charAt(0);
		}
		return result;
	}
}
