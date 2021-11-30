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
    public void setPhi(double phi) {
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
    public void setTheta(double theta) {
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
    public void setRadius(double radius) {
		assertIsValidRadius(radius);
        this.radius = radius;
		assertClassInvariants();
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
    public double getCentralAngle(Coordinate coordinate) {
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
	protected void assertIsValidPhi(double phi) {
		assert phi > -180 && phi <= 180 : "Phi is not in the range (-180,180].";
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidTheta(double theta) {
		assert theta >= 0 && theta <= 180 : "Theta is not in the range [0,180].";
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidRadius(double radius) {
		assert radius >= 0 : "Radius is negative.";
	}

	/**
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		assertIsValidPhi(phi);
		assertIsValidTheta(theta);
		assertIsValidRadius(radius);
	}
}
