package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/** {@inheritDoc} */
public class GeocodeAndIndexJobResponseValue extends GeocodeAndIndexResponseValue {
    
    /** The job you sent to be geocoded/indexed with geolocation coordinates added */
    public ParsedJob JobData;
}
