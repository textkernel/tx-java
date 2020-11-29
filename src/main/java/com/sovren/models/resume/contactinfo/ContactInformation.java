package com.sovren.models.resume.contactinfo;

import java.util.List;
import com.sovren.models.Location;
import com.sovren.models.resume.NormalizedString;

/**
* A candidate's contact information listed on a resume
*/
public class ContactInformation {

    /** The candidate's name*/
    public PersonName CandidateName;

    /** The candidate's phone numbers. If multiple numbers are found, mobile phone numbers will be listed first*/
    public List<NormalizedString> Telephones;

    /** The candidate's email addresses*/
    public List<String> EmailAddresses;

    /** The candidate's location/address*/
    public Location Location;

    /** The candidate's web addresses (URLs, social media) listed on the resume*/
    public List<WebAddress> WebAddresses;
}
