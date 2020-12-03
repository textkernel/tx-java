// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

/**
* An address used to geocode a document
*/
public class Address {
    
    /** The 2-letter ISO 3166 country code*/
    public String CountryCode;
    
    /** The Postal or Zip code*/
    public String PostalCode;
    
    /** The Region/District/State*/
    public String Region;
    
    /** The City/Municipality/Town*/
    public String Municipality;
    
    /** Street address*/
    public String AddressLine;
}
