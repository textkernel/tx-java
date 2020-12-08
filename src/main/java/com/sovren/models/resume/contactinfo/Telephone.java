// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.contactinfo;

import com.sovren.models.resume.NormalizedString;

/**
* A phone number listed on the resume
*/
public class Telephone extends NormalizedString {

    /** If OutputFormat.TelcomNumber.Style = Structured configuration setting is set, the international country code part of the phone number*/
    public String InternationalCountryCode;

    /** If OutputFormat.TelcomNumber.Style = Structured configuration setting is set, the area code part of the phone number*/
    public String AreaCityCode;

    /** If OutputFormat.TelcomNumber.Style = Structured configuration setting is set, the subscriber part of the phone number*/
    public String SubscriberNumber;
}
