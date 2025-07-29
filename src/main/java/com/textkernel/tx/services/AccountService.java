// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.account.GetAccountInfoResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/** See {@link TxClient#account()} */
public class AccountService extends ServiceBase {

    /**
     * Do not use this. See {@link TxClient#account()}
     * @param httpClient The http client for API calls
     * @param settings environment settings
     */
    public AccountService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Get the account info (remaining credits, max concurrency, etc).
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public GetAccountInfoResponse getAccountInfo() throws TxException {
        Request apiRequest = new Request.Builder()
            .url(_endpoints.account())
            .build();

        HttpResponse<GetAccountInfoResponse> response = executeRequest(apiRequest, GetAccountInfoResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }
}
