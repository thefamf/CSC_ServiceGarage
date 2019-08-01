package edu.ncsu.csc216.garage.model.vehicle;

import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Abstract class representing a Vehicle.
 * Implements the Tiered interface to compare Vehicles by Tier.
 * Contains two constructors and private setters for Name and License.
 * Includes abstract method for pickServiceBay() to be implemented by RegularCar and HybridElectricCar objects.
 * @author mlee25 Michael Lee
 *
 */
public abstract class Vehicle implements Tiered {

	/** the Vehicle's license */
	private String license;
	/** the owner's name in the format last name, first name */
	private String name;
	/** the integer value for the tier status */
	private int tier;
	/** an array for the String values associated with each tier status, with 0 equal to None */
	public static final String[] CUSTOMER_TIER = {"None", "Silver", "Gold", "Platinum"};
	
	/** 
	 * Constructor for Vehicle that takes a license, owner's name, and tier status as parameters.
	 * Checks preconditions for license and owner information (cannot be blank or all whitespace, or license greater than 8 characters).
	 * [UC2, E2, E3, E4]
	 * @param license the Vehicle's license
	 * @param owner the Vehicle's owner
	 * @param status the Vehicle's tier status as an integer
	 * @throws BadVehicleInformationException if the license or owner are not valid
	 */
	public Vehicle(String license, String owner, int status) throws BadVehicleInformationException {
		setLicense(license);
		setName(owner);
		try {
			setTier(status);			
		} catch (IllegalArgumentException e) {
			// setTier() checks for tier between 0-3
		}
	}
	
	/**
	 * Constructor for Vehicle that takes a String with license and owner's name, and tier status as parameters.
	 * @param info the String containing license followed by owner's name
	 * @param status the tier status
	 * @throws BadVehicleInformationException if the license or owner are not valid
	 */
	public Vehicle(String info, int status) throws BadVehicleInformationException {
		Scanner scanner = new Scanner(info);
//		scanner.useDelimiter("\\s+");
		
		String licenseString = null;
		if (scanner.hasNext()) {
			licenseString = scanner.next();
		}
		setLicense(licenseString);
		
		scanner.useDelimiter("(\\b|\\B)");
		String nameString = "";
		while (scanner.hasNext()) {
			nameString += scanner.next();
		}
		setName(nameString);
		
		scanner.close();

		try {
			setTier(status);			
		} catch (IllegalArgumentException e) {
			// setTier() checks for tier between 0-3
		}
	}
	
	/**
	 * Abstract method for picking a service bay to be implemented by the concrete Vehicle classes.
	 * @param garage the Garage with ServiceBays that the Vehicle will pick from
	 * @throws NoAvailableBayException [UC7, S1]
	 */
	public abstract void pickServiceBay(Garage garage) throws NoAvailableBayException;
	
	/**
	 * Checks if the filter is a prefix to this owner's last name.
	 * The check is case insensitive. 
	 * A filter of null or an empty string returns true.
	 * @param filter the filter to check
	 * @return true if filter is a prefix to owner's last name or blank
	 */
	public boolean meetsFilter(String filter) {
		if (filter == null || filter.equals("")) {
			return true;
		}
		String prefix = filter.trim();
		return this.name.toLowerCase().startsWith(prefix.toLowerCase());
	}

	/**
	 * Getter for license.
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * Private setter for license. Method handles trimming whitespace.
	 * @param license the license to set
	 * @throws BadVehicleInformationException if license is null or blank, or greater than 8 characters
	 */
	private void setLicense(String license) throws BadVehicleInformationException {
		if (license == null || license.trim().equals("")) {
			throw new BadVehicleInformationException("License cannot be blank.");
		}
		String licenseString = license.trim();
		if (licenseString.length() > 8) {
			throw new BadVehicleInformationException("License cannot be more than 8 characters.");
		}
		this.license = licenseString;
	}

	/**
	 * Getter for name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Private setter for name.  Method handles trimming whitespace.
	 * @param name the name to set
	 * @throws BadVehicleInformationException if owner name is null or blank
	 */
	private void setName(String name) throws BadVehicleInformationException {
		if (name == null || name.trim().equals("")) {
			throw new BadVehicleInformationException("Owner name cannot be blank.");
		}
		String nameString = name.trim();
		this.name = nameString;
	}

	/**
	 * Getter for tier.
	 * @return the tier
	 */
	public int getTier() {
		return tier;
	}

	/**
	 * Setter for tier.
	 * @param tier the tier to set
	 * @throws BadVehicleInformationException if the tier is less than 0 or greater than 3
	 */
	public void setTier(int tier) throws BadVehicleInformationException {
		if (tier < 0 || tier > 3) {
			throw new BadVehicleInformationException("invalid tier");
		}
		this.tier = tier;
	}

	/**
	 * Returns the String representation of the Vehicle. [UC1, S3]
	 * Formatted with fixed widths for type of vehicle, service tier, and license, and followed by the owner's name.
	 * @return the String for the Vehicle
	 */
	public String toString() {
		String result = null;
		String tierString = String.format("%-10s", CUSTOMER_TIER[tier]);
		String licenseString = String.format("%-10s", this.license);
		result = tierString + licenseString + this.name;
		return result;
	}
	
	/**
	 * Method to compare the tier status of this object with another tiered object.
	 * Returns 0 if the tier statuses match, a negative number if the tier status of this object is less than the other, and a positive number if the tier status of this object is greater.
	 * @param vehicle the other Tiered object to compare
	 * @return an integer value for the comparison, 0 if equal, a negative value if this is less than the other, and a positive value if this is greater than the other
	 */
	public int compareToTier(Tiered vehicle) {
		int result = 0;
		if (this.tier > vehicle.getTier()) {
			result = 1;
		}
		if (this.tier < vehicle.getTier()) {
			result = -1;
		}
		return result;
	}
	
}
