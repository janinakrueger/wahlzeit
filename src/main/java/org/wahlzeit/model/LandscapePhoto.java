package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

// Object creation table
// Delegation: separate-object 
// Selection: by-subclassing
// Configuration: in-code
// Instantiation: in-code 
// Initialization: default
// Building: default

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
	public LandscapePhoto(ResultSet rset) throws SQLException { // 3. Photo is created after constructor call of Factory
		super(rset);
	}

	public Landscape getLandscape() {
		return landscape;
	}
	public void setLandscape(Landscape landscape) {
		this.landscape = landscape;
	}
}
