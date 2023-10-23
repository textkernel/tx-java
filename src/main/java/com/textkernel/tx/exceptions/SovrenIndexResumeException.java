// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.exceptions;

import com.textkernel.tx.models.api.ApiResponseInfoLite;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens during indexing, but the service was still able to produce a usable Resume object (see the {@link Response} property)
 */
public class SovrenIndexResumeException extends SovrenUsableResumeException {
    public SovrenIndexResumeException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseResumeResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
