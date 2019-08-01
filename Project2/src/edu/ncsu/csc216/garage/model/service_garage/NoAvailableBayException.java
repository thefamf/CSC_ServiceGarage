package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Custom Exception class for checked Exception in Service_Garage.
 * Thrown when a Vehicle attempts to choose a ServiceBay and all appropriate bays are occupied.
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class NoAvailableBayException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for NoAvailableBayException.
	 * @param message String message to pass to parent's constructor
	 */
	public NoAvailableBayException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for NoAvailableBayException.
	 */
	public NoAvailableBayException() {
		super("Exception: No Available Bay");
	}	
}
