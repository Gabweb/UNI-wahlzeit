package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate c0;
	private Coordinate c1;
	private Coordinate c2;
	
	@Before
	public void initCoordinate() {
		c0 = new SphericCoordinate(88, 170);
		c1 = c0.asCartesianCoordinate();
		c2 = c1.asSphericCoordinate();
	}
	
	@Test
	public void testConversionAndIsEquals() {		
		assertTrue(c0.isEqual(c2));
		assertTrue(c2.isEqual(c1));
		assertTrue(c1.isEqual(c2));
	}
	
	/*
	 * Test if getDistance works with 2 different Coordinate types.
	 */
	public void testGetDistance() {
		assertEquals( 0, c0.getDistance(c0), 0.0000000001 );
		assertEquals( 0, c0.getDistance(c1), 0.0000000001 );
		assertEquals( 0, c1.getDistance(c0), 0.0000000001 );
		
		Coordinate location = new SphericCoordinate(52.517, 13.40);
		Coordinate location2 = new SphericCoordinate(35.70, 139.767);
		Coordinate location3 = new SphericCoordinate(40.712778, -74.005833);
		assertEquals(8918, location.getDistance(location2), 1);
		assertEquals(8918, location2.getDistance(location), 1);
		
		Coordinate lo = location.asCartesianCoordinate();
		Coordinate lo2 = location2.asCartesianCoordinate();
		Coordinate lo3 = location3.asCartesianCoordinate();
		assertEquals(8918, lo.getDistance(lo2), 1);
		assertEquals(8918, lo2.getDistance(lo), 1);
		
		assertEquals(8918, location.getDistance(lo2), 1);
		assertEquals(8918, lo2.getDistance(location), 1);
	}

}
