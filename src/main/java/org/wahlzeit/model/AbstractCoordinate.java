/*
 * Abstract Coordinate class that implements some functions used by the subclasses
 */

package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

import org.wahlzeit.utils.Pattern;

@Pattern(name = "Template", participants = {"AbstactCoordinate", "CartesianCoordinate", "SphericCoordinate"})
public abstract class AbstractCoordinate implements Coordinate {
	protected final static double EARTHRADIUS = 6371;
	protected final static double DELTA = 0.00000001;
	
	protected static Map<Integer, Coordinate> allCoordinates = new HashMap<Integer, Coordinate>();

	/*
	 * @methodtype template
	 */
	public double getDistance(Coordinate in) {
		assertValidCoordinate(in);
		
		double distance = Math.sqrt( Math.pow(getX() - in.getX(), 2) + Math.pow(getY() - in.getY(), 2) + Math.pow(getZ() - in.getZ(), 2) );
		double w = 2 * Math.asin( (distance/2)/EARTHRADIUS );
		
		assert(w >= 0);
		
		return w * EARTHRADIUS;
	}

	/*
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate in) {
		return equals(in);
		/*
		if( in == null ) {
			return false;
		}
		
		if( Math.abs(in.getX() - getX()) >= DELTA ) {
			return false;
		}
		if( Math.abs(in.getY() - getY()) >= DELTA ) {
			return false;
		}
		if( Math.abs(in.getZ() - getZ()) >= DELTA ) {
			return false;
		}
		return true;*/
	}
	
	/*
	* @methodtype boolean query
	*/
	public boolean equals(Coordinate coord) {
		return this == coord;
	}

	/*
	 * @methodtype comparison
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(getX());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getY());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(getZ());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
