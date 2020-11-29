/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseJobResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens, but the service was still able to produce a usable Resume object (see the {@link Response} property)
 */
public abstract class SovrenUsableJobException extends SovrenException {
    
    /** This may or may not be {@code null} or incomplete depending on what specific error occurred*/
    public ParseJobResponse Response;
    
    public SovrenUsableJobException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseJobResponse parseResponse) {
        super(null, response, errorInfo, transactionId);
        
        Response = parseResponse;
    }
}
