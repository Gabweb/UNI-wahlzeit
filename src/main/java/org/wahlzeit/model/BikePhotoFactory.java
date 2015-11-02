package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class BikePhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(BikePhotoFactory.class.getName());

	private static BikePhotoFactory instance = null;

	/*
	 * @methodtype constructor
	 */
	public BikePhotoFactory() {
		super();
	}

	/**
	 * Public singleton access method.
	 * @methodtype get
	 */
	public static synchronized BikePhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
			setInstance(new BikePhotoFactory());
		}

		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 * @methodtype set
	 */
	protected static synchronized void setInstance(BikePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}	

	/*
	 * @methodtype factory
	 */
	@Override
	public BikePhoto createPhoto() {
		return new BikePhoto();
	}

	/*
	 * @methodtype factory
	 */
	@Override
	public BikePhoto createPhoto(PhotoId id) {
		return new BikePhoto(id);
	}

}
