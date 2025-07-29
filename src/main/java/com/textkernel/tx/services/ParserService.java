// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.exceptions.TxGeocodeJobException;
import com.textkernel.tx.exceptions.TxGeocodeResumeException;
import com.textkernel.tx.exceptions.TxIndexJobException;
import com.textkernel.tx.exceptions.TxIndexResumeException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.parsing.ParseJobResponse;
import com.textkernel.tx.models.api.parsing.ParseRequest;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#parser()} */
public class ParserService extends ServiceBase {

    /** See {@link TxClient#parser()} */
    public ParserService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    /**
     * Parse a resume
     * @param request The request body
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ParseResumeResponse parseResume(ParseRequest request) throws TxException {
        
        setEnvironment(request.IndexingOptions);
        
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.parseResume())
            .post(body)
            .build();

        HttpResponse<ParseResumeResponse> response = executeRequest(apiRequest, ParseResumeResponse.class, getBodyIfDebug(apiRequest));

        if (response.getData().Value.ParsingResponse != null && !response.getData().Value.ParsingResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), response.getData().Value.ParsingResponse, response.getData().Info.TransactionId);
        }

        if (response.getData().Value.GeocodeResponse != null && !response.getData().Value.GeocodeResponse.isSuccess()) {
            throw new TxGeocodeResumeException(response.getResponse(), response.getData().Value.GeocodeResponse, response.getData().Info.TransactionId, response.getData());
        }

        if (response.getData().Value.IndexingResponse != null && !response.getData().Value.IndexingResponse.isSuccess()) {
            throw new TxIndexResumeException(response.getResponse(), response.getData().Value.IndexingResponse, response.getData().Info.TransactionId, response.getData());
        }
        
        return response.getData();
    }
    
    /**
     * Parse a job
     * @param request The request body
     * @return The API response body
     * @throws TxException Thrown when an API error occurs
     */
    public ParseJobResponse parseJob(ParseRequest request) throws TxException {
        setEnvironment(request.IndexingOptions);
        
        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
            .url(_endpoints.parseJob())
            .post(body)
            .build();

        HttpResponse<ParseJobResponse> response = executeRequest(apiRequest, ParseJobResponse.class, getBodyIfDebug(apiRequest));

        if (response.getData().Value.ParsingResponse != null && !response.getData().Value.ParsingResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), response.getData().Value.ParsingResponse, response.getData().Info.TransactionId);
        }

        if (response.getData().Value.GeocodeResponse != null && !response.getData().Value.GeocodeResponse.isSuccess()) {
            throw new TxGeocodeJobException(response.getResponse(), response.getData().Value.GeocodeResponse, response.getData().Info.TransactionId, response.getData());
        }

        if (response.getData().Value.IndexingResponse != null && !response.getData().Value.IndexingResponse.isSuccess()) {
            throw new TxIndexJobException(response.getResponse(), response.getData().Value.IndexingResponse, response.getData().Info.TransactionId, response.getData());
        }
        
        return response.getData();
    }
}
