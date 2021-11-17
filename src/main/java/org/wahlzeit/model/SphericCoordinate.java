package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.wahlzeit.services.DataObject;

public class SphericCoordinate extends DataObject implements Coordinate {
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
	 * @methodtype boolean query method
	 */
    @Override 
	public boolean isEqual(Coordinate c) {
        CartesianCoordinate cartesianCoordinate = c.asCartesianCoordinate();
		return cartesianCoordinate.isEqual(this);
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
		return Objects.hash(phi,theta,radius); 
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
	 * 
	 */
	@Override
	public void writeId(PreparedStatement stmt, int pos) throws SQLException { 
		
	}

	/**
	 * 
	 */
	@Override
	public String getIdAsString() {
		return "id";
	}

	/**
	 * @methodtype conversion
	 */
    @Override 
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius*Math.sin(theta)*Math.cos(phi);
        double y = radius*Math.sin(theta)*Math.sin(phi);
        double z = radius*Math.cos(theta);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
        return cartesianCoordinate;
	}

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCartesianDistance(Coordinate coordinate) {
		return asCartesianCoordinate().getCartesianDistance(coordinate);
	}
    
	/**
	 * @methodtype conversion
	 */
    @Override 
    public SphericCoordinate asSphericCoordinate() {
		return this;
	}
    
	/**
	 * @methodtype get
	 */
    @Override 
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double longituteDelta = Math.abs(phi - sphericCoordinate.phi);
        double centralAngle = Math.acos(Math.sin(theta)*Math.sin(sphericCoordinate.theta) + Math.cos(theta)*Math.cos(sphericCoordinate.theta)*Math.cos(longituteDelta));
        return centralAngle;
	}

}
