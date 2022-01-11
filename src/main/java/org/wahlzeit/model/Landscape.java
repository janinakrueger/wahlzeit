package org.wahlzeit.model;

public class Landscape {
    protected LandscapeType landscapeType = null;
    private Location location;

    public Landscape(LandscapeType lt, Location l) {
        if(lt == null) {
            throw new IllegalArgumentException("Null argument was given for type.");
        }
        if(l == null) {
            throw new IllegalArgumentException("Null argument was given for Location.");
        }
        landscapeType = lt;
        location = l;
    }

    public LandscapeType getLandscapeType() {
        return landscapeType;
    }

    public Location getLocation() {
        return location;
    }

    public String getType() {
        return landscapeType.getType();
    }
}
