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
public class CartesianCoordinateTest {

    public static class NotParametrized{

        @Test
        public void testIsEqualSelf() {
            Coordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(coordinate);
            assertEquals(e, true);
        }

        @Test
        public void testEqualsSelf() {
            Coordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(coordinate);
            assertEquals(e, true);
        }

        @Test
        public void testWriteOn() throws SQLException {
            Coordinate coordinate = new CartesianCoordinate(2,4,6);
            ResultSet rset = Mockito.mock(ResultSet.class);
    
            coordinate.writeOn(rset);
    
            verify(rset, Mockito.times(1)).updateDouble("x_coordinate", 2);
            verify(rset, Mockito.times(1)).updateDouble("y_coordinate", 4);
            verify(rset, Mockito.times(1)).updateDouble("z_coordinate", 6);
        }
    
        @Test
        public void testReadFrom() throws SQLException {
            Coordinate coordinate = new CartesianCoordinate(2,4,6);
            ResultSet rset = Mockito.mock(ResultSet.class);
    
            coordinate.readFrom(rset);
    
            verify(rset, Mockito.times(1)).getDouble("x_coordinate");
            verify(rset, Mockito.times(1)).getDouble("y_coordinate");
            verify(rset, Mockito.times(1)).getDouble("z_coordinate");
        }

        @Test
        public void testAsSphericCoordinate() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
            assertEquals(new SphericCoordinate(1.10714, 0.64052, 7.48331), sphericCoordinate);
        }

        @Test(expected = AssertionError.class)
        public void testXIsNan() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setX(Double.NaN);          
        }

        @Test(expected = AssertionError.class)
        public void testXIsInfinite() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setX(Double.POSITIVE_INFINITY);          
        }

        @Test(expected = AssertionError.class)
        public void testYIsNan() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setY(Double.NaN);          
        }

        @Test(expected = AssertionError.class)
        public void testYIsInfinite() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setY(Double.POSITIVE_INFINITY);          
        }
        @Test(expected = AssertionError.class)
        public void testZIsNan() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setZ(Double.NaN);          
        }

        @Test(expected = AssertionError.class)
        public void testZIsInfinite() {
            CartesianCoordinate coordinate = new CartesianCoordinate(2,4,6);
            coordinate.setZ(Double.POSITIVE_INFINITY);          
        }

        @Test(expected = NullPointerException.class)
        public void testSphericCoordinateIsNull() {
            CartesianCoordinate coordinate = null;
            coordinate.asSphericCoordinate();          
        }
    }

    @RunWith(Parameterized.class)
    public static class Parametrized {
        private CartesianCoordinate c;
        private boolean isEqual;
        private double distance;

        public Parametrized(CartesianCoordinate c, boolean isEqual, double distance) {
            this.c = c;
            this.isEqual = isEqual;
            this.distance = distance;
        }

        @Parameterized.Parameters
        public static Collection coordinates() {
            return Arrays.asList(new Object[][] { 
                {new CartesianCoordinate(0.0,0.0,0.0), true, 0.0},
                {new CartesianCoordinate(1.0,0.0,0.0), false, 1.0},
                {new CartesianCoordinate(0.0,2.0,0.0), false, 2.0},
                {new CartesianCoordinate(0.0,0.0,3.0), false, 3.0},
                {new CartesianCoordinate(2.0,2.0,1.0), false, 3.0}
            });
        }

        @Test
        public void testGetCartesianDistance() {
            CartesianCoordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
            double dist = coordinate.getCartesianDistance(c);
            double epsilon = 0.0001;
            assertEquals(dist, distance, epsilon);
        }


        @Test
        public void testIsEqual() {
            CartesianCoordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(c);
            assertEquals(e, isEqual);
        }

        @Test
        public void testEquals() {
            CartesianCoordinate coordinate = new CartesianCoordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(c);
            assertEquals(e, isEqual);
        }
    }
}
