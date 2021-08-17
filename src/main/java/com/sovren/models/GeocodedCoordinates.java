// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models;

/**
* Represents a lat/lon provided by a 3rd party service
*/
public class GeocodedCoordinates extends GeoCoordinates {

    /** The geocoding source, such as Google or Bing*/
    public String Source;
}
