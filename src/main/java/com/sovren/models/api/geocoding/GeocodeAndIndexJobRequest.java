package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/** Request body for geocoding a job and then adding into an index */
public class GeocodeAndIndexJobRequest extends GeocodeAndIndexRequest {
    /**  The job you wish to be geocoded/indexed */
    public ParsedJob JobData;
}
