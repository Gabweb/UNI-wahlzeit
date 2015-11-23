/*
 * Spheric cordinate class that works with lat and long
 */
package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SphericCoordinate extends AbstractCoordinate {

	private double latitude;
	private double longitude;
	private double radius;

	/*
	 * @methodtype constructor
	 */
	public SphericCoordinate(double lat, double lon) {
		this(lat, lon, EARTHRADIUS);
	}

	/*
	 * @methodtype constructor
	 */
	public SphericCoordinate(double lat, double lon, double radius) {
		setLatitude(lat);
		setLongitude(lon);
		
		assertValidRadius(radius);
		this.radius = radius;
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
	 * @methodtype set
	 */
	public void setLatitude(double lat) throws IllegalArgumentException {
		assertValidLatitude(lat);
		latitude = lat;
	}

	/*
	 * @methodtype set
	 */
	public void setLongitude(double lon) throws IllegalArgumentException {
		assertValidLongitude(lon);
		longitude = lon;
	}

	/*
	 * @methodtype helper
	 */
	protected double doGetDistance(Coordinate in) {
		SphericCoordinate tmp = in.asSphericCoordinate();
		
		double phi1 = Math.toRadians(latitude);
		double phi2 = Math.toRadians(((SphericCoordinate) tmp).getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(tmp));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(tmp));
		double tmp2 = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
		double angle = 2 * Math.asin(Math.sqrt(tmp2));
		double ret = Math.abs(EARTHRADIUS * angle);
		
		assert(ret >= 0);	
		
		return ret;
	}

	/*
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate in) {
		assertValidCoordinate(in);

		SphericCoordinate tmp = in.asSphericCoordinate();
		double ret = Math.abs(latitude - tmp.getLatitude());
		
		assert(ret >= 0 && ret <= 180);

		return ret;
	}

	/*
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate in) {
		assertValidCoordinate(in);

		SphericCoordinate tmp = in.asSphericCoordinate();
		double ret = Math.abs(longitude - tmp.getLongitude());
		
		assert(ret >= 0 && ret <= 360);

		return ret;
	}
	
	/*
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException {
		SphericCoordinate ret = (SphericCoordinate)this;

		ret.assertClassInvariants();

		return ret;
	}
	
	/*
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() throws IllegalArgumentException {
		SphericCoordinate tmp = (SphericCoordinate)this;
	    double lat = Math.toRadians( tmp.getLatitude() );
	    double lon = Math.toRadians( tmp.getLongitude() );
	    double x = EARTHRADIUS * Math.cos(lat)*Math.cos(lon);
	    double y = EARTHRADIUS * Math.cos(lat)*Math.sin(lon);
	    double z = EARTHRADIUS * Math.sin(lat);
	    CartesianCoordinate ret = new CartesianCoordinate(x, y, z);

		ret.assertClassInvariants();
		
		return ret;
	}

	/*
	 * @methodtype comparison
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * @methodtype boolean query
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SphericCoordinate))
			return false;
		SphericCoordinate other = (SphericCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
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
