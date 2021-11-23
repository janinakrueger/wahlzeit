package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Cartesian coordinates of a location.
 */

public class CartesianCoordinate extends AbstractCoordinate { 

	/**
	 * Cartesian coordinates
	 */
	private double x;
    private double y;
    private double z;

	public CartesianCoordinate(double x, double y, double z) {
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

	/**
	 * 
	 */
    @Override 
	public boolean equals(Object o) {
        if (o == this) {
            return true;
		}
        if (!(o instanceof CartesianCoordinate)) {
            return false;
		}
		CartesianCoordinate c = (CartesianCoordinate) o;
        return this.isEqual(c);
    }

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x,y,z);
	}

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		rset.updateDouble("x_coordinate", x);
		rset.updateDouble("y_coordinate", y);
		rset.updateDouble("z_coordinate", z);
	}

	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		rset.getDouble("x_coordinate");
		rset.getDouble("y_coordinate");
		rset.getDouble("z_coordinate");
	}

	/**
	 * @methodtype conversion
	 */
	@Override 
    public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}
    
	/**
	 * @methodtype conversion
	 */
	@Override 
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException {
        double radius = Math.sqrt(x*x + y*y + z*z);
		boolean isOrigin = radius <= EPSILON;
		// prevent dividing by 0
		if(isOrigin) {
			return new SphericCoordinate(0, 0, 0);
		}
        double theta = Math.acos(z/radius);
        double phi = Math.atan2(y,x);
        SphericCoordinate sphericCoordinate = new SphericCoordinate(phi, theta, radius);
        return sphericCoordinate;
	}

}
