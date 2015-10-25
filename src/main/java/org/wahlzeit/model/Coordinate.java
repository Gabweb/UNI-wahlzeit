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

	public Coordinate() {	
		latitude = 0;
		longitude = 0;
	}
	
	public Coordinate(double lat, double lon) {
		setLatitude(lat);
		setLongitude(lon);		
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLatitude(double lat) {
		if( lat > 90 || lat < -90 || Double.isNaN(lat) )
			throw new IllegalArgumentException("bad latitude");
		latitude = lat;
	}
	
	public void setLongitude(double lon) {
		if( lon > 180 || lon < -180 || Double.isNaN(lon) )
			throw new IllegalArgumentException("bad longitude");
		longitude = lon;
	}
	
	public Coordinate getDistance(Coordinate in) {
		return new Coordinate( getLatitudinalDistance(in), getLongitudinalDistance(in) );
	}
	
	public double getLatitudinalDistance(Coordinate in) {
		if( in == null )
			throw new IllegalArgumentException("bad coordinate");
		return Math.abs(latitude - in.getLatitude());
	}
	
	public double getLongitudinalDistance(Coordinate in) {
		if( in == null )
			throw new IllegalArgumentException("bad coordinate");
		return Math.abs(longitude - in.getLongitude());
	}
}
