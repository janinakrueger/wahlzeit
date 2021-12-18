package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AbstractCoordinateTest {
    @Test
    public void testIsEqualSperic() {
        AbstractCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
        AbstractCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        boolean isEqual = sphericCoordinate.isEqual(cartesianCoordinate);
        assertEquals(isEqual, true); 
    }

    @Test
    public void testIsEqualCartesian() {
        AbstractCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
        AbstractCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        boolean isEqual = cartesianCoordinate.isEqual(sphericCoordinate);
        assertEquals(isEqual, true);
    }

    @Test(expected = NullPointerException.class)
    public void testIsEqualArgumentIsNull() {
        AbstractCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
        AbstractCoordinate cartesianCoordinate = null;
        boolean isEqual = sphericCoordinate.isEqual(cartesianCoordinate);
    }

    @Test(expected = NullPointerException.class)
    public void getCartesianDistanceArgumentIsNull() {
        AbstractCoordinate sphericCoordinate = null;
        AbstractCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        double distance = cartesianCoordinate.getCartesianDistance(sphericCoordinate);
    }
    
    @Test(expected = NullPointerException.class)
    public void getCentralAngleArgumentIsNull() {
        AbstractCoordinate sphericCoordinate = null;
        AbstractCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(2, 4, 6);
        double angle = cartesianCoordinate.getCentralAngle(sphericCoordinate);
    }
}
