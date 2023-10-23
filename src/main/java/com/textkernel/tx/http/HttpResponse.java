// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.http;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.utilities.SovrenJsonSerializer;
import java.io.IOException;
import okhttp3.Response;

/**
 * A simple class to contain a raw OkHttp Response and a deserialized response body
 */
public class HttpResponse<T extends ApiResponse<?>> {
    private Response _response;
    private T _data = null;
    
    public HttpResponse(Response response, Class<T> type) throws IOException {
        _response = response;

        try {
            _data = SovrenJsonSerializer.deserialize(response.body().string(), type);
        }
        catch (Exception e) {
            if (_response != null && _response.code() == 200) {
                throw e;//rethrow, since this is a json deserialization issue
            }
            //otherwise, eat the exception since a non-200 response will not have the expected response body
        }
    }
    
    public Response getResponse() {return  _response; }
    public T getData() { return _data; }
}
