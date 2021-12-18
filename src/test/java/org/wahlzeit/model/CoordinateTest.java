package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {
    
    @Test
    public void testIsEqualSperic() {
        Coordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
        Coordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        boolean isEqual = sphericCoordinate.isEqual(cartesianCoordinate);
        assertEquals(isEqual, true);
    }

    @Test
    public void testIsEqualCartesian() {
        Coordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
        Coordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        boolean isEqual = cartesianCoordinate.isEqual(sphericCoordinate);
        assertEquals(isEqual, true);
    }

}
