// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

/**
* A national identity found on a resume
*/
public class NationalIdentity {

    /** The national identity number*/
    public String Number;

    /** The type of identity/number this is (for example, SSN)*/
    public String Phrase;
}
