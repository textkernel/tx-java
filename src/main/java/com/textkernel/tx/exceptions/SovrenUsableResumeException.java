// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.exceptions;

import com.textkernel.tx.models.api.ApiResponseInfoLite;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens, but the service was still able to produce a usable Resume object (see the {@link Response} property)
 */
public abstract class SovrenUsableResumeException extends SovrenException {
    
    /** This may or may not be {@code null} or incomplete depending on what specific error occurred*/
    public ParseResumeResponse Response;
    
    public SovrenUsableResumeException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseResumeResponse parseResponse) {
        super(null, response, errorInfo, transactionId);
        
        Response = parseResponse;
    }
}
