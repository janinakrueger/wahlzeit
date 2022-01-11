package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;

public class LandscapeType {
    protected LandscapeType superType = null;
    protected Set<LandscapeType> subTypes = new HashSet<LandscapeType>();
    String typeName;

    // Give LandscapeType a String name
    public LandscapeType(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Null argument was given.");
        }
        typeName = name;
    }

    // Create object Landscape with LandscapeType this
    public Landscape createInstance(Location location) {
        return new Landscape(this, location);
    }

    // Methods to build and query type hierarchies:
    public LandscapeType getSuperType() {
        return superType;
    }

    public void setSuperType(LandscapeType lt) {
        superType = lt;
    }

    public String getType() {
        return typeName;
    }

    public Set<LandscapeType> getSubTypes() {
        return subTypes;
    }

    public void addSubType(LandscapeType lt) {
        if(lt == null) {
            throw new IllegalArgumentException("Tried to set null sub-type.");
        }
        lt.setSuperType(this);
        subTypes.add(lt);
    }

    public boolean isSubtype(LandscapeType lt) {
        assert (lt != null) : "Asked about null object.";
        if(lt.getType().equals(this.getType())) {
            return true;
        }

        Set<LandscapeType> subTypesLt = lt.getSubTypes();
        for (LandscapeType type: subTypesLt) {
            if (isSubtype(type)) {
                return true;
            }
        }
        return false;
    }
}
