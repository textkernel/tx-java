package com.sovren.models.api.geocoding;

import com.sovren.models.resume.ParsedResume;

/** {@inheritDoc} */
public class GeocodeAndIndexResumeRequest extends GeocodeAndIndexRequest {
    /**  The resume you wish to be geocoded/indexed*/
    public ParsedResume ResumeData;
}
