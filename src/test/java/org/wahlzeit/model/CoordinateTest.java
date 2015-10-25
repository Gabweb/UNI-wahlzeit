package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate co1;
	private Coordinate co2;


	@Before
	public void initCoordinate() {
		co1 = new Coordinate();
		co2 = new Coordinate();
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(co1);
		assertNotNull(co2);

		coordinatesEquals(co1, co2);
		coordinatesEquals(co1, new Coordinate(0,0));
		
		Coordinate co3 = new Coordinate(1,2);
		
		coordinatesEquals(co3, new Coordinate(1,2));
		
		co2.setLatitude(1);
		co2.setLongitude(2);
		
		assertEquals(co2.getLatitude(), 1, 0.001);
		assertEquals(co2.getLongitude(), 2, 0.001);
		
		coordinatesEquals(co3, co2);
	}
	
	private void coordinatesEquals(Coordinate one, Coordinate two) {
		assertEquals(one.getLatitude(), two.getLatitude(), 0.001);
		assertEquals(one.getLongitude(), two.getLongitude(), 0.001);
	}
	
	@Test
	public void testIllegalArgument() {
		boolean fails;
		
		//Latitude
		fails = false;
		try {
			new Coordinate(90.1,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);

		fails = false;
		try {
			new Coordinate(-90.1,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		fails = false;
		try {
			new Coordinate(Double.NaN,0);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		//Longitude
		fails = false;
		try {
			new Coordinate(0,180.1);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);

		fails = false;
		try {
			new Coordinate(0, 180.1);
		} catch (IllegalArgumentException e) {
		   fails = true;
		} 
		assertTrue(fails);
		
		fails = false;
		try {
			new Coordinate(0, Double.NaN);
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
		
		assertEquals(co1.getLatitudinalDistance(co2), 0, 0.001);
		assertEquals(co1.getLongitudinalDistance(co2), 0, 0.001);
		
		Coordinate co3 = new Coordinate(20,20);
		
		assertEquals(co1.getLatitudinalDistance(co3), 20, 0.001);
		assertEquals(co1.getLongitudinalDistance(co3), 20, 0.001);
		assertEquals(co3.getLatitudinalDistance(co1), 20, 0.001);
		assertEquals(co3.getLongitudinalDistance(co1), 20, 0.001);
		
		Coordinate co4 = new Coordinate(-20,-20);
		
		assertEquals(co1.getLatitudinalDistance(co3), 20, 0.001);
		assertEquals(co1.getLongitudinalDistance(co3), 20, 0.001);
		
		coordinatesEquals(co1.getDistance(co3), new Coordinate(20,20));
		coordinatesEquals(co3.getDistance(co4), new Coordinate(40,40));
		coordinatesEquals(co4.getDistance(co3), new Coordinate(40,40));
	}

}
