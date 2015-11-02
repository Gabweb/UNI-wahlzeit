package org.wahlzeit.model;

public class Location {

	private String name;
	public Coordinate coordinate;

	/*
	 * @methodtype constructor
	 */
	public Location() {
		this("", null);
	}

	/*
	 * @methodtype constructor
	 */
	public Location(String name) {
		this(name, null);
	}

	/*
	 * @methodtype constructor
	 */	
	public Location(String name, Coordinate coordinate) {
		this.name = name;
		this.coordinate = coordinate;
	}

	/*
	 * @methodtype set
	 */
	public void setName(String in) {
		name = in;
	}

	/*
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}
		

}
