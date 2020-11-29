package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseJobResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens during indexing, but the service was still able to produce a usable Job object (see the {@link Response} property)
 */
public class SovrenIndexJobException extends SovrenUsableJobException {
    public SovrenIndexJobException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseJobResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
