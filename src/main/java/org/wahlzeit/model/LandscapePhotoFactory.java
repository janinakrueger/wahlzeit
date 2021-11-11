package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.SysLog;

public class LandscapePhotoFactory extends PhotoFactory {

	private static LandscapePhotoFactory instance = null;

    /**
     * @methodtype constructor
     */
    protected LandscapePhotoFactory() {
        super();
    }

    /**
	 * Public singleton access method.
	 */
	public static synchronized LandscapePhotoFactory getInstance() {
		if (instance == null) {
			SysLog.logSysInfo("setting generic LandscapePhotoFactory");
			setInstance(new LandscapePhotoFactory());
		}
		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(LandscapePhotoFactory landscapePhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initialize LandscapePhotoFactory twice");
		}
		instance = landscapePhotoFactory;
	}

    /**
	 * @methodtype factory
	 */
    @Override
	public LandscapePhoto createPhoto() {
		return new LandscapePhoto(); 
	}
	
	/**
	 * 
	 */
    @Override
	public LandscapePhoto createPhoto(PhotoId id) {
		return new LandscapePhoto(id);
	}
	
	/**
	 * 
	 */
    @Override
	public LandscapePhoto createPhoto(ResultSet rs) throws SQLException {
		return new LandscapePhoto(rs);
	}
	
}
