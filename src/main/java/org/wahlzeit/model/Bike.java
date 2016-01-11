package org.wahlzeit.model;

public class Bike {
	
	private BikeType bikeType = null;
	
	private int gears; //Anzahl der Gänge
	private double weight; //Gewicht
	private String color;
	
	/**
	 * @methodtype constructor
	 * @param bt
	 */
	public Bike(BikeType bt) {
		this(bt, -1, 0, "unknown");
	}
	
	/**
	 * @methodtype constructor
	 * @param bt
	 * @param gears
	 * @param weight
	 * @param color
	 */
	public Bike(BikeType bt, int gears, double weight, String color) {
		this.bikeType = bt;
		this.gears = gears;
		this.weight = weight;
		this.color = color;
	}
	
	/**
	 * @methodtype get
	 * @return
	 */
	public BikeType getType() {
		return bikeType;
	}
	
	/**
	 * @methodtype set
	 * @param bt
	 */
	public void setType(BikeType bt) {
		assert(bt != null) : "null argument not allowed";
		this.bikeType = bt;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public int getGears() {
		return gears;
	}

	/**
	 * @methodtype set
	 * @param gears
	 */
	public void setGears(int gears) {
		this.gears = gears;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @methodtype set
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @methodtype set
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
