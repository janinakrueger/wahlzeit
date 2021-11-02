package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Enclosed.class)
public class CoordinateTest {

    public static class NotParametrized{
        @Test
        public void testIsGivenCoordinate() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            Coordinate co = new Coordinate(coordinate.getX(), coordinate.getY(), coordinate.getZ());
            assertEquals(co, coordinate);
        }

        @Test
        public void testSetCoordinate() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            coordinate.setX(1.0);
            coordinate.setY(2.0);
            coordinate.setZ(3.0);
            Coordinate co = new Coordinate(1.0, 2.0, 3.0);
            assertEquals(coordinate, co);
        }

        @Test
        public void testGetDistanceSelf() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            double dist = coordinate.getDistance(coordinate);
            assertEquals(dist, 0.0, 0.0001);
        }

        @Test
        public void testIsEqualSelf() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(coordinate);
            assertEquals(e, true);
        }

        @Test
        public void testEqualsSelf() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(coordinate);
            assertEquals(e, true);
        }
    }

    @RunWith(Parameterized.class)
    public static class Parametrized {
        private Coordinate c;
        private boolean isEqual;
        private double distance;

        public Parametrized(Coordinate c, boolean isEqual, double distance) {
            this.c = c;
            this.isEqual = isEqual;
            this.distance = distance;
        }

        @Parameterized.Parameters
        public static Collection coordinates() {
            return Arrays.asList(new Object[][] { 
                {new Coordinate(0.0,0.0,0.0), true, 0.0},
                {new Coordinate(1.0,0.0,0.0), false, 1.0},
                {new Coordinate(0.0,2.0,0.0), false, 2.0},
                {new Coordinate(0.0,0.0,3.0), false, 3.0},
                {new Coordinate(2.0,2.0,1.0), false, 3.0}
            });
        }


        @Test
        public void testGetDistance() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            double dist = coordinate.getDistance(c);
            assertEquals(dist, distance, 0.0001);
        }

        @Test
        public void testIsEqual() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.isEqual(c);
            assertEquals(e, isEqual);
        }

        @Test
        public void testEquals() {
            Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
            boolean e = coordinate.equals(c);
            assertEquals(e, isEqual);
        }
    }
}
