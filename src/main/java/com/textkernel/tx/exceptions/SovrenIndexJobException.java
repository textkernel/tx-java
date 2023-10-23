// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.exceptions;

import com.textkernel.tx.models.api.ApiResponseInfoLite;
import com.textkernel.tx.models.api.parsing.ParseJobResponse;
import okhttp3.Response;

/**
 * This exception is thrown when an error happens during indexing, but the service was still able to produce a usable Job object (see the {@link Response} property)
 */
public class SovrenIndexJobException extends SovrenUsableJobException {
    public SovrenIndexJobException(Response response, ApiResponseInfoLite errorInfo, String transactionId, ParseJobResponse parseResponse) {
        super(response, errorInfo, transactionId, parseResponse);
    }
}
