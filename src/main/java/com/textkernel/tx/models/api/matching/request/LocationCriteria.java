// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

import java.util.List;
import com.textkernel.tx.models.api.geocoding.GeocodeProvider;

/**
* Criteria for distance/range filtering
*/
public class LocationCriteria {
    
    /** Results must be found within a certain distance of one of these specified locations.*/
    public List<FilterLocation> Locations;
    
    /** The distance from {@link #Locations} within which to find results.*/
    public Integer Distance;
    
    /** The units for the specified distance. */
    public DistanceUnit DistanceUnit;
    
    /**
     * The provider to lookup latitude/longitude if they are not specified. If you use {@link GeocodeProvider#Bing}
     * you must specify your {@link #GeocodeProviderKey}
    */
    public GeocodeProvider GeocodeProvider;
    
    /**
     * Your private API key for the geocoding provider. If using {@link GeocodeProvider#Bing} you must specify your own API key.
     * <p>If using {@link GeocodeProvider#Google}, you can optionally provide your own API key
    */
    public String GeocodeProviderKey;
}
