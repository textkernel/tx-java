package com.sovren.models.api.geocoding;

import com.sovren.models.GeoCoordinates;

/**
* Options for geocoding a document (specifying some location on Earth)
*/
public class GeocodeOptionsBase extends GeocodeCredentials {
    
    /**
     * The address you wish to geocode. This field is optional. 
     * <p><b>If you specify this value,
     * this address will be used to get the geocode coordinates instead of the address included
     * in the parsed document (if present); however, the address in the parsed document will not be modified.</b></p>
    */
    public Address PostalAddress;
    
    /**
     * The geographic coordinates (latitude/longitude) for your postal address. 
     * <p><b>Use this if you already 
     * have latitude/longitude coordinates and simply wish to add them to your parsed document. If provided, 
     * these values will be inserted into your parsed document and the address included in the 
     * parsed document (if present), will not be modified.</b></p>
    */
    public GeoCoordinates GeoCoordinates;
}
