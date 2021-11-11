package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LandscapePhotoTest {
    
    @Test
    public void testContructor() {
        Photo photo = new LandscapePhoto();
        assertEquals(LandscapePhoto.class, photo.getClass());
    }
}
