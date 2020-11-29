package com.sovren.models;

import java.util.List;

/**
* Represents a physical location on Earth (mostly used for addresses)
*/
public class Location {

    /** The 2-letter ISO 3166 country code*/
    public String CountryCode;

    /** The Postal or Zip code*/
    public String PostalCode;

    /** The Regions/Districts/States*/
    public List<String> Regions;

    /** The City/Municipality/Town*/
    public String Municipality;

    /** Street address lines*/
    public List<String> StreetAddressLines;

    /** If geocoding has been done, this is the lat/lon for the location*/
    public GeoCoordinates GeoCoordinates;
}
