package com.sovren.models.api.geocoding;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.api.ApiResponseInfoLite;

/** The {@link ApiResponse#Value} from a 'geocode and index' response */
public class GeocodeAndIndexResponseValue {
    
    /** If geocoding was requested, the status of the geocode transaction will be output here */
    public ApiResponseInfoLite GeocodeResponse;

    /** If indexing was requested, the status of the index transaction will be output here */
    public ApiResponseInfoLite IndexingResponse;
}
