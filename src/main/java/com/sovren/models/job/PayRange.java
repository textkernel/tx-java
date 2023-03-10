// Copyright © 2023 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.job;

import com.sovren.models.SovrenPrimitive;

/**
* An object containing details about a job position's pay.
*/
public class PayRange {
    
    /** The normalized minimum yearly salary*/
    public SovrenPrimitive<Integer> Minimum;

    /** The normalized maximum yearly salary*/
    public SovrenPrimitive<Integer> Maximum;

    /** Currency code (ISO 4217) applied to the {@link #Minimum} and {@link #Maximum}*/
    public String Currency;
}
