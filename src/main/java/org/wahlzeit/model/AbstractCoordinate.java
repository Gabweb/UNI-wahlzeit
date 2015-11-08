package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	protected final static double EARTHRADIUS = 6371;
	
	/*
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate in) {
		assertValidCoordinate(in);
		
		SphericCoordinate me = asSphericCoordinate(this);
		SphericCoordinate other = asSphericCoordinate(in);
		
		double offset = 0.0000000001;
		
		if( Math.abs(me.getLatitude() - other.getLatitude()) <= offset && Math.abs(me.getLongitude() - other.getLongitude()) <= offset ) {
			return true;
		}
		return false;
	}
	
	/*
	 * @methodtype conversion
	 */
	protected static SphericCoordinate asSphericCoordinate(Coordinate in) throws IllegalArgumentException {
		SphericCoordinate ret;
		if( in instanceof CartesianCoordinate ) {
			CartesianCoordinate tmp = (CartesianCoordinate)in;
			double r = Math.sqrt(tmp.getX() * tmp.getX() + tmp.getY() * tmp.getY() + tmp.getZ()* tmp.getZ()); 
			double lat = Math.toDegrees(Math.asin(tmp.getZ()/r));
			double lon = Math.toDegrees(Math.atan2(tmp.getY(), tmp.getX()));
			
			ret = new SphericCoordinate(lat,lon);
		}
		else if( in instanceof SphericCoordinate ) {
			ret = (SphericCoordinate)in;
		}
		else {
			throw new IllegalArgumentException("Unknown instance of coordinate");
		}
		//Just to make sure everything works well.
		assert( ret != null );
		return ret;
	}
	
	/*
	 * @methodtype conversion
	 */
	protected static CartesianCoordinate asCartesianCoordinate(Coordinate in) throws IllegalArgumentException {
		CartesianCoordinate ret;
		if( in instanceof SphericCoordinate ) {
			SphericCoordinate tmp = (SphericCoordinate)in;
		    double lat = Math.toRadians( tmp.getLatitude() );
		    double lon = Math.toRadians( tmp.getLongitude() );
		    double x = EARTHRADIUS * Math.cos(lat)*Math.cos(lon);
		    double y = EARTHRADIUS * Math.cos(lat)*Math.sin(lon);
		    double z = EARTHRADIUS * Math.sin(lat);
		    ret = new CartesianCoordinate(x, y, z);
		}
		else if( in instanceof CartesianCoordinate ) {
			ret = (CartesianCoordinate)in;
		}
		else {
			throw new IllegalArgumentException("Unknown instance of coordinate");
		}
		//Just to make sure everything works well.
		assert( ret != null );
		return ret;
	}
	
	/*
	 * @methodtype assertion
	 */
	protected void assertValidCoordinate(Coordinate in) throws IllegalArgumentException {
		if (in == null)
			throw new IllegalArgumentException("bad coordinate");		
	}
	
}
