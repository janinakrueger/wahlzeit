package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

@RunWith(Enclosed.class)
public class SphericCoordinateTest {

    public static class NotParametrized{

        @Test
        public void testGetSameCoordinate() {
            SphericCoordinate coordinateFirst = SphericCoordinate.getSphericCoordinate(1, 2, 3);
            SphericCoordinate coordinateSecond = SphericCoordinate.getSphericCoordinate(1, 2, 3);
            assert(coordinateFirst == coordinateSecond);
        }

        @Test
        public void testGetNewCoordinate() {
            SphericCoordinate coordinateFirst = SphericCoordinate.getSphericCoordinate(1, 2, 3);
            SphericCoordinate coordinateSecond = SphericCoordinate.getSphericCoordinate(1, 2, 5);
            assert(coordinateFirst != coordinateSecond);
        }

        @Test
        public void testSetSameCoordinate() {
            SphericCoordinate coordinateFirst = SphericCoordinate.getSphericCoordinate(1, 2, 3);
            SphericCoordinate coordinateSecond = SphericCoordinate.getSphericCoordinate(1, 2, 5);
            SphericCoordinate coordinateThird = coordinateSecond.setRadius(3);
            assert(coordinateFirst == coordinateThird);
        }

        @Test
        public void testIsEqualSelf() {
            Coordinate coordinate = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(coordinate);
            assertEquals(e, true);
        }

        @Test
        public void testEqualsSelf() {
            Coordinate coordinate = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(coordinate);
            assertEquals(e, true);
        }

        @Test
        public void testWriteOn() throws SQLException { 
            Coordinate coordinate = SphericCoordinate.getSphericCoordinate(2,4,6);
            ResultSet rset = Mockito.mock(ResultSet.class);
    
            coordinate.writeOn(rset);
    
            verify(rset, Mockito.times(1)).updateDouble("phi", 2); 
            verify(rset, Mockito.times(1)).updateDouble("theta", 4);
            verify(rset, Mockito.times(1)).updateDouble("radius", 6);
        }
    
        @Test
        public void testReadFrom() throws SQLException {
            Coordinate coordinate = SphericCoordinate.getSphericCoordinate(2,4,6);
            ResultSet rset = Mockito.mock(ResultSet.class);
    
            coordinate.readFrom(rset);
    
            verify(rset, Mockito.times(1)).getDouble("phi");
            verify(rset, Mockito.times(1)).getDouble("theta");
            verify(rset, Mockito.times(1)).getDouble("radius");
        }

        @Test
        public void testAsCartesianCoordinate() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(1.10714, 0.64052, 7.48331);
            CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
            assertEquals(CartesianCoordinate.getCartesianCoordinate(2, 4, 6), cartesianCoordinate);
        }
        @Test(expected = IllegalArgumentException.class)
        public void testInvalidPhi() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(-200,4,6);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testInvalidTheta() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(2,200,6);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testInvalidRadius() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(2,4,-0.1);
        }

        @Test(expected = NullPointerException.class)
        public void testCartesianCoordinateIsNull() {
            SphericCoordinate coordinate = null;
            coordinate.asCartesianCoordinate();          
        }
    }

    @RunWith(Parameterized.class)
    public static class Parametrized {
        private SphericCoordinate c;
        private boolean isEqual;
        private double distance;

        public Parametrized(SphericCoordinate c, boolean isEqual, double distance) {
            this.c = c;
            this.isEqual = isEqual;
            this.distance = distance;
        }

        @Parameterized.Parameters
        public static Collection coordinates() { 
            return Arrays.asList(new Object[][] { 
                {SphericCoordinate.getSphericCoordinate(0.0,0.0,0.0), true, 0.0},
                {SphericCoordinate.getSphericCoordinate(0.0,1.57079,1.0), false, 1.0},
                {SphericCoordinate.getSphericCoordinate(1.57079,1.57079,2.0), false, 2.0},
                {SphericCoordinate.getSphericCoordinate(0.78539,1.23095,3.0), false, 3.0}
            });
        }


        @Test
        public void testGetCartesianDistance() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
            double dist = coordinate.getCartesianDistance(c);
            double epsilon = 0.0001;
            assertEquals(dist, distance, epsilon);
        }


        @Test
        public void testIsEqual() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(c);
            assertEquals(e, isEqual);
        }

        @Test
        public void testEquals() {
            SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(c);
            assertEquals(e, isEqual);
        }
    }
}
