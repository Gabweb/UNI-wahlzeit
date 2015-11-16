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
		if (lat > 90 || lat < -90 || Double.isNaN(lat))
			throw new IllegalArgumentException("bad latitude");
		latitude = lat;
	}

	/*
	 * @methodtype set
	 */
	public void setLongitude(double lon) throws IllegalArgumentException {
		if (lon > 180 || lon < -180 || Double.isNaN(lon))
			throw new IllegalArgumentException("bad longitude");
		longitude = lon;
	}

	/*
	 * @methodtype query
	 */
	@Override
	public double getDistance(Coordinate in) {
		assertValidCoordinate(in);

		SphericCoordinate tmp = asSphericCoordinate(in);
		
		double phi1 = Math.toRadians(latitude);
		double phi2 = Math.toRadians(((SphericCoordinate) tmp).getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(tmp));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(tmp));
		double ret = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
		double angle = 2 * Math.asin(Math.sqrt(ret));
		return Math.abs(EARTHRADIUS * angle);
	}

	/*
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate in) {
		assertValidCoordinate(in);

		SphericCoordinate tmp = asSphericCoordinate(in);

		return Math.abs(latitude - tmp.getLatitude());
	}

	/*
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate in) {
		assertValidCoordinate(in);

		SphericCoordinate tmp = asSphericCoordinate(in);

		return Math.abs(longitude - tmp.getLongitude());
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

}
