/**
 * 
 */
package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Interface to describe behaviors of objects that can be ordered by tier status.
 * The program is designed with three tier status levels, Platinum (tier status 3), Gold (tier status 2) and Silver (tier status 1).
 * Vehicles with no tier status will have a tier status of 0.
 * The interface has methods to get the tier status and to compare the tier status of two objects.
 * @author mlee25 Michael Lee
 *
 */
public interface Tiered {

	/**
	 * Getter for this object's tier status.
	 * @return the tier status as an int
	 */
	public int getTier();

	/**
	 * Method to compare the tier status of this object with another tiered object.
	 * Returns 0 if the tier statuses match, a negative number if the tier status of this object is less than the other, and a positive number if the tier status of this object is greater.
	 * @param t the other Tiered object to compare
	 * @return an integer value for the comparison, 0 if equal, a negative value if this is less than the other, and a positive value if this is greater than the other
	 */
	public int compareToTier(Tiered t);
}
