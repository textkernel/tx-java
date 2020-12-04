// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.exceptions;

import okhttp3.Response;
import com.sovren.SovrenClient;
import com.sovren.models.api.ApiResponseInfo;
import com.sovren.models.api.ApiResponseInfoLite;
import java.util.Optional;

/**
 * The most generic exception thrown by the SDK as a result of an error response from the API
 */
public class SovrenException extends Exception {
    
    /** The raw response from the API */
    public Response RestResponse;
    
    /** The HTTP Status Code of the response. See https://docs.sovren.com/api/rest/#http-status-codes*/
    public int HttpStatusCode;
    
    /**  The Info.Code of the response. This will indicate what type of error occurred. See https://docs.sovren.com/api/rest/#http-status-codes*/
    public String SovrenErrorCode;
    
    /** The Id of the transaction, use this when reporting errors to Sovren Support*/
    public String TransactionId;
    
    /** The JSON request body, will only have a value if {@link SovrenClient#ShowFullRequestBodyInExceptions} is {@code true}*/
    public String RequestBody;
    
    /** If this exception was caused by another exception, the root cause will be here.*/
    public Exception InnerException;
    
    public SovrenException(String requestBody, Response response, ApiResponseInfoLite errorInfo, String transactionId) {
        super(Optional.ofNullable(errorInfo).map(e -> e.Message).orElse("Invalid response object from API"));
        
        RestResponse = response;
        HttpStatusCode = Optional.ofNullable(response).map(r -> r.code()).orElse(500);
        SovrenErrorCode = Optional.ofNullable(errorInfo).map(e -> e.Code).orElse("Unknown Error");
        TransactionId = transactionId;
        RequestBody = requestBody;
    }
    
    public SovrenException(String requestBody, Response response, ApiResponseInfo errorInfo) {
        this(requestBody, response, errorInfo, errorInfo.TransactionId);
    }
}
