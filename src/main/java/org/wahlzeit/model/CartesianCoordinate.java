package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {

	private double x;
	private double y;
	private double z;
	
	/*
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * @methodtype query
	 */
	@Override
	public double getDistance(Coordinate in) {
		assertValidCoordinate(in);
		
		CartesianCoordinate tmp = asCartesianCoordinate(in);
		
		double distance = Math.sqrt( Math.pow(x - tmp.getX(), 2) + Math.pow(y - tmp.getY(), 2) + Math.pow(z - tmp.getZ(), 2) );
		double w = 2 * Math.asin( (distance/2)/EARTHRADIUS );
		
		return w * EARTHRADIUS;
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
	private void assertNotNaN(double in) throws IllegalArgumentException {
		if( Double.isNaN(in) ) {
			throw new IllegalArgumentException();
		}
	}
}
