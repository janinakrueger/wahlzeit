package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class LandscapeTest {

    @Test
    public void testGetType() {
        Location location = Mockito.mock(Location.class);
        LandscapeType type = new LandscapeType("testType");
        Landscape landscape = new Landscape(type, location);
        assertEquals("testType", landscape.getType());
    }
}
