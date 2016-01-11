/*
 * Singelton Manager to handle those BikeTypes
 */
package org.wahlzeit.model;

import java.util.HashMap;

public class BikeManager {
	
	protected static final BikeManager instance = new BikeManager();
	
	protected HashMap<String, BikeType> bikeTypes = new HashMap<String, BikeType>();

	/**
	 * @methodetype get
	 * @return
	 */
	public static final BikeManager getInstance() {
		return instance;
	}
	
	/**
	 * @methodetype set
	 * @param bt
	 * @param typeName
	 */
	public void addBikeType(BikeType bt, String typeName) {
		assert(bikeTypes.get(typeName) != null) : "typeName already exists";
		bikeTypes.put(typeName, bt);
	}
	
	/**
	 * @methodetype get
	 * @param typeName
	 * @return
	 */
	public BikeType getBikeType(String typeName) {
		return bikeTypes.get(typeName);
	}
	
	/**
	 * @methodetype factory
	 * @param typeName
	 * @return
	 */
	public Bike createBike(String typeName) {
		BikeType bt = getBikeType(typeName);
		assert (bt != null) : "invalid typeName";
		return bt.createInstance();
	}


}
