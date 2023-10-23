// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.api.ApiResponseInfo;

/**
* Information about a document conversion
*/
public class ConversionMetadata {
    
    /** The file type that was detected*/
    public String DetectedType;
    
    /** The suggested extension based on the {@link #DetectedType}*/
    public String SuggestedFileExtension;
    
    /** 
     * The computed validity based on the source text. This will indicate whether a document 
     * looks like a legitimate resume/job or not. See https://sovren.com/technical-specs/latest/rest-api/resume-parser/overview/document-conversion-code/
    */
    public String OutputValidityCode;
    
    /**
     * How long the document conversion took, in milliseconds.
     * This is a subset of {@link ApiResponseInfo#TotalElapsedMilliseconds}
    */
    public int ElapsedMilliseconds;

    /** The MD5 hash of the document bytes*/
    public String DocumentHash;
}
