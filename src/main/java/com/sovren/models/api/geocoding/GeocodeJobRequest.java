package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/**
* Options for geocoding a job (specifying some location on Earth)
*/
public class GeocodeJobRequest extends GeocodeOptionsBase {
    /**  The job you wish to be geocoded */
    public ParsedJob JobData;
}
