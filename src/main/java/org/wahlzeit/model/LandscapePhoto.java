package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LandscapePhoto extends Photo {

	private Landscape landscape;
	/**
	 * @methodtype constructor
	 */
	public LandscapePhoto() {
		super();
	}

	/**
	 * 
	 * @methodtype constructor
	 * New constructor using Landscape attribute.
	 */
	public LandscapePhoto(Landscape landscape) {
		super();
		setLandscape(landscape);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(PhotoId myId) {
		super(myId);
	}
	
	/**
	 * 
	 * @methodtype constructor
	 */
	public LandscapePhoto(ResultSet rset) throws SQLException { 
		super(rset);
	}

	public Landscape getLandscape() {
		return landscape;
	}
	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}
}
