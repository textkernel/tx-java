// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
     * <p>- {@link WebAddressType#Twitter}
     * <p>- {@link WebAddressType#GitHub}
     * <p>- {@link WebAddressType#Facebook}
     * <p>- {@link WebAddressType#Skype}
     * <p>- {@link WebAddressType#WhatsApp}
     * <p>- {@link WebAddressType#StackOverflow}
     * <p>- {@link WebAddressType#Instagram}
     * <p>- {@link WebAddressType#Reddit}
     * <p>- {@link WebAddressType#Signal}
     * <p>- {@link WebAddressType#Quora}
     * <p>- {@link WebAddressType#ICQ}
     * <p>- {@link WebAddressType#WeChat}
     * <p>- {@link WebAddressType#QQ}
     * <p>- {@link WebAddressType#Telegraph}
     * <p>- {@link WebAddressType#Telegram}
     * <p>- {@link WebAddressType#MeWe}
     * <p>- {@link WebAddressType#Parler}
     * <p>- {@link WebAddressType#Gab}
     * <p>- {@link WebAddressType#Unknown}
     * */
    public String Type;
}
