package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class representing a service bay that can accommodate hybrid/electric vehicles only.
 * Subclass of ServiceBay.
 * @author mlee25 Michael Lee
 *
 */
public class HybridElectricBay extends ServiceBay {

	/**
	 * Constructor for a new empty bay for servicing hybrid/electric vehicles according to the current bay numbering.
	 * The prefix for the bayID is "E".
	 * Increments nextNumber after creating a bay. [UC6]
	 */
	public HybridElectricBay() {
		super("E");
	}

	/**
	 * Occupies the service bay with the given Vehicle.
	 * @param vehicle the Vehicle to put into the bay
	 * @throws BayCarMismatchException if the Vehicle attempts to choose a service bay that is the wrong type for that Vehicle
	 * @throws BayOccupiedException if the bay is already occupied
	 */
	@Override
	public void occupy(Vehicle vehicle) throws BayCarMismatchException, BayOccupiedException {
		if (isOccupied()) {
			throw new BayOccupiedException("bay occupied");
		}
		if (vehicle instanceof RegularCar) {
			throw new BayCarMismatchException("bay mismatch");
		} 
		super.occupy(vehicle);
	}
}
