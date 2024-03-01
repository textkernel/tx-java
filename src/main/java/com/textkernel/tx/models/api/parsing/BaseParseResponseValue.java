// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.api.ApiResponseInfoLite;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class BaseParseResponseValue {
    
    /** Information about converting the document to plain text*/
    public ConversionMetadata ConversionMetadata;
    
    /** Any additional conversions you requested will be here (eg: PDF or HTML)*/
    public Conversions Conversions;
    
    /** Information about the parsing transaction*/
    public ParsingMetadata ParsingMetadata;
    
    /** 
     * If geocoding was requested in the {@link ParseOptions#GeocodeOptions},
     * the status of the geocode transaction will be output here
    */
    public ApiResponseInfoLite GeocodeResponse;
    
    /** 
     * If indexing was requested in the {@link ParseOptions#IndexingOptions},
     * the status of the index transaction will be output here
    */
    public ApiResponseInfoLite IndexingResponse;
    
    /** The status of the parse transaction*/
    public ApiResponseInfoLite ParsingResponse;

    /** 
    * If profession normalization was requested in the {@link ParseOptions#ProfessionsSettings}, 
    * the status of the profession normalization transaction will be output here
    */
    public ApiResponseInfoLite ProfessionNormalizationResponse;
}
