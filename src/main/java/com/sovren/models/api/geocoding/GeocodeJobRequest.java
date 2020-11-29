package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/** {@inheritDoc} */
public class GeocodeJobRequest extends GeocodeOptionsBase {
    /**  The job you wish to be geocoded */
    public ParsedJob JobData;
}
