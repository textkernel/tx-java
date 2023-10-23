// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api;

/** The response body from a Sovren API call*/
public interface ISovrenResponse {

    /** @return Information about the response and the customer*/
    ApiResponseInfo getInfo();
}
