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
		c0 = SphericCoordinate.getCoordinate(88, 170);
		c1 = CartesianCoordinate.getCoordinate(c0.getX(), c0.getY(), c0.getZ());
	}
	
	@Test
	public void testConversionAndIsEquals() {		
		assertTrue(c0.isEqual(c1));
		assertTrue(c1.isEqual(c0));
	}
	
	/*
	 * Test if getDistance works with 2 different Coordinate types.
	 */
	public void testGetDistance() {
		assertEquals( 0, c0.getDistance(c0), 0.0000000001 );
		assertEquals( 0, c0.getDistance(c1), 0.0000000001 );
		assertEquals( 0, c1.getDistance(c0), 0.0000000001 );
		
		Coordinate location = SphericCoordinate.getCoordinate(52.517, 13.40);
		Coordinate location2 = SphericCoordinate.getCoordinate(35.70, 139.767);
		Coordinate location3 = SphericCoordinate.getCoordinate(40.712778, -74.005833);
		assertEquals(8918, location.getDistance(location2), 1);
		assertEquals(8918, location2.getDistance(location), 1);
	}

}
