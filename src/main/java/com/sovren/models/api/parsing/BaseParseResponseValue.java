// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.parsing;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class BaseParseResponseValue {
    
    /** Information about the parse request*/
    public ParseRequestDetails RequestDetails;
    
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
}
