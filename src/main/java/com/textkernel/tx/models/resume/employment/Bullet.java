// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.resume.employment;

/**
 * A single entry in a bullet-point list in a position/job description on a resume
 */
public class Bullet {
    /**
     * The type of text/term found for this bullet point. One of:
     * <ul>
     * <li>creativeTerm - {@link #Text} indicates that an action was taken by the candidate</li>
     * <li>sentence: the default, no special terms were found in {@link #Text}</li>
     * </ul>
     */
    public String Type;
    
    /** The text value of the bullet-list item (excluding the bullet point character)*/
    public String Text;
}
