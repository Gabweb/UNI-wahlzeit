/*
 * Cartesian Coordinate class that works with classic x,y,z
 */
package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CartesianCoordinate extends AbstractCoordinate {

	private double x;
	private double y;
	private double z;
	
	/*
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/*
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/*
	 * @methodtype set
	 */
	public void setX(double x) {
		assertNotNaN(x);
		this.x = x;
	}

	/*
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}
	
	/*
	 * @methodtype set
	 */
	public void setY(double y) {
		assertNotNaN(y);
		this.y = y;
	}

	/*
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/*
	 * @methodtype set
	 */
	public void setZ(double z) {
		assertNotNaN(z);
		this.z = z;
	}

	/*
	 * @methodtype helper
	 */
	protected double doGetDistance(Coordinate in) {
		CartesianCoordinate tmp = in.asCartesianCoordinate();
		
		double distance = Math.sqrt( Math.pow(x - tmp.getX(), 2) + Math.pow(y - tmp.getY(), 2) + Math.pow(z - tmp.getZ(), 2) );
		double w = 2 * Math.asin( (distance/2)/EARTHRADIUS );
		
		assert(w >= 0);
		
		return w * EARTHRADIUS;
	}
	
	/*
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate() {
		SphericCoordinate ret;

		CartesianCoordinate tmp = (CartesianCoordinate)this;
		double r = Math.sqrt(tmp.getX() * tmp.getX() + tmp.getY() * tmp.getY() + tmp.getZ()* tmp.getZ()); 
		double lat = Math.toDegrees(Math.asin(tmp.getZ()/r));
		double lon = Math.toDegrees(Math.atan2(tmp.getY(), tmp.getX()));
		
		ret = new SphericCoordinate(lat,lon);

		assert( ret != null );
		ret.assertClassInvariants();
		
		return ret;
	}
	
	/*
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() {
		CartesianCoordinate ret = (CartesianCoordinate)this;
		
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
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		if (!(obj instanceof CartesianCoordinate))
			return false;
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	/*
	 * @methodtype assertion
	 */
	public void assertClassInvariants() throws IllegalStateException {
		try {
			assertNotNaN(this.x);
			assertNotNaN(this.y);
			assertNotNaN(this.z);
		}
		catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
	}
}
