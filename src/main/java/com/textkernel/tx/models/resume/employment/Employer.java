// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.resume.employment;

import com.textkernel.tx.models.resume.NormalizedString;
import com.textkernel.tx.models.Location;

/**
* A name/location for a company/employer
*/
public class Employer {
    
    /** The name of the employer (and an accuracy probability)*/
    public CompanyNameWithProbability Name;
    
    /**
     * Sometimes a second possible company name is found, or a department/organization
     * within a company. This is that value, if it is found.
    */
    public NormalizedString NameVariation;
    
    /** The location/address of the employer*/
    public Location Location;
}
