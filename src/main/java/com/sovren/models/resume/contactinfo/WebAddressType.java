package com.sovren.models.resume.contactinfo;

/** A type of {@link WebAddress}. These are useful instead of magic strings.*/
public class WebAddressType {

    /** A personal website URL*/
    public static WebAddressType PersonalWebsite = new WebAddressType("PersonalWebsite");

    /** A LinkedIn URL*/
    public static WebAddressType LinkedIn = new WebAddressType("LinkedIn");

    /** A candidate's Twitter handle*/
    public static WebAddressType TwitterHandle = new WebAddressType("Twitter");

    /** A candidate's Facebook profile URL*/
    public static WebAddressType Facebook = new WebAddressType("Facebook");

    /** A candidate's Instagram username*/
    public static WebAddressType Instagram = new WebAddressType("Instagram");

    /** A candidate's ICQ username*/
    public static WebAddressType ICQ = new WebAddressType("ICQ");

    /** The raw string value */
    public String Value;

    private WebAddressType(String value) {
        Value = value;
    }
}
