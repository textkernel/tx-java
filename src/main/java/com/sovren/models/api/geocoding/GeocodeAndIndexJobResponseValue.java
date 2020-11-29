package com.sovren.models.api.geocoding;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.job.ParsedJob;

/** The {@link ApiResponse#Value} from a 'geocode and index' response */
public class GeocodeAndIndexJobResponseValue extends GeocodeAndIndexResponseValue {
    
    /** The job you sent to be geocoded/indexed with geolocation coordinates added */
    public ParsedJob JobData;
}
