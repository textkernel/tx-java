package com.sovren.models.api.geocoding;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.job.ParsedJob;

/** The {@link ApiResponse#Value} from a 'geocode job' response */
public class GeocodeJobResponseValue {
    
    /** The job you sent to be geocoded with geolocation coordinates added */
    public ParsedJob JobData;
}
