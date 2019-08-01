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
 * Test class for Garage.
 * @author mlee25 Michael Lee
 *
 */
public class GarageTest {

	/**
	 * Test method for Garage constructor.
	 * Includes tests for getBayAt() and getSize() and numberOfEmptyBays()
	 */
	@Test
	public void testGarage() {
		Garage garage = new Garage();
		assertEquals(8, garage.getSize());
		assertEquals("108", garage.getBayAt(0).getBayID());
		assertEquals("106", garage.getBayAt(1).getBayID());
		assertEquals("105", garage.getBayAt(2).getBayID());
		assertEquals("103", garage.getBayAt(3).getBayID());
		assertEquals("102", garage.getBayAt(4).getBayID());
		assertEquals("E01", garage.getBayAt(5).getBayID());
		assertEquals("E04", garage.getBayAt(6).getBayID());
		assertEquals("E07", garage.getBayAt(7).getBayID());
		
		//test number of empty bays
		assertEquals(8, garage.numberOfEmptyBays());
	}

	/**
	 * Test method for addRepairBay
	 * Includes tests for getBayAt() and getSize() and numberOfEmptyBays()
	 */
	@Test
	public void testAddRepairBay() {
		Garage garage = new Garage();
		//add 9th bay - regular
		garage.addRepairBay();
		assertEquals(9, garage.getSize());
		assertEquals("109", garage.getBayAt(0).getBayID());
		assertEquals("108", garage.getBayAt(1).getBayID());
		assertEquals("106", garage.getBayAt(2).getBayID());
		assertEquals("105", garage.getBayAt(3).getBayID());
		assertEquals("103", garage.getBayAt(4).getBayID());
		assertEquals("102", garage.getBayAt(5).getBayID());
		assertEquals("E01", garage.getBayAt(6).getBayID());
		assertEquals("E04", garage.getBayAt(7).getBayID());
		assertEquals("E07", garage.getBayAt(8).getBayID());
		assertEquals(9, garage.numberOfEmptyBays());

		//add 10th bay - hybrid
		garage.addRepairBay();
		assertEquals(10, garage.getSize());
		assertEquals("109", garage.getBayAt(0).getBayID());
		assertEquals("108", garage.getBayAt(1).getBayID());
		assertEquals("106", garage.getBayAt(2).getBayID());
		assertEquals("105", garage.getBayAt(3).getBayID());
		assertEquals("103", garage.getBayAt(4).getBayID());
		assertEquals("102", garage.getBayAt(5).getBayID());
		assertEquals("E01", garage.getBayAt(6).getBayID());
		assertEquals("E04", garage.getBayAt(7).getBayID());
		assertEquals("E07", garage.getBayAt(8).getBayID());
		assertEquals("E10", garage.getBayAt(9).getBayID());
		assertEquals(10, garage.numberOfEmptyBays());

		//add 11th, 12th and 13 bay - 2 regular and 1 hybrid
		garage.addRepairBay();
		garage.addRepairBay();
		garage.addRepairBay();
		assertEquals(13, garage.getSize());
		assertEquals("112", garage.getBayAt(0).getBayID());
		assertEquals("111", garage.getBayAt(1).getBayID());
		assertEquals("109", garage.getBayAt(2).getBayID());
		assertEquals("108", garage.getBayAt(3).getBayID());
		assertEquals("106", garage.getBayAt(4).getBayID());
		assertEquals("105", garage.getBayAt(5).getBayID());
		assertEquals("103", garage.getBayAt(6).getBayID());
		assertEquals("102", garage.getBayAt(7).getBayID());
		assertEquals("E01", garage.getBayAt(8).getBayID());
		assertEquals("E04", garage.getBayAt(9).getBayID());
		assertEquals("E07", garage.getBayAt(10).getBayID());
		assertEquals("E10", garage.getBayAt(11).getBayID());
		assertEquals("E13", garage.getBayAt(12).getBayID());
		assertEquals(13, garage.numberOfEmptyBays());
		
		//add 17 bays to reach 30
		for (int i = 1; i <= 17; i++) {
			garage.addRepairBay();			
		}
		assertEquals(30, garage.getSize());
		assertEquals(30, garage.numberOfEmptyBays());

		//try adding 31st bay
		garage.addRepairBay();			
		assertEquals(30, garage.getSize());
		assertEquals(30, garage.numberOfEmptyBays());
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.garage.model.service_garage.Garage#release(int)}.
	 */
	@Test
	public void testRelease() {
		Garage garage = new Garage();
		assertEquals(8, garage.getSize());
		Vehicle rv1 = null;
		Vehicle ev1 = null;
		Vehicle ev2 = null;
		Vehicle testvehicle = null;
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			ev1 = new HybridElectricCar("NC-77011", "Last, First", 0);
			ev2 = new HybridElectricCar("NC-77011", "Last, First", 1);
		} catch (BadVehicleInformationException e) {
			fail();
		}

		//test regular car in regular bay
		try {
			garage.getBayAt(0).occupy(rv1);
			assertTrue(garage.getBayAt(0).isOccupied());
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		//test electric car in occupied regular bay
		try {
			garage.getBayAt(0).occupy(ev1);
			fail();
		} catch (BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay occupied", e.getMessage());
		}
		//test release and put electric car in regular bay
		try {
			assertTrue(garage.getBayAt(0).isOccupied());
			testvehicle = garage.release(0);
			assertEquals("R Platinum  NC-77011  Last, First", testvehicle.toString());
			garage.getBayAt(0).occupy(ev1);
			testvehicle = garage.release(0);
			assertEquals("E None      NC-77011  Last, First", testvehicle.toString());
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}

		//test regular car in hybrid electric bay
		try {
			garage.getBayAt(7).occupy(rv1);
			fail();
		} catch (BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay mismatch", e.getMessage());
			assertFalse(garage.getBayAt(7).isOccupied());
		}
		//test electric car in hybrid bay
		try {
			garage.getBayAt(7).occupy(ev1);
			assertTrue(garage.getBayAt(7).isOccupied());
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		//test electric car in occupied hybrid bay
		try {
			garage.getBayAt(7).occupy(ev2);
			fail();
		} catch (BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay occupied", e.getMessage());
			assertTrue(garage.getBayAt(7).isOccupied());
		}
		//test release and put electric car in electric bay
		try {
			assertTrue(garage.getBayAt(7).isOccupied());
			testvehicle = garage.release(7);
			assertEquals("E None      NC-77011  Last, First", testvehicle.toString());
			garage.getBayAt(7).occupy(ev2);
			testvehicle = garage.release(7);
			assertEquals("E Silver    NC-77011  Last, First", testvehicle.toString());
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		

	}

}
