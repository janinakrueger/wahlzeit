package org.wahlzeit.model;

import org.wahlzeit.services.Persistent;

public interface Coordinate extends Persistent {
	
	/**
	 * 
	 */
    public CartesianCoordinate asCartesianCoordinate();

	/**
	 * 
	 */
    public double getCartesianDistance(Coordinate coordinate);
    
	/**
	 * 
	 */
    public SphericCoordinate asSphericCoordinate();
    
	/**
	 * 
	 */
    public double getCentralAngle(Coordinate coordinate);
    
	/**
	 * 
	 */
    public boolean isEqual(Coordinate coordinate);

}