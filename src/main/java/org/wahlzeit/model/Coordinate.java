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

	protected double getDistance(Coordinate c) {
		double diffX = this.getX() - c.getX();
		double diffY = this.getY() - c.getY();
		double diffZ = this.getZ() - c.getZ();
		return Math.sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);
	}


	protected boolean isEqual(Coordinate c) {
		return this.getDistance(c) <= 0.0001;
	}


    @Override 
	public boolean equals(Object o) {
        if (o == this) {
            return true;
		}
        if (!(o instanceof Coordinate)) {
            return false;
		}
		Coordinate c = (Coordinate) o;
        return this.isEqual(c);
    }
}
