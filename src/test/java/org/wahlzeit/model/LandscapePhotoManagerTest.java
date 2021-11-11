package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LandscapePhotoManagerTest {

    @Test
    public void testInstanceType() {
        PhotoManager instance = LandscapePhotoManager.getInstance();
        assertEquals(PhotoManager.class, instance.getClass());
    }
}
