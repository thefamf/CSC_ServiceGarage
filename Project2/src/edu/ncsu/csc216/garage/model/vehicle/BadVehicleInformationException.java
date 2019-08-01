package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Custom Exception class for checked Exception in Vehicle.
 * Thrown when a user attempts to add/edit a Vehicle with invalid information (type, name, license,tier).
 * Uses default serialVersionUID of 1L.
 * @author mlee25 Michael Lee
 *
 */
public class BadVehicleInformationException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized constructor for BadVehicleInformationException.
	 * @param message String message to pass to parent's constructor
	 */
	public BadVehicleInformationException(String message) {
		super(message);
	}

	/**
	 * Parameterless constructor for BadVehicleInformationException.
	 */
	public BadVehicleInformationException() {
		super("Exception: Bad Vehicle Information");
	}	
}
