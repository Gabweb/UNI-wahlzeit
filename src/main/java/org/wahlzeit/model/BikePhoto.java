package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BikePhoto extends Photo {
	
	private Bike bike;

	/*
	 * @methodtype constructor
	 */
	public BikePhoto(Bike bike) {
		super();
		this.bike = bike;
	}

	/*
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId, Bike bike) {
		super(myId);
		this.bike = bike;
	}
	
	/*
	 * @methodtype constructor
	 */
	public BikePhoto() {
		super();
		this.bike = bike;
	}

	/*
	 * @methodtype constructor
	 */
	public BikePhoto(PhotoId myId) {
		super(myId);
		this.bike = bike;
	}

}
