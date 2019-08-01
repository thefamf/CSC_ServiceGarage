package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Custom Exception class for checked Exception in Service_Garage.
 * Thrown when a Vehicle attempts to choose a ServiceBay that is empty but is the wrong type for that Vehicle.
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class BayCarMismatchException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for BayCarMismatchException.
	 * @param message String message to pass to parent's constructor
	 */
	public BayCarMismatchException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for BayCarMismatchException.
	 */
	public BayCarMismatchException() {
		super("Exception: Bay Car Mismatch");
	}	

}
