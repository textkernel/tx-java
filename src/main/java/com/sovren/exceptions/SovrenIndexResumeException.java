package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseResumeResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens during indexing, but the service was still able to produce a usable Resume object (see the {@link Response} property)
 */
public class SovrenIndexResumeException extends SovrenUsableResumeException {
    public SovrenIndexResumeException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseResumeResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
