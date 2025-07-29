// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import java.io.IOException;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.api.ApiResponseInfoLite;
import com.textkernel.tx.models.api.indexes.IndexingOptionsGeneric;
import com.textkernel.tx.models.api.indexes.SearchAndMatchVersion;
import com.textkernel.tx.utilities.TxJsonSerializer;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/** Base class for all Tx services */
abstract class ServiceBase {

    private OkHttpClient _httpClient;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    protected final EnvironmentSettings _settings;
    protected final ApiEndpoints _endpoints;

    protected ServiceBase(OkHttpClient httpClient, EnvironmentSettings settings) {
        _httpClient = httpClient;
        _settings = settings;
        _endpoints = new ApiEndpoints(settings.DataCenter);
    }

    protected void setEnvironment(IndexingOptionsGeneric options) {
        if (options != null && options.SearchAndMatchVersion == SearchAndMatchVersion.V2) {
            //ensure this is set correctly
            options.SearchAndMatchEnvironment = _settings.MatchV2Environment;
        }
    }

    static String getBodyIfDebug(Request request) {
        
        if (TxClient.ShowFullRequestBodyInExceptions) {
            try {
                final Request copy = request.newBuilder().build();
                final okio.Buffer buffer = new okio.Buffer();
                RequestBody body = copy.body();
                if (body != null) {
                    body.writeTo(buffer);
                    return buffer.readUtf8();
                }
                return null;
            }
            catch (IOException e) {
                return null;
            }
        }
        
        return null;
    }

    @SuppressWarnings("deprecation")
    protected RequestBody createJsonBody(Object body) {
        // Use OkHttp v3 signature to ensure binary compatibility between v3 and v4
        // https://github.com/textkernel/tx-java/issues/36
        return RequestBody.create(JSON, TxJsonSerializer.serialize(body));
    }

    protected <T extends ApiResponse<?>> HttpResponse<T> executeRequest(Request apiRequest, Class<T> classOfT, String requestBody) throws TxException {
        
        ApiResponseInfoLite errorInfo = new ApiResponseInfoLite();
        errorInfo.Code = "Error";
        errorInfo.Message = "Unknown API error.";
        
        HttpResponse<T> apiResponse = null;
        Response rawResponse = null;
        
        try {
            rawResponse = _httpClient.newCall(apiRequest).execute();
            apiResponse = new HttpResponse<T>(rawResponse, classOfT);

            if (rawResponse != null && rawResponse.code() == 413) {
                errorInfo.Message = "Request body was too large.";
                throw new TxException(requestBody, rawResponse, errorInfo, null);
            }
            
            if (rawResponse != null && apiResponse.getData() == null && rawResponse.code() != 200) {
                //something went wrong, a non-200 status code
                errorInfo.Message = rawResponse.code() + " - " + rawResponse.message();
            }

            if (apiResponse == null || apiResponse.getData() == null) throw new TxException(requestBody, rawResponse, errorInfo, null);
        }
        catch (IOException e) {
            errorInfo.Message = e.getMessage();
            TxException newEx = new TxException(requestBody, rawResponse, errorInfo, null);
            newEx.InnerException = e;
            throw newEx;
        }
       
        if (!rawResponse.isSuccessful()) throw new TxException(requestBody, rawResponse, apiResponse.getData().Info);
        
        return apiResponse;
    }
    
}
