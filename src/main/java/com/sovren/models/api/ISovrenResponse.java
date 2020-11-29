package com.sovren.models.api;

/** The response body from a Sovren API call*/
public interface ISovrenResponse {

    /** Contains information about the response and the customer*/
    public ApiResponseInfo getInfo();
}
