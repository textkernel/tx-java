// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.contactinfo;

import com.textkernel.tx.models.resume.NormalizedString;

/**
* A phone number listed on the resume
*/
public class Telephone extends NormalizedString {

    /** The international country code part of the phone number*/
    public String InternationalCountryCode;

    /** The area code part of the phone number*/
    public String AreaCityCode;

    /** The subscriber number part of the phone number*/
    public String SubscriberNumber;
}
