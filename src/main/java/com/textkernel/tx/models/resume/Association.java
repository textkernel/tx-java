// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.resume;

/**
* An association/organization found on a resume
*/
public class Association {

    /** The name of the association/organization*/
    public String Organization;

    /** The role the candidate held*/
    public String Role;

    /** The full text in which this association was found*/
    public String FoundInContext;
}
