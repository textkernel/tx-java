// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models;

/**
* Represents a lat/lon provided by a 3rd party service
*/
public class GeocodedCoordinates extends GeoCoordinates {

    /** The geocoding source, such as Google or Bing*/
    public String Source;
}
