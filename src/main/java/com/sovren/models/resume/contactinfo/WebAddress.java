package com.sovren.models.resume.contactinfo;

/**
* A web address (URL, twitter handle, etc)
*/
public class WebAddress {

    /** The URL or username*/
    public String Address;

    /**
     * The type of address. One of:
     * <p>- {@link WebAddressType#PersonalWebsite}</p>
     * <p>- {@link WebAddressType#LinkedIn}</p>
     * <p>- {@link WebAddressType#TwitterHandle}</p>
     * <p>- {@link WebAddressType#Facebook}</p>
     * <p>- {@link WebAddressType#Instagram}</p>
     * <p>- {@link WebAddressType#ICQ}</p>
     * */
    public String Type;
}
