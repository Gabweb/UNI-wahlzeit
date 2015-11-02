/**
 * 
 */
package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * @author gabweb
 *
 */
public class Coordinate {

	private double latitude;
	private double longitude;

	/*
	 * @methodtype constructor
	 */
	public Coordinate() {
		latitude = 0;
		longitude = 0;
	}

	/*
	 * @methodtype constructor
	 */
	public Coordinate(double lat, double lon) {
		setLatitude(lat);
		setLongitude(lon);
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
	 * @methodtype set
	 */
	public void setLatitude(double lat) {
		if (lat > 90 || lat < -90 || Double.isNaN(lat))
			throw new IllegalArgumentException("bad latitude");
		latitude = lat;
	}

	/*
	 * @methodtype set
	 */
	public void setLongitude(double lon) {
		if (lon > 180 || lon < -180 || Double.isNaN(lon))
			throw new IllegalArgumentException("bad longitude");
		longitude = lon;
	}

	/*
	 * @methodtype query
	 */
	public double getDistance(Coordinate in) {
		return Math.sqrt(Math.pow(getLatitudinalDistance(in), 2) + Math.pow(getLongitudinalDistance(in), 2));
	}
	
	/*
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate in) {
		if (in == null)
			throw new IllegalArgumentException("bad coordinate");
		return Math.abs(latitude - in.getLatitude());
	}

	/*
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate in) {
		if (in == null)
			throw new IllegalArgumentException("bad coordinate");
		return Math.abs(longitude - in.getLongitude());
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
}
