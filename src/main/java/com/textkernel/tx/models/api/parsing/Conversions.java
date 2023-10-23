// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

/**
* Conversions output by the document converter during a parse transaction
*/
public class Conversions {
    
    /**
     * If requested by {@link ParseOptions#OutputPdf}, this is the document converted to a PDF.
     * This is a {@code byte[]} as a Base64-encoded string. You can use {@link java.util.Base64#getDecoder()} and 
     * then {@link java.util.Base64.Decoder#decode(String)} to turn this back into a {@code byte[]}
    */
    public String PDF;
    
    /** If requested by {@link ParseOptions#OutputHtml}, this is the document converted to HTML.*/
    public String HTML;
    
    /** If requested by {@link ParseOptions#OutputRtf}, this is the document converted to RTF.*/
    public String RTF;
    
    /**
     * If a candidate photo was extracted, it will be output here. This is a {@code byte[]} as a Base64-encoded string.
     * You can use {@link java.util.Base64#getDecoder()} and then {@link java.util.Base64.Decoder#decode(String)} to turn this back into a {@code byte[]}
    */
    public String CandidateImage;
    
    /** If a candidate photo was extracted, the appropriate file extension for the photo will be output for this field (e.g. ".png").*/
    public String CandidateImageExtension;
}
