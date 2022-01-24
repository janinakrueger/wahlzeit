package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

public class LandscapePhotoFactory extends PhotoFactory {

	private static PhotoFactory instance = null;

    /**
     * @methodtype constructor
     */
    protected LandscapePhotoFactory() {
        super();
    }

    /**
	 * Public singleton access method.
	 */
	public static synchronized PhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic LandscapePhotoFactory");
			setInstance(new LandscapePhotoFactory());
		}
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(PhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize LandscapePhotoFactory twice");
		}
		instance = photoFactory;
	}

    /**
	 * @methodtype factory
	 */
    @Override
	public Photo createPhoto() {
		return new LandscapePhoto(); 
	}
	
	/**
	 * 
	 */
    @Override
	public Photo createPhoto(PhotoId id) {
		return new LandscapePhoto(id);
	}
	
	/**
	 * 
	 */
    @Override
	public Photo createPhoto(ResultSet rs) throws SQLException { // 2. Called by LandscapePhotoManager, calls constructor of LandscapePhoto
		return new LandscapePhoto(rs);
	}

	// create a photo using Landscape attribute
	public Photo createPhoto(String typeName, Location location) {
		LandscapeManager manager = LandscapeManager.getInstance();
		Landscape landscape = manager.createLandscape(typeName, location);
		return new LandscapePhoto(landscape);
	}
	
}
