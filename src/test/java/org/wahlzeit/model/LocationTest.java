package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
