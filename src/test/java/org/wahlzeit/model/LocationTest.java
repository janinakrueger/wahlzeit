package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class LocationTest {
    
    @Test
    public void testHasGivenCoordinate() {
        Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
        Location location = new Location(coordinate);
        assertEquals(location.getCoordinate(), coordinate);
    }

    @Test
    public void testHasSetCoordinate() {
        Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
        Location location = new Location(coordinate);
        Coordinate c = new Coordinate(1.0, 1.0, 1.0);
        location.setCoordinate(c);
        assertEquals(location.getCoordinate(), c);
    }

    @Test
    public void testWriteOnCoordinate() throws SQLException {
        Coordinate coordinate = Mockito.mock(Coordinate.class);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = new Location(coordinate);

        location.writeOn(rset);

        verify(coordinate, Mockito.times(1)).writeOn(rset);
    }

    @Test
    public void testReadFromCoordinate() throws SQLException {
        Coordinate coordinate = Mockito.mock(Coordinate.class);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = new Location(coordinate);

        location.readFrom(rset);

        verify(coordinate, Mockito.times(1)).readFrom(rset);
    }
}
