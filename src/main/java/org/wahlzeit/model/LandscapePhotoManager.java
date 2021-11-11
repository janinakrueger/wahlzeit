package org.wahlzeit.model;

public class LandscapePhotoManager extends PhotoManager {

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhotoManager() {
		photoTagCollector = LandscapePhotoFactory.getInstance().createPhotoTagCollector();
	}

}
