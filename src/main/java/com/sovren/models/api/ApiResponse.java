package com.sovren.models.api;

public class ApiResponse<T> implements ISovrenResponse {

    /** Contains information about the response and the customer*/
    public ApiResponseInfo Info;

    /** The data returned based on the request type/content*/
    public T Value;

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiResponseInfo getInfo() {
        return Info;
    }

    
}
