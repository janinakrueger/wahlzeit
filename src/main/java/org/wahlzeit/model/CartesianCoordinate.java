package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cartesian coordinates of a location.
 */

public class CartesianCoordinate extends AbstractCoordinate { 

	/**
	 * List that saves all instantiated coordinate objects
	 */
	private static List<CartesianCoordinate> allCartesianCoordinates = new ArrayList<>();

	/**
	 * Final immutable attributes which describe the coordinate, because it is a value object.
	 */
	private final double x;
    private final double y;
    private final double z;

	// show thrown unchecked exception in signature for clarity
	private CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariants();
	}

	// Method to return the value object with given parameters. 
	// Is synchronized, so no two users can instantiate a new object which represents the same coordinate.
	public static synchronized CartesianCoordinate getCartesianCoordinate(double x, double y, double z) {
		CartesianCoordinate coordinate = new CartesianCoordinate(x, y, z); // new coordinate is instantiated, but only returned, if the same coordinate does not yet exist.
		CartesianCoordinate resultCoordinate;
		int index = allCartesianCoordinates.indexOf(coordinate);
		if (index == -1) { // Coordinate does not yet exist
			resultCoordinate = coordinate;
			allCartesianCoordinates.add(resultCoordinate);
		}
		else { // Coordinate already exists and is taken from the list with all objects.
			resultCoordinate = allCartesianCoordinates.get(index);
		}
		return resultCoordinate;
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
    public CartesianCoordinate setX(double x) throws IllegalArgumentException {
		assertIsValidParameter(x);
		assertClassInvariants();
		return getCartesianCoordinate(x, this.y, this.z);
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
    public CartesianCoordinate setY(double y) throws IllegalArgumentException {
		assertIsValidParameter(y);
		assertClassInvariants();
		return getCartesianCoordinate(this.x, y, this.z);
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
    public CartesianCoordinate setZ(double z) throws IllegalArgumentException {
		assertIsValidParameter(z);
		assertClassInvariants();
		return getCartesianCoordinate(this.x, this.y, z);
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x,y,z);
	}

	/**
	 * @methodtype boolean query method
	 */
	@Override 
	public boolean isEqual(Coordinate coordinate) throws IllegalArgumentException {
		assertClassInvariants();
        // Check if distance of two coordinates is small, which means that they represent the same point.
		boolean isEqual = getCartesianDistance(coordinate) <= EPSILON;
		assertClassInvariants();
		return isEqual;
	}

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException, IllegalArgumentException {
		assertClassInvariants();
		rset.updateDouble("x_coordinate", x);
		rset.updateDouble("y_coordinate", y);
		rset.updateDouble("z_coordinate", z);
		assertClassInvariants();
	}

	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException, IllegalArgumentException {
		assertClassInvariants();
		rset.getDouble("x_coordinate");
		rset.getDouble("y_coordinate");
		rset.getDouble("z_coordinate");
		assertClassInvariants();
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
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException, IllegalArgumentException, NullPointerException {
		assertClassInvariants();
        double radius = Math.sqrt(x*x + y*y + z*z);
		boolean isOrigin = radius <= EPSILON;
		// prevent dividing by 0
		if(isOrigin) {
			return SphericCoordinate.getSphericCoordinate(0, 0, 0);
		}
        double theta = Math.acos(z/radius);
        double phi = Math.atan2(y,x);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(phi, theta, radius);

		assertIsNonNullArgument(sphericCoordinate);
		assertClassInvariants();
        return sphericCoordinate;
	}

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException {
		assertClassInvariants();

		// for calculation use x, y, z Attributes of CartesianCoordinate
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
	
		double diffX = cartesianCoordinate.getX() - getX();
		double diffY = cartesianCoordinate.getY() - getY();
		double diffZ = cartesianCoordinate.getZ() - getZ();
		double distance = Math.sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);

		assertClassInvariants();
		return distance;
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsValidParameter(double parameter) throws IllegalArgumentException {
		if (Double.isNaN(parameter)) {
			throw new IllegalArgumentException("Parameter is NaN.");
		}
		if (!Double.isFinite(parameter)) {
			throw new IllegalArgumentException("Parameter is not finite.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	@Override
	protected void assertClassInvariants() throws IllegalArgumentException {
		super.assertClassInvariants();
		assertIsValidParameter(x);
		assertIsValidParameter(y);
		assertIsValidParameter(z);
	}

}
