package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BikeType {

	protected String categorie; //mtb, rennrad, etc. - used to build hierachry
	protected String brand;
	protected String model;
	
	protected BikeType superType = null;
	protected Set<BikeType> subTypes = new HashSet<BikeType>();
	
	/**
	 * @methodtype constructor
	 * @param brand
	 * @param model
	 * @param categorie
	 */
	public BikeType(String brand, String model, String categorie) {
		this.brand = brand;
		this.model = model;
		this.categorie = categorie;
	}
	
	/**
	 * @methodtype constructor
	 * @param categorie
	 */
	public BikeType(String categorie) {
		this(null, null, categorie);
	}
	
	/**
	 * @methodtype factory
	 * @return
	 */
	public Bike createInstance() {
		return new Bike(this);
	}

	/**
	 * @methodtype set
	 * @param superType
	 */
	public void setSuperType(BikeType superType) {
		this.superType = superType;
	}
	
	/**
	 * @methodtype get
	 * @return
	 */
	public BikeType getSuperType() {
		return superType;
	}
	
	/**
	 * @methodtype get
	 * @return
	 */
	public Iterator<BikeType> getSubTypeIterator() {
		return subTypes.iterator();
	}
	
	/**
	 * @methodtype set
	 * @param bt
	 */
	public  void addSubType(BikeType bt) {
		assert (bt != null) : "tried to set null sub-type";
		subTypes.add(bt);
	}
	
	/**
	 * @methodtype query
	 * @param bike
	 * @return
	 */
	public boolean hasInstance(Bike bike) {
		assert(bike != null) : "asked about null object";
		
		if(bike.getType() == this) {
			return true;
		}
		
		for(BikeType type : subTypes) {
			if(type.hasInstance(bike)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @methodtype set
	 * @param categorie
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @methodtype set
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @methodtype get
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @methodtype set
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}
}
