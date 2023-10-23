// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

import com.textkernel.tx.models.SovrenPrimitive;

/**
* An object containing details about a job position's pay.
*/
public class PayRange {
    
    /** The normalized minimum yearly salary*/
    public SovrenPrimitive<Integer> Minimum;

    /** The normalized maximum yearly salary*/
    public SovrenPrimitive<Integer> Maximum;

    /** The raw, un-normalized, minimum value. This is returned as is in the text, so there is no guarantee that it will evaluate to a valid number and not a string.*/
    public String RawMinimum;

    /** The raw, un-normalized, maximum value. This is returned as is in the text, so there is no guarantee that it will evaluate to a valid number and not a string.*/
    public String RawMaximum;

    /** Currency code (ISO 4217) applied to the {@link #Minimum} and {@link #Maximum}*/
    public String Currency;
}
