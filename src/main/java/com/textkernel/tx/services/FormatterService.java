// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.formatter.FormatResumeRequest;
import com.textkernel.tx.models.api.formatter.FormatResumeResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#formatter()} */
public class FormatterService extends ServiceBase {

    /** See {@link TxClient#formatter()} */
    public FormatterService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Format a resume into a standardized template that you provide
     * @param request The request body
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public FormatResumeResponse formatResume(FormatResumeRequest request) throws TxException {
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.formatResume())
            .post(body)
            .build();

        HttpResponse<FormatResumeResponse> response = executeRequest(apiRequest, FormatResumeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
}
