package org.wahlzeit.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class LandscapeManagerTest {

    @Test
    public void testInstanceType() {
        LandscapeManager instance = LandscapeManager.getInstance();
        assertEquals(LandscapeManager.class, instance.getClass());
    }

    // Test if right type is given to Landscape
    @Test
    public void testCreateLandscape() {
        Location location = Mockito.mock(Location.class);
        LandscapeManager manager = LandscapeManager.getInstance();
        Landscape landscape = manager.createLandscape("testType", location);
        assertEquals("testType", landscape.getType());
    }

    // Test if same instance of LandscapeType is returned for same type
    @Test
    public void testGetLandscapeType() {
        LandscapeManager manager = LandscapeManager.getInstance();
        LandscapeType lt1 = manager.getLandscapeType("testType");
        LandscapeType lt2 = manager.getLandscapeType("testType");
        assert(lt1 == lt2);
    }
}
