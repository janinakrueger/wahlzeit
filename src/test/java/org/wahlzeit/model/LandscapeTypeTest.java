package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LandscapeTypeTest {

    // Test if supertype is set correctly
    @Test
    public void testAddSubtype() {
        LandscapeType l1 = new LandscapeType("testType");
        LandscapeType l2 = new LandscapeType("testSubType");
        l1.addSubType(l2);
        assertEquals("testType",l2.superType.getType());
    }

    // Test if subtypes are set correctly
    @Test
    public void testIsSubtype() {
        LandscapeManager manager = LandscapeManager.getInstance();
        LandscapeType l1 = manager.getLandscapeType("testType");
        LandscapeType l2 = manager.getLandscapeType("testSubType");
        LandscapeType l3 = manager.getLandscapeType("testNonSubType");
        l1.addSubType(l2);
        assertEquals(true,l2.isSubtype(l1));
        assertEquals(false,l3.isSubtype(l1));
    }
}
