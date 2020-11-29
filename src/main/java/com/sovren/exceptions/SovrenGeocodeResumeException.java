package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseResumeResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens during geocoding, but the service was still able to produce a usable Resume object (see the {@link Response} property)
 */
public class SovrenGeocodeResumeException extends SovrenUsableResumeException {
    public SovrenGeocodeResumeException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseResumeResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
