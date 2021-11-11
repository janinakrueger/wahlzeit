package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LandscapePhotoFactoryTest {
    
    @Test
    public void testCreateLandscapePhoto() {
        PhotoFactory landscapeFactory = LandscapePhotoFactory.getInstance();
        Photo photo = landscapeFactory.createPhoto();
        assertEquals(LandscapePhoto.class, photo.getClass());
    }

    @Test
    public void testInitialize() {
        LandscapePhotoFactory.initialize();
        PhotoFactory landscapeFactory1 = LandscapePhotoFactory.getInstance();
        LandscapePhotoFactory.initialize();
        PhotoFactory landscapeFactory2 = LandscapePhotoFactory.getInstance();
        assertEquals(landscapeFactory1, landscapeFactory2);
    }
}
