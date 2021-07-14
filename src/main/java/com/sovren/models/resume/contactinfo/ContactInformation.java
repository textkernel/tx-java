// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume.contactinfo;

import java.util.List;
import com.sovren.models.Location;

/**
* A candidate's contact information listed on a resume
*/
public class ContactInformation {

    /** The candidate's name*/
    public PersonName CandidateName;

    /** The candidate's phone numbers. If multiple numbers are found, mobile phone numbers will be listed first*/
    public List<Telephone> Telephones;

    /** The candidate's email addresses*/
    public List<String> EmailAddresses;

    /** 
     * The candidate's location/address. The Parser does not standardize addresses. Address standardization
     * services are available, including for example the Google Maps API, that can take the Parser's contact
     * info fields and standardize/geocode the data.
    */
    public Location Location;

    /** The candidate's web addresses (URLs, social media) listed on the resume*/
    public List<WebAddress> WebAddresses;
}
