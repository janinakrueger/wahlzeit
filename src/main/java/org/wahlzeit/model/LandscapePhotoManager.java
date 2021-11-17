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
	protected Photo createObject(ResultSet rset) throws SQLException {
		return LandscapePhotoFactory.getInstance().createPhoto(rset);
	}

}