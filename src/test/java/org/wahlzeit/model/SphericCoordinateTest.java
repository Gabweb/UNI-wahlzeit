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
		co1 = SphericCoordinate.getCoordinate(0, 0);
		co2 = SphericCoordinate.getCoordinate(3, 4);
	}

	@Test
	public void testConstructor() {
		assertNotNull(co1);
		assertNotNull(co2);

		assertTrue(co1.equals(SphericCoordinate.getCoordinate(0, 0)));

		assertTrue(!co1.equals(co2));
	}

	@Test
	public void testIllegalArgument() {
		boolean fails;

		// Latitude
		fails = false;
		try {
			SphericCoordinate.getCoordinate(90.1, 0);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		fails = false;
		try {
			SphericCoordinate.getCoordinate(-90.1, 0);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		fails = false;
		try {
			SphericCoordinate.getCoordinate(Double.NaN, 0);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		// Longitude
		fails = false;
		try {
			SphericCoordinate.getCoordinate(0, 180.1);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		fails = false;
		try {
			SphericCoordinate.getCoordinate(0, 180.1);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		fails = false;
		try {
			SphericCoordinate.getCoordinate(0, Double.NaN);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		// Latitude
		fails = false;
		try {
			co1.getLatitudinalDistance(null);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		// Longitude
		fails = false;
		try {
			co1.getLongitudinalDistance(null);
		} catch (IllegalArgumentException e) {
			fails = true;
		}
		assertTrue(fails);

		// Distance
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
		Coordinate location = SphericCoordinate.getCoordinate(52.517, 13.40);
		Coordinate location2 = SphericCoordinate.getCoordinate(35.70, 139.767);
		Coordinate location3 = SphericCoordinate.getCoordinate(40.712778, -74.005833);
		assertEquals(8918, location.getDistance(location2), 1);
		assertEquals(8918, location2.getDistance(location), 1);
		assertEquals(6385, location.getDistance(location3), 1);
		assertEquals(6385, location3.getDistance(location), 1);
		assertEquals(10844, location2.getDistance(location3), 1);
		assertEquals(10844, location3.getDistance(location2), 1);
	}

}
