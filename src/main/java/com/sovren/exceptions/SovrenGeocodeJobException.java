package com.sovren.exceptions;

import com.sovren.models.api.ApiResponseInfoLite;
import com.sovren.models.api.parsing.ParseJobResponse;
import okhttp3.Response;

/** {@inheritDoc}*/
public class SovrenGeocodeJobException extends SovrenUsableJobException {
    public SovrenGeocodeJobException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseJobResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
