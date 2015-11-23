/*
 * Abstract Coordinate class that implements some functions used by the subclasses
 */

package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	protected final static double EARTHRADIUS = 6371;
	/*
	 * @methodtype template
	 */
	public double getDistance(Coordinate in) {
		assertValidCoordinate(in);
		return doGetDistance(in);
	}
	
	/*
	 * @methodtype helper
	 */
	protected abstract double doGetDistance(Coordinate in);
	
	/*
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate in) {
		if( in == null ) {
			return false;
		}
		
		SphericCoordinate me = this.asSphericCoordinate();
		SphericCoordinate other = in.asSphericCoordinate();
		
		double offset = 0.0000000001;
		
		if( Math.abs(me.getLatitude() - other.getLatitude()) <= offset && Math.abs(me.getLongitude() - other.getLongitude()) <= offset ) {
			return true;
		}
		return false;
	}
	
	/*
	 * @methodtype assertion
	 */
	protected static void assertValidCoordinate(Coordinate in) throws IllegalArgumentException {
		if (in == null)
			throw new IllegalArgumentException("bad coordinate");		
	}
	
	/*
	 * @methodtype assertion
	 */
	protected void assertNotNaN(double in) throws IllegalArgumentException {
		if( Double.isNaN(in) ) {
			throw new IllegalArgumentException();
		}
	}	
}
