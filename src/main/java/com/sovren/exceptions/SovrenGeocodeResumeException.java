package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseResumeResponse;
import okhttp3.Response;

/** {@inheritDoc}*/
public class SovrenGeocodeResumeException extends SovrenUsableResumeException {
    public SovrenGeocodeResumeException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseResumeResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
