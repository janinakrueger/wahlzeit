package org.wahlzeit.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.services.DataObject;

/**
 * Location of a user-provided (uploaded) photo.
 */

public class Location extends DataObject {

    protected Coordinate coordinate;

	public Location(Coordinate coordinate) throws NullPointerException {
		assertIsNonNullArgument(coordinate);
		setCoordinate(coordinate);
	}

	/**
	 * 
	 * @methodtype set
	 */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

	/**
	 * 
	 * @methodtype get
	 */
    public Coordinate getCoordinate() {
        return coordinate;
    }

	/**
	 * 
	 */
	@Override
	public void writeOn(ResultSet rset) throws SQLException {
		coordinate.writeOn(rset);
	}
	
	/**
	 * 
	 */
	@Override
	public void readFrom(ResultSet rset) throws SQLException {
		coordinate.readFrom(rset);
	}

	/**
	 * 
	 */
	@Override
	public boolean isDirty() {
		boolean selfDirty = writeCount != 0;
		boolean coordinateDirty = coordinate.isDirty();
		return selfDirty || coordinateDirty;
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
	 * @methodtype assertion
	 */
	// Method used to check if null argument is given as a parameter to methods
	protected void assertIsNonNullArgument(Object o) throws NullPointerException {
		if (o == null) {
			throw new NullPointerException("The input is null.");
		}
	}
}
