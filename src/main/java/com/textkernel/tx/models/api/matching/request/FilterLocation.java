// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
