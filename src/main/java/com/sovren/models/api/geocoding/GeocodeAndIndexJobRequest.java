package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/** {@inheritDoc} */
public class GeocodeAndIndexJobRequest extends GeocodeAndIndexRequest {
    /**  The job you wish to be geocoded/indexed */
    public ParsedJob JobData;
}
