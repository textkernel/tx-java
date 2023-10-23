// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

/**
* Credentials for geocoding
*/
public class GeocodeCredentials {
    
    /** 
     * The provider you wish to use to geocode the postal address. If you use {@link GeocodeProvider#Bing}
     * you must specify your {@link #ProviderKey}
    */
    public GeocodeProvider Provider;
    
    /**
     * Your private API key for the geocoding provider. If using {@link GeocodeProvider#Bing} you must specify your own API key.
     * If using {@link GeocodeProvider#Google}, you can optionally provide your own API key
    */
    public String ProviderKey;
}
