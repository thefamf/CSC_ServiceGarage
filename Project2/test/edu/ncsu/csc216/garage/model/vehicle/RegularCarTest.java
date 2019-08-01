/**
 * 
 */
package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for RegularCar.
 * @author mlee25 Michael Lee
 *
 */
public class RegularCarTest {

	/**
	 * Test method for constructor with 3 parameters
	 */
	@Test
	public void testVehicleStringStringInt() {
		// Includes Tests for getTier(), getLicense() and getName()
		Vehicle r1 = null;

		//Test Invalid Cases
		//null license
		try {
			r1 = new RegularCar(null, "name", 0);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
			assertTrue(r1 == null);
		}
		//blank license
		try {
			r1 = new RegularCar("", "name", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
			assertTrue(r1 == null);
		}
		//license over 8 characters
		try {
			r1 = new RegularCar("VA-780912345", "name", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be more than 8 characters.", e.getMessage());
			assertTrue(r1 == null);
		}
		//null name
		try {
			r1 = new RegularCar("NC-77011", null, 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
			assertTrue(r1 == null);
		}
		//blank name
		try {
			r1 = new RegularCar("NC-77011", "", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
			assertTrue(r1 == null);
		}
		//negative tier status
		try {
			r1 = new RegularCar("NC-77011", "name", -1);			
		} catch (BadVehicleInformationException e) {
			assertEquals("invalid tier", e.getMessage());
			assertTrue(r1 == null);
		}
		//tier status 4
		try {
			r1 = new RegularCar("NC-77011", "name", 4);
		} catch (BadVehicleInformationException e) {
			assertEquals("invalid tier", e.getMessage());
			assertTrue(r1 == null);
		}
		
		//Test Valid Cases
		//test license with leading whitespace
		try {
			r1 = new RegularCar("   NC-77011", "name", 0);
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test license with trailing whitespace
		try {
			r1 = new RegularCar("NC-77011   ", "name", 0);			
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test license with leading and trailing whitespace
		try {
			r1 = new RegularCar("   NC-77011   ", "name", 0);			
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test name with leading whitespace
		try {
			r1 = new RegularCar("NC-77011", "   Last, First", 0);			
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("Last, First", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test name with trailing whitespace
		try {
			r1 = new RegularCar("NC-77011", "Last, First   ", 0);			
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("Last, First", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test name with middle whitespace
		try {
			r1 = new RegularCar("NC-77011", "Last, First", 0);			
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("Last, First", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 0
		try {
			r1 = new RegularCar("NC-77011", "name", 0);
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			r1 = new RegularCar("NC-77011", "name", 1);
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(1, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 2
		try {
			r1 = new RegularCar("NC-77011", "name", 2);
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(2, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 3
		try {
			r1 = new RegularCar("NC-77011", "name", 3);
			assertEquals("NC-77011", r1.getLicense());
			assertEquals("name", r1.getName());
			assertEquals(3, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
	}

	/**
	 * Test method for constructor with 2 parameters
	 */
	@Test
	public void testVehicleStringInt() {
		Vehicle r1 = null;
		String info = "   NC-77011     Last, First M";
		//test tier status 0
		try {
			r1 = new RegularCar(info, 0);
			assertEquals("R None      NC-77011  Last, First M", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			r1 = new RegularCar(info, 1);
			assertEquals("R Silver    NC-77011  Last, First M", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 2
		try {
			r1 = new RegularCar(info, 2);
			assertEquals("R Gold      NC-77011  Last, First M", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 3
		try {
			r1 = new RegularCar(info, 3);
			assertEquals("R Platinum  NC-77011  Last, First M", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test 
		try {
			r1 = new RegularCar("  HI-01345     Rhyne, Lauren \n", 3);
			assertEquals("R Platinum  HI-01345  Rhyne, Lauren", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
	}

	/**
	 * Test method for meetsFilter
	 */
	@Test
	public void testMeetsFilter() {
		Vehicle v1 = null;
		try {
			v1 = new RegularCar("NC-12345", "Last, First", 0);
			assertEquals("R None      NC-12345  Last, First", v1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		assertEquals("Last, First", v1.getName());
		assertTrue(v1.meetsFilter(null));
		assertTrue(v1.meetsFilter(""));
		assertTrue(v1.meetsFilter("l"));
		assertTrue(v1.meetsFilter("L"));
		assertTrue(v1.meetsFilter("  L"));
		assertTrue(v1.meetsFilter("L  "));
		assertTrue(v1.meetsFilter("  L  "));
		assertTrue(v1.meetsFilter("  La  "));
		assertTrue(v1.meetsFilter("  Las  "));
		assertTrue(v1.meetsFilter("  Last  "));
		assertTrue(v1.meetsFilter("Last,"));
		assertTrue(v1.meetsFilter("Last, F"));
		assertFalse(v1.meetsFilter("a"));
		assertFalse(v1.meetsFilter("F"));

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.garage.model.vehicle.Vehicle#setTier(int)}.
	 */
	@Test
	public void testSetTier() {
		// constructor has bounds tests for tier
		Vehicle r1 = null;

		//set tier 2
		try {
			r1 = new RegularCar("NC-77011", "Last, First", 2);
			assertEquals("R Gold      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//change tier to 3
		try {
			r1.setTier(3);
			assertEquals("R Platinum  NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//try invalid tier
		try {
			r1.setTier(-1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("R Platinum  NC-77011  Last, First", r1.toString());
		}
		try {
			r1.setTier(4);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("R Platinum  NC-77011  Last, First", r1.toString());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.garage.model.vehicle.Vehicle#compareToTier(edu.ncsu.csc216.garage.model.vehicle.Tiered)}.
	 */
	@Test
	public void testCompareToTier() {
		Vehicle r1 = null;
		Vehicle r2 = null;
		
		//test tier status 0
		try {
			r1 = new RegularCar(" NC-77011  " + "   Last, First  a", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			r2 = new RegularCar(" NC-77011  " + "   Last, First     ", 1);
			assertEquals("R Silver    NC-77011  Last, First", r2.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}

		//Compare r1 and r2
		assertEquals(-1, r1.compareToTier(r2));
		assertEquals(1, r2.compareToTier(r1));
		
		//change r1 to tier 1
		try {
			r1.setTier(1);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		assertEquals(0, r1.compareToTier(r2));
		assertEquals(0, r2.compareToTier(r1));
	}

	/**
	 * Test method for toString
	 */
	@Test
	public void testToString() {
		//test platinum
		try {
			RegularCar r1 = new RegularCar("NC-77011", "Last, First", 3);
			assertEquals("R Platinum  NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test gold
		try {
			RegularCar r1 = new RegularCar("NC-77011", "Last, First", 2);
			assertEquals("R Gold      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test silver
		try {
			RegularCar r1 = new RegularCar("NC-77011", "Last, First", 1);
			assertEquals("R Silver    NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test no tier status
		try {
			RegularCar r1 = new RegularCar("NC-77011", "Last, First", 0);
			assertEquals("R None      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
	}

	/**
	 * Test method for pickServiceBay
	 */
	@Test
	public void testPickServiceBay() {
		//TODO
	}

}
