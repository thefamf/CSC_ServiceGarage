/**
 * 
 */
package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

//import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * Test class for VehicleList.
 * @author mlee25 Michael Lee
 *
 */
public class VehicleListTest {

	/**
	 * Test method for vehicleListScanner()
	 */
	@Test
	public void testVehicleListScanner() {
		String string = "R 3  HI-01345     Rhyne, Lauren \n" + 
				"R 0 NC-122 Doe, John \n" + 
				"e 2 NC-5678 Emerson, Jane \n" + 
				"R 1 VA-121A Henderson, William \n" +
				"e 3 DC-0090 Harrison, Emily \n";
		Scanner scanner = new Scanner(string);
		VehicleList list = new VehicleList(scanner);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get(null, 0).toString());
		assertEquals("E Platinum  DC-0090   Harrison, Emily", list.get(null, 1).toString());
		assertEquals("E Gold      NC-5678   Emerson, Jane", list.get(null, 2).toString());
		assertEquals("R Silver    VA-121A   Henderson, William", list.get(null, 3).toString());
		assertEquals("R None      NC-122    Doe, John", list.get(null, 4).toString());

		try {
			scanner = new Scanner(new File("test-files/cars2.txt"));			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		list = new VehicleList(scanner);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("", 0).toString());
		assertEquals("E Gold      NC-5678   Emerson, Jane", list.get("", 1).toString());
		
		assertEquals("" + 
				"R Platinum  HI-01345  Rhyne, Lauren\n" + 
				"E Gold      NC-5678   Emerson, Jane\n" + 
				"R Gold      VIRG0122  Jones, Francis\n" +
				"R Silver    VA-121A   Henderson, William\n" + 
				"R Silver    MN-20134  McKeel, Robyn\n" + 
				"R Silver    SC-0I033  Smith, Amos\n" + 
				"R None      NC-122    Doe, John\n" + 
				"E None      FL-09876  Peterson, Keith\n" + 
				"", list.filteredList(""));
	}

//	/**
//	 * Test method for constructor.
//	 */
//	@Test
//	public void testVehicleList() {
//		VehicleList list = new VehicleList();
//		assertEquals(null, list.head);
//	}

//	/**
//	 * Test method for iterator()
//	 */
//	@Test
//	public void testIterator() {
//		VehicleList list = new VehicleList();
//		SimpleIterator<Vehicle> cursor = new Cursor();
//		cursor = iterator();
//	}

	/**
	 * Test method for add()
	 */
	@Test
	public void testAdd() {
		VehicleList list = new VehicleList();
		Vehicle v1 = null;
		Vehicle v2 = null;
		try {
			v1 = new RegularCar("HI-01345 Rhyne, Lauren", 3);
			v2 = new RegularCar("NC-122 Doe, John", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		list.add(v1);
		list.add(v2);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("", 0).toString());
		assertEquals("R None      NC-122    Doe, John", list.get("", 1).toString());
	}

	/**
	 * Test method for get()
	 */
	@Test
	public void testGet() {
		VehicleList list = new VehicleList();
		Vehicle v1 = null;
		Vehicle v2 = null;
		Vehicle v3 = null;
		try {
			v1 = new RegularCar("HI-01345 Rhyne, Lauren", 3);
			v2 = new RegularCar("NC-122 Doe, John", 0);
			v3 = new HybridElectricCar("NC-12345 Davis, Jane", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		list.add(v1);
		list.add(v2);
		list.add(v3);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("", 0).toString());
		assertEquals("R None      NC-122    Doe, John", list.get("", 1).toString());
		assertEquals("E None      NC-12345  Davis, Jane", list.get("", 2).toString());
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("r", 0).toString());
		assertEquals("R None      NC-122    Doe, John", list.get("d", 0).toString());
		assertEquals("E None      NC-12345  Davis, Jane", list.get("d", 1).toString());
	}

	/**
	 * Test method for remove()
	 */
	@Test
	public void testRemove() {
		VehicleList list = new VehicleList();
		Vehicle v1 = null;
		Vehicle v2 = null;
		Vehicle v3 = null;
		try {
			v1 = new RegularCar("HI-01345 Rhyne, Lauren", 3);
			v2 = new RegularCar("NC-122 Doe, John", 0);
			v3 = new HybridElectricCar("NC-12345 Davis, Jane", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		list.add(v1);
		list.add(v2);
		list.add(v3);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("", 0).toString());
		assertEquals("R None      NC-122    Doe, John", list.get("", 1).toString());
		assertEquals("E None      NC-12345  Davis, Jane", list.get("", 2).toString());
		
		assertEquals(v1, list.remove("", 0));
		assertEquals("R None      NC-122    Doe, John", list.get("", 0).toString());
		assertEquals("E None      NC-12345  Davis, Jane", list.get("", 1).toString());
		
		list.add(v1);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren", list.get("", 0).toString());
		assertEquals("R None      NC-122    Doe, John", list.get("", 1).toString());
		assertEquals("E None      NC-12345  Davis, Jane", list.get("", 2).toString());

		assertEquals(v2, list.remove("d", 0));
		list.add(v2);
	
		assertEquals(v3, list.remove("d", 1));
		
	}

	/**
	 * Test method for filteredList()
	 */
	@Test
	public void testFilteredList() {
		VehicleList list = new VehicleList();
		Vehicle v1 = null;
		Vehicle v2 = null;
		try {
			v1 = new RegularCar("HI-01345 Rhyne, Lauren", 3);
			v2 = new RegularCar("NC-122 Doe, John", 0);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		list.add(v1);
		list.add(v2);
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren\n" +
				"R None      NC-122    Doe, John\n", list.filteredList(""));
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren\n" +
				"R None      NC-122    Doe, John\n", list.filteredList("   "));
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren\n", list.filteredList("r"));
		assertEquals("R None      NC-122    Doe, John\n", list.filteredList("d"));
	}

}
