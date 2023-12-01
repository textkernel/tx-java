// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.contactinfo;

/** A type of {@link WebAddress}. These are useful instead of magic strings.*/
public class WebAddressType {

    /** An unknown internet handle/URL (the platform/website/app was not specified)*/
    public static WebAddressType Unknown = new WebAddressType("Unknown");
    
    /** A personal website URL*/
    public static WebAddressType PersonalWebsite = new WebAddressType("PersonalWebsite");

    /** A LinkedIn URL*/
    public static WebAddressType LinkedIn = new WebAddressType("LinkedIn");

    /** A Twitter handle*/
    public static WebAddressType Twitter = new WebAddressType("Twitter");

    /** A Facebook profile URL*/
    public static WebAddressType Facebook = new WebAddressType("Facebook");

    /** An Instagram username*/
    public static WebAddressType Instagram = new WebAddressType("Instagram");

    /** An ICQ username*/
    public static WebAddressType ICQ = new WebAddressType("ICQ");
    
    /** A Quora username */
    public static WebAddressType Quora = new WebAddressType("Quora");

    /** A Skype username/URL */
    public static WebAddressType Skype = new WebAddressType("Skype");

    /** A WeChat username */
    public static WebAddressType WeChat = new WebAddressType("WeChat");

    /** A QQ username/number */
    public static WebAddressType QQ = new WebAddressType("QQ");

    /** A Telegraph username */
    public static WebAddressType Telegraph = new WebAddressType("Telegraph");

    /** A WhatsApp username/number */
    public static WebAddressType WhatsApp = new WebAddressType("WhatsApp");

    /** A Telegram username */
    public static WebAddressType Telegram = new WebAddressType("Telegram");

    /** A MeWe username/URL */
    public static WebAddressType MeWe = new WebAddressType("MeWe");

    /** A Parler username */
    public static WebAddressType Parler = new WebAddressType("Parler");

    /** A Gab username */
    public static WebAddressType Gab = new WebAddressType("Gab");

    /** A Reddit username/URL */
    public static WebAddressType Reddit = new WebAddressType("Reddit");

    /** A GitHub username/URL */
    public static WebAddressType GitHub = new WebAddressType("GitHub");

    /** A Signal username/number */
    public static WebAddressType Signal = new WebAddressType("Signal");

    /** A Stack Overflow username/URL */
    public static WebAddressType StackOverflow = new WebAddressType("StackOverflow");

    /** The raw string value */
    public String Value;

    private WebAddressType(String value) {
        Value = value;
    }
}
