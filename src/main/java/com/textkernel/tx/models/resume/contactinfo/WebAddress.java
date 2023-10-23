// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.contactinfo;

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
