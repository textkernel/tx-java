package com.sovren.models.api.parsing;

import com.sovren.models.api.ApiResponseInfo;

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
     * looks like a legitimate resume/job or not. See https://docs.sovren.com/#document-conversion-result-codes
    */
    public String OutputValidityCode;
    
    /**
     * How long the document conversion took, in milliseconds.
     * This is a subset of {@link ApiResponseInfo#TotalElapsedMilliseconds}
    */
    public int ElapsedMilliseconds;
}
