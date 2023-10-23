// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

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
