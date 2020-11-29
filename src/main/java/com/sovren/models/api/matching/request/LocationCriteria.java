package com.sovren.models.api.matching.request;

import java.util.List;
import java.util.Optional;
import com.sovren.models.Location;
import com.sovren.models.api.geocoding.GeocodeProvider;

/**
* Criteria for distance/range filtering
*/
public class LocationCriteria {
    
    /** Results must be found within a certain distance of one of these specified locations.*/
    public List<Location> Locations;
    
    /** The distance from {@link #Locations} within which to find results.*/
    public Optional<Integer> Distance;
    
    /** The units for the specified distance. */
    public DistanceUnit DistanceUnit;
    
    /**
     * The provider to lookup latitude/longitude if they are not specified. If you use {@link GeocodeProvider#Bing}
     * you must specify your {@link #GeocodeProviderKey}
    */
    public GeocodeProvider GeocodeProvider;
    
    /**
     * Your private API key for the geocoding provider. If using {@link GeocodeProvider#Bing} you must specify your own API key.
     * <p>If using {@link GeocodeProvider#Google}, you can optionally provide your own API key</p>
    */
    public String GeocodeProviderKey;
}
