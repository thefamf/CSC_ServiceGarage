package edu.ncsu.csc216.garage.model.dealer;

import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
//import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.VehicleList;

/**
 * Concrete class that implements the Manageable interface.  
 * Controller class for MVC pattern and interfaces with the UI package classes RepairGarageGUI and RepairGarageConsole.
 * Handles adding and removing Vehicles from the waiting list, adding new ServiceBays, and processing Vehicles through the ServiceBays.
 * Contains a Garage to maintain an array list of ServiceBays and a VehicleList to maintain a linked list of Vehicles.
 * @author mlee25 Michael Lee
 */
public class ServiceManager implements Manageable {

	/** private instance of VehicleList */
	private VehicleList waitingCars;
	/** private instance of Garage */
	private Garage myGarage;
	
	/**
	 * Creates a ServiceManager with no vehicles on the waiting list.
	 * If the filename specified by the user does not exist, the program exits with an error dialog [UC1, E1]
	 * //TODO comment
	 */
	public ServiceManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the list of vehicles awaiting service with data from the Scanner to read a file.
	 * The user specifies the file as a command line argument or through the file chooser.
	 * The file is processed one line at a time and any line that fails to meet the specified format is ignored. [UC1, S3]
	 * Initializes the list of ServiceBays with 8 empty bays [UC1, S4]
	 * @param s //TODO
	 */
	public ServiceManager(Scanner s) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Fills the open, empty ServiceBays with Vehicles on the waiting list. [UC7]
	 */
	@Override
	public void fillServiceBays() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Removes the Vehicle that appears in the filtered list at the given position of the linked list.
	 * Calls Vehicle.meetsFilter() to filter the list. [UC5]
	 * @param filter the prefix of the owner's last name
	 * @param position the position in the VehicleList to remove
	 * @return the Vehicle removed
	 */
	@Override
	public Tiered remove(String filter, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes the Vehicle in the specified ServiceBay and returns it. [UC3]
	 * Calls Garage.release().
	 * @param bay the index of the ServiceBay to release
	 * @return the Vehicle removed
	 */
	@Override
	public Tiered releaseFromService(int bay) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds a ServiceBay in accordance with [UC6].
	 * Calls Garage.addRepairBay().
	 */
	@Override
	public void addNewBay() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns a String representation of all Vehicles that meet the filter.
	 * Calls VehicleList.filteredList().  Each substring for a Vehicle is terminated by a newline.
	 * @param filter the prefix of the owner's last name
	 * @return the String representation of the filtered list
	 */
	@Override
	public String printWaitList(String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns a String representation of open ServiceBays.
	 * @return the String representation of open ServiceBays
	 */
	@Override
	public String printServiceBays() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Adds a Vehicle to the VehicleList to the end of the sublist of Vehicles with tiers that are at least as high as the newly added Vehicle.
	 * Calls putOnWaitingList(Tiered).
	 * @param type the type of vehicle (regular or hybrid/electric)
	 * @param license the Vehicle's license
	 * @param owner the owner's name
	 * @param tierIndex the Vehicle's tier status
	 */
	@Override
	public void putOnWaitingList(String type, String license, String owner, int tierIndex) {
		// TODO Auto-generated method stub
	}

	/**
	 * Puts an item in the list of Vehicles awaiting service.
	 * @param v  The item to put on the waiting list
	 */
	@Override
	public void putOnWaitingList(Tiered v) {
		// TODO Auto-generated method stub		
	}
	

	/**
	 * Getter for the Vehicle at the specified position on the filtered waiting list.
	 * Calls VehicleList.filteredList().
	 * @param filter the prefix of the owner's last name
	 * @param position the position of the Vehicle to return
	 * @return the Vehicle at the specified position
	 */
	@Override
	public Tiered getWaitingItem(String filter, int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
