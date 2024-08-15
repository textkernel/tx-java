// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.job;

import com.textkernel.tx.models.TxPrimitive;

/**
* An object containing details about a job position's pay.
*/
public class PayRange {
    
    /** The normalized minimum yearly salary*/
    public TxPrimitive<Integer> Minimum;

    /** The normalized maximum yearly salary*/
    public TxPrimitive<Integer> Maximum;

    /** The raw, un-normalized, minimum value. This is returned as is in the text, so there is no guarantee that it will evaluate to a valid number and not a string.*/
    public String RawMinimum;

    /** The raw, un-normalized, maximum value. This is returned as is in the text, so there is no guarantee that it will evaluate to a valid number and not a string.*/
    public String RawMaximum;

    /** Currency code (ISO 4217) applied to the {@link #Minimum} and {@link #Maximum}*/
    public String Currency;

    /**
     * Time scale applied to the raw values to get the minimum and maximum annual salary. Possible values are:
     * <ul>
     * <li>Hourly</li>
     * <li>Daily</li>
     * <li>Weekly</li>
     * <li>Monthly</li>
     * <li>Annually</li>
     * </ul>
     * If no lexical cues are available from the vacancy, the time scale is guessed based on predefined salary ranges.
     * Here are some rough salary ranges (note that country-specific conditions may apply):
     * <ul>
     * <li>1 or 2 digits salary (9, 12): Hourly</li>
     * <li>3 or 4 digits salary (3800, 5000): Monthly</li>
     * <li>5 digit salary (38000, 50000): Annually</li>
     * </ul>
     * If a monthly salary is extracted, to get the annual salary it is multiplied by 14 (if country = AT) or 12 (all other countries).
    */
    public String TimeScale;
}
