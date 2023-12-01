// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume;

/**
* A salary found in a resume
*/
public class Salary {

    /** The three-letter currency, eg: USD*/
    public String Currency;

    /** The amount of the salary (usually yearly when listed on a resume)*/
    public double Amount;
}
