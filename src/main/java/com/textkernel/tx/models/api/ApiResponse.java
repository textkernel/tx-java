// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api;

/** The response body from a Sovren API call*/
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
