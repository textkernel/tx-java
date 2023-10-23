// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

/**
* Options for geocoding a document (specifying some location on Earth)
*/
public class GeocodeOptions extends GeocodeOptionsBase {
    
    /** {@code true} to geocode, otherwise {@code false}*/
    public boolean IncludeGeocoding;
}
