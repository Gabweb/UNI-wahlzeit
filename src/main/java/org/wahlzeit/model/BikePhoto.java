package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BikePhoto extends Photo {

	private String category = "Unknown"; // Maintainbike, Rennrad, Fixie, etc.
	private String brand = "Unknown";
	private String color = "Unknown";
	private double weight;

	/*
	 * @methodtype constructor
	 */
	public BikePhoto() {
		super();
	}

	/*
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId) {
		super(myId);
	}
	
	/*
	 * @methodtype get
	 */
	public String getCategory() {
		return category;
	}

	/*
	 * @methodtype set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/*
	 * @methodtype get
	 */
	public String getBrand() {
		return brand;
	}

	/*
	 * @methodtype set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/*
	 * @methodtype get
	 */
	public String getColor() {
		return color;
	}

	/*
	 * @methodtype set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/*
	 * @methodtype get
	 */
	public double getWeight() {
		return weight;
	}

	/*
	 * @methodtype set
	 */
	public void setWeight(double weight) throws IllegalArgumentException {
		if ( !isValidWeight(weight) ) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}
	
	/*
	 * @methodtype boolean query
	 */	
	private boolean isValidWeight(double weight) {
		if( weight > 0 && weight < 100) {
			return true;
		}
		return false;
	}

}
