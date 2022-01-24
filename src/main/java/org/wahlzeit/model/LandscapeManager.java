package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LandscapeManager {

    // LandscapeManager is a Singleton.
    protected static final LandscapeManager instance = new LandscapeManager();
    // Use getInstance, because of Singleton pattern
    private LandscapeManager() {}

    public static final synchronized LandscapeManager getInstance() {
        return instance;
    }

    protected Set<Landscape> landscapes = new HashSet<Landscape>();
    protected Map<String, LandscapeType> landscapeTypes = new HashMap<String, LandscapeType>();

    // Create object Landscape with given name for LandscapeType
    public Landscape createLandscape(String typeName, Location location) { // 1. Creation of Landscape starts
        LandscapeType lt = getLandscapeType(typeName);
        Landscape result = lt.createInstance(location); // Creation of Landscape is delegated to an Object of LandscapeType
        landscapes.add(result);
        return result;
    }

    // Check if LandscapeType with given name already exists
    public LandscapeType getLandscapeType(String typeName) { // 1. Creation of LandscapeType starts
        if(landscapeTypes.containsKey(typeName)) {
            return landscapeTypes.get(typeName);
        }
        else {
            LandscapeType landscapeType = new LandscapeType(typeName); // Constructor is called if object does not yet exists
            landscapeTypes.put(typeName, landscapeType);
            return landscapeType;
        }
    }
}
