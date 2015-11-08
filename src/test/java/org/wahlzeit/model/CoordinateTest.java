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
		c1 = AbstractCoordinate.asCartesianCoordinate(c0);
		c2 = AbstractCoordinate.asSphericCoordinate(c1);
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
	}

}
