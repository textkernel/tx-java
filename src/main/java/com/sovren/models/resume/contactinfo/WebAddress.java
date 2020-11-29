package com.sovren.models.resume.contactinfo;

/**
* A web address (URL, twitter handle, etc)
*/
public class WebAddress {

    /** The URL or username*/
    public String Address;

    /**
     * The type of address. One of:
     * <p>- {@link WebAddressType#PersonalWebsite}
     * <p>- {@link WebAddressType#LinkedIn}
     * <p>- {@link WebAddressType#TwitterHandle}
     * <p>- {@link WebAddressType#Facebook}
     * <p>- {@link WebAddressType#Instagram}
     * <p>- {@link WebAddressType#ICQ}
     * */
    public String Type;
}
