// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.exceptions;

import okhttp3.Response;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.models.api.ApiResponseInfo;
import com.textkernel.tx.models.api.ApiResponseInfoLite;
import java.util.Optional;

/**
 * The most generic exception thrown by the SDK as a result of an error response from the API
 */
public class TxException extends Exception {
    
    /** The raw response from the API */
    public Response RestResponse;
    
    /** The HTTP Status Code of the response. See https://developer.textkernel.com/Sovren/v10/overview/#http-status-codes*/
    public int HttpStatusCode;
    
    /**  The Info.Code of the response. This will indicate what type of error occurred. See https://developer.textkernel.com/Sovren/v10/overview/#http-status-codes*/
    public String TxErrorCode;
    
    /** The Id of the transaction, use this when reporting errors to Support*/
    public String TransactionId;
    
    /** The JSON request body, will only have a value if {@link TxClient#ShowFullRequestBodyInExceptions} is {@code true}*/
    public String RequestBody;
    
    /** If this exception was caused by another exception, the root cause will be here.*/
    public Exception InnerException;
    
    public TxException(String requestBody, Response response, ApiResponseInfoLite errorInfo, String transactionId) {
        super(Optional.ofNullable(errorInfo).map(e -> e.Message).orElse("Invalid response object from API"));
        
        RestResponse = response;
        HttpStatusCode = Optional.ofNullable(response).map(r -> r.code()).orElse(500);
        TxErrorCode = Optional.ofNullable(errorInfo).map(e -> e.Code).orElse("Unknown Error");
        TransactionId = transactionId;
        RequestBody = requestBody;
    }
    
    public TxException(String requestBody, Response response, ApiResponseInfo errorInfo) {
        this(requestBody, response, errorInfo, errorInfo.TransactionId);
    }
}
