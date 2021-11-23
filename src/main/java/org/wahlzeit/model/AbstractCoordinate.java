package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

public abstract class AbstractCoordinate extends DataObject implements Coordinate{

    protected static final double EPSILON = 0.0001;

    // Methods, which apply to all subclasses (CartesianCoordinate and SphericCoordinate) are implemented

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCartesianDistance(Coordinate coordinate) {
        // for calculation use x, y, z Attributes of CartesianCoordinate
        CartesianCoordinate cartesianCoordinateA = coordinate.asCartesianCoordinate();
		CartesianCoordinate cartesianCoordinateB = this.asCartesianCoordinate();
	
		double diffX = cartesianCoordinateA.getX() - cartesianCoordinateB.getX();
		double diffY = cartesianCoordinateA.getY() - cartesianCoordinateB.getY();
		double diffZ = cartesianCoordinateA.getZ() - cartesianCoordinateB.getZ();
		return Math.sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);
	}

	/**
	 * @methodtype get
	 */
    @Override 
    public double getCentralAngle(Coordinate coordinate) {
        // for calculation use phi, theta, radius Attributes of SphericCoordinate
        SphericCoordinate sphericCoordinateA = coordinate.asSphericCoordinate();
        SphericCoordinate sphericCoordinateB = this.asSphericCoordinate();

        double longituteDelta = Math.abs(sphericCoordinateB.getPhi() - sphericCoordinateA.getPhi());
        double centralAngle = Math.acos(Math.sin(sphericCoordinateB.getTheta())*Math.sin(sphericCoordinateA.getTheta()) + Math.cos(sphericCoordinateB.getTheta())*Math.cos(sphericCoordinateA.getTheta())*Math.cos(longituteDelta));
        return centralAngle;
	}

	/**
	 * @methodtype boolean query method
	 */
	@Override 
	public boolean isEqual(Coordinate coordinate) {
        // Check if distance of two coordinates is small, which means that they represent the same point.
		return this.getCartesianDistance(coordinate) <= EPSILON;
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
}
