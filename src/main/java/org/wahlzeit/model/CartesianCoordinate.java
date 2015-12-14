/*
 * Cartesian Coordinate class that works with classic x,y,z
 */
package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

import com.googlecode.objectify.annotation.Subclass;

@Pattern(name = "Template", participants = {"AbstactCoordinate", "CartesianCoordinate", "SphericCoordinate"})
@Subclass
public class CartesianCoordinate extends AbstractCoordinate {

	private final double x;
	private final double y;
	private final double z;
	
	/*
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		assertNotNaN(x);
		this.x = x;
		
		assertNotNaN(y);
		this.y = y;
		
		assertNotNaN(z);
		this.z = z;
	}
	
	/*
	 * @methodtype factory method
	 */
	public static CartesianCoordinate getCoordinate(double x, double y, double z) {
		CartesianCoordinate tmp = new CartesianCoordinate(x, y, z);
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
		return (CartesianCoordinate)result;
	}
	
	/*
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/*
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/*
	 * @methodtype get
	 */
	public double getZ() {
		return z;
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
