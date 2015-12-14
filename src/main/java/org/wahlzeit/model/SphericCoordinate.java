/*
 * Spheric cordinate class that works with lat and long
 */
package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

import com.googlecode.objectify.annotation.Subclass;


@Pattern(name = "Template", participants = {"AbstactCoordinate", "CartesianCoordinate", "SphericCoordinate"})
@Subclass
public class SphericCoordinate extends AbstractCoordinate {

	private final double latitude;
	private final double longitude;
	private final double radius;

	/*
	 * @methodtype constructor
	 */
	private SphericCoordinate(double lat, double lon) {
		this(lat, lon, EARTHRADIUS);
	}

	/*
	 * @methodtype constructor
	 */
	private SphericCoordinate(double lat, double lon, double radius) {
		assertValidLatitude(lat);
		latitude = lat;
		
		assertValidLongitude(lon);
		longitude = lon;
		
		assertValidRadius(radius);
		this.radius = radius;
	}	
	
	/*
	 * @methodtype factory method
	 */
	public static SphericCoordinate getCoordinate(double lat, double lon) {
		SphericCoordinate tmp = new SphericCoordinate(lat, lon);
		int hashCode = tmp.hashCode();
		Coordinate result = allCoordinates.get(hashCode);
		if (result == null) {
			synchronized (allCoordinates) {
				result = allCoordinates.get(hashCode);
				if (result == null) {
					result = tmp;
					allCoordinates.put(hashCode, result);
				}
			}
		}
		return (SphericCoordinate)result;
	}

	/*
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/*
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/*
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}
	
	/*
	 * @methodtype get
	 */
	public double getX() {
		return EARTHRADIUS * Math.cos(Math.toRadians( getLatitude() ))*Math.cos(Math.toRadians( getLongitude() ));
	}

	/*
	 * @methodtype get
	 */
	public double getY() {
		return EARTHRADIUS * Math.cos(Math.toRadians( getLatitude() ))*Math.sin(Math.toRadians( getLongitude() ));
	}

	/*
	 * @methodtype get
	 */
	public double getZ() {
		return EARTHRADIUS * Math.sin(Math.toRadians( getLatitude() ));
	}

	/*
	 * @methodtype query
	 */
	public double getLatitudinalDistance(SphericCoordinate in) {
		assertValidCoordinate(in);

		double ret = Math.abs(latitude - in.getLatitude());
		
		assert(ret >= 0 && ret <= 180);

		return ret;
	}

	/*
	 * @methodtype query
	 */
	public double getLongitudinalDistance(SphericCoordinate in) {
		assertValidCoordinate(in);

		double ret = Math.abs(longitude - in.getLongitude());
		
		assert(ret >= 0 && ret <= 360);

		return ret;
	}

	
	/*
	 * @methodtype assertion
	 */
	public void assertClassInvariants() throws IllegalArgumentException {
		try {
			assertValidLatitude(this.latitude);
			assertValidLongitude(this.longitude);
			assertValidRadius(this.radius);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
	}
	
	/*
	 * @methodtype assertion
	 */
	private void assertValidLatitude(double lat) throws IllegalArgumentException {
		assertNotNaN(lat);
		if (lat > 90 || lat < -90)
			throw new IllegalArgumentException("bad latitude");		
	}
	
	/*
	 * @methodtype assertion
	 */
	private void assertValidLongitude(double lon) throws IllegalArgumentException {
		assertNotNaN(lon);
		if (lon > 180 || lon < -180)
			throw new IllegalArgumentException("bad longitude");
	}
	
	/*
	 * @methodtype assertion
	 */
	private void assertValidRadius(double radius) throws IllegalArgumentException {
		assertNotNaN(radius);
		if (radius <= 0)
			throw new IllegalArgumentException("bad radius");
	}
}
