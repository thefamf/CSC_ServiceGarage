package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class representing a Garage that manages an array of ServiceBays.
 * Every Garage knows its size and number of empty bays, and can add bays and release a Vehicle from a bay.
 * @author mlee25 Michael Lee
 *
 */
public class Garage {

	/** constant for the max number of bays in a Garage */
	private static final int MAX_BAYS = 30;
	/** constant for the initial number of bays in a Garage */
	private static final int DEFAULT_SIZE = 8;
	/** variable to keep track of the current number of bays */
	private int size;
	/** array for ServiceBays */
	private ServiceBay[] bays;
	
	/**
	 * Constructor for Garage that creates an array list of 8 empty service bays.
	 */
	public Garage() {
		ServiceBay.startBayNumberingAt101();
		this.bays = new ServiceBay[MAX_BAYS];
		initBays(DEFAULT_SIZE);
	}
	
	
	/**
	 * Adds a repair bay such that at least 1/3 of the bays in the garage are HybridElectricBays after adding a bay.
	 * [UC6]
	 */
	public void addRepairBay() {
		ServiceBay newbay = null;
		if (size < 30) {
			if (size % 3 == 0) {
				newbay = new HybridElectricBay();
				bays[size++] = newbay;
			} else {
				newbay = new ServiceBay();
				int index = size;
				while (index > 0) {
					bays[index] = bays[index - 1];
					index--;
				}
				bays[0] = newbay;
				size++;
			}
		}
	}

	/**
	 * Getter for number of currently empty bays.
	 * @return the number of empty bays
	 */
	public int numberOfEmptyBays() {
		int result = 0;
		for (int i = 0; i < size; i++) {
			if (!bays[i].isOccupied()) {
				result++;
			}
		}
		return result;
	}

	/**
	 * Getter for the ServiceBay object at the given index.
	 * @param index the index of the ServiceBay to get
	 * @return the ServiceBay
	 * @throws IllegalArgumentException if the index is less than 0 or greater than or equal to the number of currently open bays
	 */
	public ServiceBay getBayAt(int index) throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("invalid bay index");
		}
		return bays[index];
	}
	
	/**
	 * Getter for the total number of open service bays.
	 * @return the size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Removes the Vehicle currently in the ServiceBay at the given index and returns it.
	 * Calls ServiceBay.release().
	 * @param index the index of the ServiceBay to remove
	 * @return the Vehicle that was removed
	 * @throws IllegalArgumentException if the index is less than 0 or greater than the number of currently open bays
	 */
	public Vehicle release(int index) throws IllegalArgumentException {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("invalid bay index to release");
		}
		Vehicle result = null;
		if (bays[index].isOccupied()) {
			result = bays[index].release();
		}
		return result;
	}

	/**
	 * Private method to initialize bays.
	 * @param x the number of bays
	 */
	private void initBays(int x) {
		int hybrids = (int) Math.ceil(x / 3.0);
		int remaining = x;
		int hbayIndex = x - hybrids;
		int sbayIndex = hbayIndex - 1;
		HybridElectricBay hbay = null;
		ServiceBay sbay = null;

		while (hybrids > 0) {
			hbay = new HybridElectricBay();
			size++;
			bays[hbayIndex++] = hbay;
			remaining--;
			if (remaining-- > 0) {
				sbay = new ServiceBay();
				size++;
				bays[sbayIndex--] = sbay;
			}
			if (remaining-- > 0) {
				sbay = new ServiceBay();
				size++;
				bays[sbayIndex--] = sbay;
			}
			hybrids--;
		}
	}
}
