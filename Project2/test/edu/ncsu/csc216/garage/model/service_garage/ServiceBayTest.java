/**
 * 
 */
package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Test class for ServiceBay.
 * @author mlee25 Michael Lee
 *
 */
public class ServiceBayTest {

	/**
	 * Test method for startBayNumberingAt101().
	 */
	@Test
	public void testStartBayNumberingAt101() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		ServiceBay sb2 = new ServiceBay();
		ServiceBay sb3 = new ServiceBay();
		ServiceBay sb4 = new ServiceBay();
		ServiceBay sb5 = new ServiceBay();
		
		assertEquals("101", sb1.getBayID());
		assertEquals("102", sb2.getBayID());
		assertEquals("103", sb3.getBayID());
		assertEquals("104", sb4.getBayID());
		assertEquals("105", sb5.getBayID());
	}

	/**
	 * Test method for null constructor.
	 */
	@Test
	public void testServiceBay() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		ServiceBay sb2 = new ServiceBay();
		ServiceBay sb3 = new ServiceBay();
		
		assertEquals("101", sb1.getBayID());
		assertEquals("102", sb2.getBayID());
		assertEquals("103", sb3.getBayID());
	}

	/**
	 * Test method for constructor with String parameter
	 */
	@Test
	public void testServiceBayString() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay("E");
		ServiceBay sb2 = new ServiceBay("   E");
		ServiceBay sb3 = new ServiceBay("E   ");
		ServiceBay sb4 = new ServiceBay("   E   ");
		
		assertEquals("E01", sb1.getBayID());
		assertEquals("E02", sb2.getBayID());
		assertEquals("E03", sb3.getBayID());
		assertEquals("E04", sb4.getBayID());
	}

	/**
	 * Test method for getBayID
	 */
	@Test
	public void testGetBayID() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		ServiceBay sb2 = new ServiceBay();
		ServiceBay sb3 = new ServiceBay();
		
		assertEquals("101", sb1.getBayID());
		assertEquals("102", sb2.getBayID());
		assertEquals("103", sb3.getBayID());
	}

	/**
	 * Test method for isOccupied
	 */
	@Test
	public void testIsOccupied() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		Vehicle rv1 = null;
		Vehicle ev1 = null;
		Vehicle testvehicle = null;
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			sb1.occupy(rv1);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		assertTrue(sb1.isOccupied());
		sb1.release();
		assertFalse(sb1.isOccupied());
	}

	/**
	 * Test method for occupy()
	 */
	@Test
	public void testOccupy() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		ServiceBay sb2 = new ServiceBay();
		ServiceBay sb3 = new ServiceBay();
		Vehicle rv1 = null;
		Vehicle ev1 = null;
		Vehicle ev2 = null;
		Vehicle testvehicle = null;
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			sb1.occupy(rv1);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}

		//test occupied bay
		try {
			sb1.occupy(rv1);
		} catch (BayOccupiedException | BayCarMismatchException e) {
			assertEquals("bay occupied", e.getMessage());
		}

	}

	/**
	 * Test method for release()
	 */
	@Test
	public void testRelease() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		Vehicle rv1 = null;
		Vehicle ev1 = null;
		Vehicle testvehicle = null;
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			sb1.occupy(rv1);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		assertTrue(sb1.isOccupied());
		sb1.release();
		assertFalse(sb1.isOccupied());
	}

	/**
	 * Test method for toString.
	 */
	@Test
	public void testToString() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sb1 = new ServiceBay();
		ServiceBay sb2 = new ServiceBay();
		ServiceBay sb3 = new ServiceBay();
		Vehicle rv1 = null;
		Vehicle rv2 = null;
		Vehicle testvehicle = null;
		
		assertEquals("101: EMPTY", sb1.toString());
		try {
			rv1 = new RegularCar("NC-77011", "Last, First", 3);
			sb1.occupy(rv1);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		assertEquals("101: NC-77011 Last, First", sb1.toString());
		try {
			rv2 = new RegularCar("NC-123", "Doe, John", 0);
			sb2.occupy(rv2);
		} catch (BadVehicleInformationException | BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		assertEquals("102: NC-123   Doe, John", sb2.toString());
	}

}
