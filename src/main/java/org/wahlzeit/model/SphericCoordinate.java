package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {
 	/**
	 * Spheric coordinates
	 */
	private double phi;
    private double theta;
    private double radius;

	public SphericCoordinate(double phi, double theta, double radius) {
		setPhi(phi);
		setTheta(theta);
		setRadius(radius);
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getPhi() {
        return phi;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setPhi(double phi) {
        this.phi = phi;
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getTheta() {
        return theta;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setTheta(double theta) {
        this.theta = theta;
	}

	/**
	 * 
	 * @methodtype get
	 */
    public double getRadius() {
        return radius;
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setRadius(double radius) {
        this.radius = radius;
	}

	/**
	 * 
	 */
    @Override 
	public boolean equals(Object o) {
        if (o == this) {
            return true;
		}
        if (!(o instanceof SphericCoordinate)) {
            return false;
		}
		SphericCoordinate c = (SphericCoordinate) o;
        return this.isEqual(c);
    }

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
		double x = cartesianCoordinate.getX();
		double y = cartesianCoordinate.getY();
		double z = cartesianCoordinate.getZ();
		return Objects.hash(x,y,z); 
	}

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException { 
		rset.updateDouble("phi", phi);
		rset.updateDouble("theta", theta);
		rset.updateDouble("radius", radius);
	}

	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		rset.getDouble("phi");
		rset.getDouble("theta");
		rset.getDouble("radius");
	}

	/**
	 * @methodtype conversion
	 */
    @Override 
    public CartesianCoordinate asCartesianCoordinate() throws ArithmeticException {
        double x = radius*Math.sin(theta)*Math.cos(phi);
        double y = radius*Math.sin(theta)*Math.sin(phi);
        double z = radius*Math.cos(theta);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
        return cartesianCoordinate;
	}

	/**
	 * @methodtype conversion
	 */
    @Override 
    public SphericCoordinate asSphericCoordinate() {
		return this;
	}

}
