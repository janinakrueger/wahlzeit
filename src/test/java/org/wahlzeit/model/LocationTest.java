package org.wahlzeit.model;

import static org.mockito.Mockito.verify;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class LocationTest {

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
