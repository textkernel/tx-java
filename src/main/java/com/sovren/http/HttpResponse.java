package com.sovren.http;

import com.sovren.models.api.ApiResponse;
import com.sovren.utilities.SovrenJsonSerializer;
import java.io.IOException;
import okhttp3.Response;

/**
 * A simple class to contain a raw OkHttp Response and a deserialized response body
 */
public class HttpResponse<T extends ApiResponse> {
    private final Response _response;
    private final T _data;
    
    public HttpResponse(Response response, Class<T> type) throws IOException {
        _response = response;
        
        _data = SovrenJsonSerializer.deserialize(response.body().string(), type);
    }
    
    public Response getResponse() {return  _response; }
    public T getData() { return _data; }
}
