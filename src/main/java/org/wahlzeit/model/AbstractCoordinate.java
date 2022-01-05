package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.wahlzeit.annotations.PatternInstance;
import org.wahlzeit.services.DataObject;

@PatternInstance( 
	patternName = "Template Method",
	participants = {"AbstractClass"}
)

public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    protected static final double EPSILON = 0.0001;

    // Methods, which apply to all subclasses (CartesianCoordinate and SphericCoordinate) are implemented

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCartesianDistance(Coordinate coordinate) {
		assertClassInvariants();
		assertIsNonNullArgument(coordinate); //Precondition

		double distance = coordinate.asCartesianCoordinate().getCartesianDistance(this);

		assertIsValidDistance(distance);
		assertClassInvariants();
		return distance;
	}

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCentralAngle(Coordinate coordinate) {
		assertClassInvariants();
		assertIsNonNullArgument(coordinate); //Precondition

		double centralAngle = coordinate.asSphericCoordinate().getCentralAngle(this);
		
		assertIsValidAngle(centralAngle);
		assertClassInvariants();
        return centralAngle;
	}

	/**
	 * 
	 */
    @Override 
	public boolean equals(Object o) {
        return this.isEqual((Coordinate) o);
    }

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return asCartesianCoordinate().hashCode();
	}
	/**
	 * @methodtype boolean query method
	 */
	@Override 
	public boolean isEqual(Coordinate coordinate) {
		assertClassInvariants();
		assertIsNonNullArgument(coordinate); //Precondition
		if (this == coordinate) {
			return true;
		}
		CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
		// Check if distance of two coordinates is small, which means that they represent the same point.

		boolean isEqual = cartesianCoordinate.isEqual(this);
		assertClassInvariants();
		return isEqual;
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

    // Primitive Methods, which functionality is implemented in subclasses.
    @Override 
    public abstract SphericCoordinate asSphericCoordinate();
    @Override 
    public abstract CartesianCoordinate asCartesianCoordinate();

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidDistance(double distance) throws IllegalArgumentException {
		// Check if distance, taking small EPSILON as fault tolerance.
		if (distance + EPSILON < 0) {
			throw new IllegalArgumentException("The distance is negative.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertIsValidAngle(double angle) throws IllegalArgumentException {
		// Check if ange is in the right range, taking small EPSILON as fault tolerance.
		if (angle - EPSILON < 0 || angle + EPSILON > 360) {
			throw new IllegalArgumentException("The angle is not in the range [0,360].");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertIsNonNullArgument(Coordinate coordinate) throws NullPointerException {
		if (coordinate == null) {
			throw new NullPointerException("The coordinate is null.");
		}
	}

	/**
	 * @methodtype assertion
	 */
	protected void assertClassInvariants() {
	}
	
}
