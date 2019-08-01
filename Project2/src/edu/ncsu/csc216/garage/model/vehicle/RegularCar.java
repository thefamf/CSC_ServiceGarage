package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Concrete class representing a Vehicle that cannot be serviced in a HybridElectricBay.
 * @author mlee25 Michael Lee
 *
 */
public class RegularCar extends Vehicle {

	/**
	 * Constructor for a RegularCar that takes a license, owner's name, and tier status as parameters.
	 * Calls super constructor which checks preconditions for parameters.
	 * @param license the Vehicle's license
	 * @param owner the Vehicle's owner
	 * @param status the Vehicle's tier status as an integer
	 * @throws BadVehicleInformationException if the license, owner or status are not valid
	 */
	public RegularCar(String license, String owner, int status) throws BadVehicleInformationException {
		super(license, owner, status);
	}
	
	/**
	 * Constructor for a RegularCar that takes a String with license and owner's name, and tier status as parameters.
	 * Calls super constructor which checks preconditions for parameters.
	 * @param info the String containing license followed by owner's name
	 * @param status the tier status
	 * @throws BadVehicleInformationException if the info or status are not valid
	 */
	public RegularCar(String info, int status) throws BadVehicleInformationException {
		super(info, status);
	}
	
	/**
	 * Picks a ServiceBay for a RegularCar.  
	 * Goes through the list of ServiceBays, starting at the front, searching for an empty one.
	 * @param garage the Garage with ServiceBays to pick from
	 * @throws NoAvailableBayException [UC7, S1]
	 */
	public void pickServiceBay(Garage garage) throws NoAvailableBayException {
		//TODO
	}
	
	/**
	 * String representation of the Vehicle, prefixed by "R".
	 * @return the String representation of the Vehicle
	 */
	public String toString() {
		String result = null;
		result = "R " + super.toString();
		return result;
	}
}
