package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandscapePhotoManager extends PhotoManager {

	/**
	 * 
	 */
	protected static final PhotoManager instance = new PhotoManager();

	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhotoManager() {
		photoTagCollector = LandscapePhotoFactory.getInstance().createPhotoTagCollector();
	}

	/**
	 * 
	 */
	@Override
	protected Photo createObject(ResultSet rset) throws SQLException { // 1. Start of object creation for LandscapePhoto
		return LandscapePhotoFactory.getInstance().createPhoto(rset);
	}

}