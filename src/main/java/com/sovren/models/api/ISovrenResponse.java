package com.sovren.models.api;

/** The response body from a Sovren API call*/
public interface ISovrenResponse {

    /** @return Information about the response and the customer*/
    ApiResponseInfo getInfo();
}
