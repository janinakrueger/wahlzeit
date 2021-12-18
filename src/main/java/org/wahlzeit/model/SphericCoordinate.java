package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SphericCoordinate extends AbstractCoordinate {
 	/**
	 * Final immutable attributes which describe the coordinate, because it is a value object.
	 */
	private final double phi;
    private final double theta;
    private final double radius;

	private static List<SphericCoordinate> allSphericCoordinates = new ArrayList<>(); 

	// show thrown unchecked exception in signature for clarity
	private SphericCoordinate(double phi, double theta, double radius) throws IllegalArgumentException {
		this.phi = phi;
		this.theta = theta;
		this.radius = radius;

		assertClassInvariants();
	}

	// Method to return the value object with given parameters. 
	// Is synchronized, so no two users can instantiate a new object which represents the same coordinate.
	// There is only one object of the same SphericCoordinate, all objects are saved in a list.
	public static synchronized SphericCoordinate getSphericCoordinate(double phi, double theta, double radius) {
		SphericCoordinate coordinate = new SphericCoordinate(phi, theta, radius); // new coordinate is instantiated, but only returned, if the same coordinate does not yet exist.
		SphericCoordinate resultCoordinate;
		int index = allSphericCoordinates.indexOf(coordinate);
		if (index == -1) { // Coordinate does not yet exist
			resultCoordinate = coordinate;
			allSphericCoordinates.add(resultCoordinate);
		}
		else { // Coordinate already exists and is taken out of the list with all objects.
			resultCoordinate = allSphericCoordinates.get(index);
		}
		return resultCoordinate;
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
    public SphericCoordinate setPhi(double phi) throws IllegalArgumentException {
		assertIsValidPhi(phi);
		assertClassInvariants();
		return getSphericCoordinate(phi, theta, radius);
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
    public SphericCoordinate setTheta(double theta) throws IllegalArgumentException {
		assertIsValidTheta(theta);
		assertClassInvariants();
		return getSphericCoordinate(phi, theta, radius);
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
    public SphericCoordinate setRadius(double radius) throws IllegalArgumentException {
		assertIsValidRadius(radius);
		assertClassInvariants();
		return getSphericCoordinate(phi, theta, radius);
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
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(x, y, z);

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
