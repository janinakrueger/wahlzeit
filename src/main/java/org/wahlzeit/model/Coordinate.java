package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import org.wahlzeit.services.DataObject;

/**
 * Cartesian coordinates of a location.
 */

public class Coordinate extends DataObject { 

	/**
	 * Cartesian coordinates
	 */
	private double x;
    private double y;
    private double z;
	private static final double EPSILON = 0.0001;

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

	/**
	 * 
	 */
	protected double getDistance(Coordinate c) {
		double diffX = this.getX() - c.getX();
		double diffY = this.getY() - c.getY();
		double diffZ = this.getZ() - c.getZ();
		return Math.sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);
	}

	/**
	 * 
	 */
	protected boolean isEqual(Coordinate c) {
		return this.getDistance(c) <= EPSILON;
	}

	/**
	 * 
	 */
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
}
