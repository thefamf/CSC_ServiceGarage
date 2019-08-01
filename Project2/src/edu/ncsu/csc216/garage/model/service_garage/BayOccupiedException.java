package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Custom Exception class for checked Exception in Service_Garage.
 * Thrown when a Vehicle attempts to choose a ServiceBay that is occupied.
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class BayOccupiedException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for BayOccupiedException.
	 * @param message String message to pass to parent's constructor
	 */
	public BayOccupiedException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for BayOccupiedException.
	 */
	public BayOccupiedException() {
		super("Exception: Bay Occupied");
	}	


}
