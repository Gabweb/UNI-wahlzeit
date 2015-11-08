package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {

	private SphericCoordinate co1;
	private SphericCoordinate co2;


	@Before
	public void initCoordinate() {
		co1 = new SphericCoordinate(0,0);
		co2 = new SphericCoordinate(3,4);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(co1);
		assertNotNull(co2);

		assertTrue(co1.equals(new SphericCoordinate(0,0)));
		
		co1.setLatitude(3);
		co1.setLongitude(4);
		
		assertEquals(3, co1.getLatitude(), 0.001);
		assertEquals(4, co1.getLongitude(), 0.001);
		
		assertTrue(co1.equals(co2));
	}
	
	@Test
	public void testIllegalArgument() {
		boolean fails;
		
		//Latitude
		fails = false;
		try {
			new SphericCoordinate(90.1,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);

		fails = false;
		try {
			new SphericCoordinate(-90.1,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		fails = false;
		try {
			new SphericCoordinate(Double.NaN,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		//Longitude
		fails = false;
		try {
			new SphericCoordinate(0,180.1);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);

		fails = false;
		try {
			new SphericCoordinate(0, 180.1);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		fails = false;
		try {
			new SphericCoordinate(0, Double.NaN);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		//Latitude
		fails = false;
		try {
			co1.getLatitudinalDistance(null);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		//Longitude
		fails = false;
		try {
			co1.getLongitudinalDistance(null);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		//Distance
		fails = false;
		try {
			co1.getDistance(null);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
	}
	
	@Test
	public void testDistance() {
		
		assertEquals(0, co1.getLatitudinalDistance(co1), 0.001);
		assertEquals(0, co1.getLongitudinalDistance(co1), 0.001);
		
		Coordinate co3 = new SphericCoordinate(20,20); 
		
		assertEquals(20, co1.getLatitudinalDistance(co3), 0.001);
		assertEquals(20, co1.getLongitudinalDistance(co3), 0.001);
		//assertEquals(20, co3.getLatitudinalDistance(co1), 0.001);
		//assertEquals(20, co3.getLongitudinalDistance(co1), 0.001);
		
		Coordinate co4 = new SphericCoordinate(-20,-20);
		
		assertEquals(20, co1.getLatitudinalDistance(co3), 0.001);
		assertEquals(20, co1.getLongitudinalDistance(co3), 0.001);
		
		assertEquals(Math.sqrt(800), co1.getDistance(co3), 0.001);
		assertEquals(Math.sqrt(3200), co4.getDistance(co3), 0.001);
		assertEquals(Math.sqrt(3200), co3.getDistance(co4), 0.001);

	}

}
