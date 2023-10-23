// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.military;

/**
* A security credential/clearance found on a resume
*/
public class SecurityCredential {

    /** The name of the credential/clearance*/
    public String Name;

    /** The full context of where Sovren found this clearance/credential*/
    public String FoundInContext;
}
