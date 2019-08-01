/**
 * 
 */
package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Test class for HybridElectricBay.
 * @author mlee25 Michael Lee
 *
 */
public class HybridElectricBayTest {

	/**
//	 * Test method for constructor.
	 */
	@Test
	public void testHybridElectricBay() {
		ServiceBay.startBayNumberingAt101();
		HybridElectricBay hb1 = new HybridElectricBay();
		HybridElectricBay hb2 = new HybridElectricBay();
		HybridElectricBay hb3 = new HybridElectricBay();
		
		assertEquals("E01", hb1.getBayID());
		assertEquals("E02", hb2.getBayID());
		assertEquals("E03", hb3.getBayID());
	}

	/**
	 * Test method for occupy().
	 */
	@Test
	public void testOccupy() {
		ServiceBay.startBayNumberingAt101();
		HybridElectricBay hb1 = new HybridElectricBay();
		Vehicle rv1 = null;
		Vehicle ev1 = null;
		Vehicle ev2 = null;

		//test regular car in hybrid bay
		assertEquals("E01: EMPTY", hb1.toString());
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			hb1.occupy(rv1);
			fail();
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay mismatch", e.getMessage());
		}
		assertFalse(hb1.isOccupied());
		//test hybrid car 
		try {
			ev1 = new HybridElectricCar("NC-77011", "Last, First", 3);
			hb1.occupy(ev1);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		assertTrue(hb1.isOccupied());
		//test occupied bay
		assertEquals("E01: NC-77011 Last, First", hb1.toString());
		try {
			ev2 = new RegularCar("NC-12345", "Doe, John", 3);
			hb1.occupy(ev2);
			fail();
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay occupied", e.getMessage());
		}
		//test release
		assertTrue(hb1.isOccupied());
		hb1.release();
		assertEquals("E01: EMPTY", hb1.toString());

	}


}
