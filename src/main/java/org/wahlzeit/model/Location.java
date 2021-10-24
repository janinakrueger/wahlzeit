package org.wahlzeit.model;

/**
 * Location of a user-provided (uploaded) photo.
 */

public class Location {

    protected Coordinate coordinate;

	public Location(Coordinate coordinate){
		setCoordinate(coordinate);
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

	/**
	 * 
	 * @methodtype get
	 */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
