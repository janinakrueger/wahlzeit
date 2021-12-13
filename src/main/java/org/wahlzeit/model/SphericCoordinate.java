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

	// show thrown unchecked exception in signature for clarity
	public SphericCoordinate(double phi, double theta, double radius) throws IllegalArgumentException {
		setPhi(phi);
		setTheta(theta);
		setRadius(radius);

		assertClassInvariants();
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
    public void setPhi(double phi) throws IllegalArgumentException {
		assertIsValidPhi(phi);
        this.phi = phi;
		assertClassInvariants();
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
    public void setTheta(double theta) throws IllegalArgumentException {
		assertIsValidTheta(theta);
        this.theta = theta;
		assertClassInvariants();
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
    public void setRadius(double radius) throws IllegalArgumentException {
		assertIsValidRadius(radius);
        this.radius = radius;
		assertClassInvariants();
	}

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException { 
		assertClassInvariants();
		rset.updateDouble("phi", phi);
		rset.updateDouble("theta", theta);
		rset.updateDouble("radius", radius);
		assertClassInvariants();
	}

	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		assertClassInvariants();
		rset.getDouble("phi");
		rset.getDouble("theta");
		rset.getDouble("radius");
		assertClassInvariants();
	}

	/**
	 * @methodtype conversion
	 */
    @Override 
    public CartesianCoordinate asCartesianCoordinate() throws ArithmeticException, IllegalArgumentException, NullPointerException {
		assertClassInvariants();
        double x = radius*Math.sin(theta)*Math.cos(phi);
        double y = radius*Math.sin(theta)*Math.sin(phi);
        double z = radius*Math.cos(theta);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);

		assertIsNonNullArgument(cartesianCoordinate);
		assertClassInvariants();
        return cartesianCoordinate;
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
    public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException {
		assertClassInvariants();

		// for calculation use phi, theta, radius Attributes of SphericCoordinate
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();

        double longituteDelta = Math.abs(sphericCoordinate.getPhi() - getPhi());
        double centralAngle = Math.acos(Math.sin(sphericCoordinate.getTheta())*Math.sin(getTheta()) + Math.cos(sphericCoordinate.getTheta())*Math.cos(getTheta())*Math.cos(longituteDelta));
		
		assertClassInvariants();
        return centralAngle;
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidPhi(double phi) throws IllegalArgumentException {
		if (phi < -180 || phi > 180) {
			throw new IllegalArgumentException("Phi is not in the range (-180,180].");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidTheta(double theta) throws IllegalArgumentException {
		if (theta < 0 || theta > 180) {
			throw new IllegalArgumentException("Theta is not in the range [0,180].");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidRadius(double radius) throws IllegalArgumentException {
		if (radius < 0) {
			throw new IllegalArgumentException("Radius is negative.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() throws IllegalArgumentException {
		super.assertClassInvariants();
		assertIsValidPhi(phi);
		assertIsValidTheta(theta);
		assertIsValidRadius(radius);
	}
}
