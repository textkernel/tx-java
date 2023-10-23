// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui;

/** Options for styling the Matching UI*/
public class Style {
    /**
     * An HTML color used to generate several related colors for various UI elements. For example: {@code #077799}.
     */
    public String PrimaryColor;

    /**
     * An HTML color used for the background of the section/accordian headers. For example: {@code #077799}.
     */
    public String HeaderColor;
    
    /**
     * {@code true} to use square corners for UI elements. Default is {@code false} for rounded corners.
     */
    public boolean SquareCorners;
    
    /**
     * A CSS font-family to use for all UI elements. For example: {@code Arial}.
     */
    public String FontFamily;
    
    /**
     * If you'd like to use a non-standard font, specify the URL where that font can be downloaded here. For example:
     * {@code https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap}. Note that you also
     * need to define the {@link #FontFamily} if you use this option.
     */
    public String FontUrl;
}
