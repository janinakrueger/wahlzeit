package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;

// Object creation table
// Delegation: separate-object
// Selection: on-the-spot
// Configuration: in-code
// Instantiation: in-code
// Initialization: default
// Building: default

public class LandscapeType {
    protected LandscapeType superType = null;
    protected Set<LandscapeType> subTypes = new HashSet<LandscapeType>();
    String typeName;

    // Give LandscapeType a String name
    public LandscapeType(String name) { // 2. Constructor is called by LandscapeManager
        if(name == null) {
            throw new IllegalArgumentException("Null argument was given.");
        }
        typeName = name;
    }

    // Create object Landscape with LandscapeType this
    public Landscape createInstance(Location location) { // 2. LandscapeType uses the contructor of Landscape to create an object
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
        if(lt == null) {
            throw new IllegalArgumentException("Asked about null object.");
        }
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
