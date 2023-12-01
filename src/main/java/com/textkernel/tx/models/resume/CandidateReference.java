// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

import java.util.List;
import com.textkernel.tx.models.Location;
import com.textkernel.tx.models.resume.contactinfo.WebAddress;
import com.textkernel.tx.models.resume.contactinfo.PersonName;

/**
* A reference found on a resume
*/
public class CandidateReference {

    /** The name of the reference*/
    public PersonName ReferenceName;

    /** The job title of the reference*/
    public String Title;

    /** The employer of the reference*/
    public String Company;

    /** The type of reference*/
    public String Type;

    /** The physical location of the reference*/
    public Location Location;

    /** Phone numbers listed for the reference*/
    public List<NormalizedString> Telephones;

    /** Email addresses listed for the reference*/
    public List<String> EmailAddresses;

    /** Other web addresses listed for the reference*/
    public List<WebAddress> WebAddresses;
}
