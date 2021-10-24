package org.wahlzeit.model;

/**
 * Cartesian coordinates of a location.
 */

public class Coordinate { 

	/**
	 * Cartesian coordinates
	 */
	private double x;
    private double y;
    private double z;

	public Coordinate(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getX() {
        return x;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setX(double x) {
        this.x = x;
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getY() {
        return y;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setY(double y) {
        this.y = y;
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getZ() {
        return z;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setZ(double z) {
        this.z = z;
	}
}
