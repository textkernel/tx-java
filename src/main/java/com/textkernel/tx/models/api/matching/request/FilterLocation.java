// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

import com.textkernel.tx.models.GeoCoordinates;

/**
* A location used for filtering results by exact location or radius searching
*/
public class FilterLocation {
    
    /** The 2-letter ISO 3166 country code*/
    public String CountryCode;

    /** The Postal or Zip code*/
    public String PostalCode;

    /** The Region/District/State*/
    public String Region;

    /** The City/Municipality/Town*/
    public String Municipality;

    /** The geocordinates to be used in the location*/
    public GeoCoordinates GeoPoint;
}
