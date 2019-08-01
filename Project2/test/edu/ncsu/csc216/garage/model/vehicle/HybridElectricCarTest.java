/**
 * 
 */
package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for HybridElectricCar.
 * @author mlee25 Michael Lee
 *
 */
public class HybridElectricCarTest {

	/**
	 * Test method for constructor with 3 parameters
	 */
	@Test
	public void testVehicleStringStringInt() {
		//Tests getTier(), getLicense() and getName()
		
		//test null license
		try {
			HybridElectricCar r1 = new HybridElectricCar(null, "name", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
		}
		//test blank license
		try {
			HybridElectricCar r1 = new HybridElectricCar("", "name", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
		}
		//test license over 8 characters
		try {
			HybridElectricCar r1 = new HybridElectricCar("VA-780912345", "name", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be more than 8 characters.", e.getMessage());
		}
		//test license with leading whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("   NC-77011", "name", 0);
			assertEquals("NC-77011", r1.getLicense());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test license with trailing whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011   ", "name", 0);			
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test license with leading and trailing whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("   NC-77011   ", "name", 0);			
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test null name
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", null, 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
		}
		//test blank name
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "", 0);			
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
		}
		//test name with leading whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "   Last, First", 0);			
			assertEquals("Last, First", r1.getName());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test name with trailing whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First   ", 0);			
			assertEquals("Last, First", r1.getName());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test name with middle whitespace
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First", 0);			
			assertEquals("Last, First", r1.getName());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test negative tier status
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", -1);			
		} catch (BadVehicleInformationException e) {
			assertEquals("invalid tier", e.getMessage());
		}
		//test tier status 4
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", 4);
		} catch (BadVehicleInformationException e) {
			assertEquals("invalid tier", e.getMessage());
		}
		//test tier status 0
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", 0);
			assertEquals(0, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", 1);
			assertEquals(1, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 2
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", 2);
			assertEquals(2, r1.getTier());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 3
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "name", 3);
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
		//test tier status 0
		try {
			HybridElectricCar r1 = new HybridElectricCar(" NC-77011  " + "   Last, First  a", 0);
			assertEquals("E None      NC-77011  Last, First  a", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			HybridElectricCar r1 = new HybridElectricCar(" NC-77011  " + "   Last, First     ", 1);
			assertEquals("E Silver    NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 2
		try {
			HybridElectricCar r1 = new HybridElectricCar(" NC-77011  " + "   Last, First     ", 2);
			assertEquals("E Gold      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 3
		try {
			HybridElectricCar r1 = new HybridElectricCar(" NC-77011  " + "   Last, First     ", 3);
			assertEquals("E Platinum  NC-77011  Last, First", r1.toString());
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
			v1 = new HybridElectricCar("NC-12345", "Last, First", 0);
			assertEquals("E None      NC-12345  Last, First", v1.toString());
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
	 * Test method for setTier()
	 */
	@Test
	public void testSetTier() {
		// constructor has bounds tests for tier
		HybridElectricCar r1 = null;

		//test gold status reset to platinum status
		try {
			r1 = new HybridElectricCar("NC-77011", "Last, First", 2);
			assertEquals("E Gold      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		try {
			r1.setTier(3);
			assertEquals("E Platinum  NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
	}

	/**
	 * Test method for compareToTier()
	 */
	@Test
	public void testCompareToTier() {
		HybridElectricCar r1 = null;
		HybridElectricCar r2 = null;
		
		//test tier status 0
		try {
			r1 = new HybridElectricCar(" NC-77011  " + "   Last, First  a", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		//test tier status 1
		try {
			r2 = new HybridElectricCar(" NC-77011  " + "   Last, First     ", 1);
			assertEquals("E Silver    NC-77011  Last, First", r2.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}

		//Compare r1 and r2
		assertEquals(-1, r1.compareToTier(r2));
		assertEquals(1, r2.compareToTier(r1));
	}

	/**
	 * Test method for toString
	 */
	@Test
	public void testToString() {
		//test platinum
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First", 3);
			assertEquals("E Platinum  NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test gold
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First", 2);
			assertEquals("E Gold      NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test silver
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First", 1);
			assertEquals("E Silver    NC-77011  Last, First", r1.toString());
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//test no tier status
		try {
			HybridElectricCar r1 = new HybridElectricCar("NC-77011", "Last, First", 0);
			assertEquals("E None      NC-77011  Last, First", r1.toString());
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
