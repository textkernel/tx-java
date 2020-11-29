package com.sovren.models.api.geocoding;

import com.sovren.models.resume.ParsedResume;

/** Request body for geocoding a resume and then adding into an index */
public class GeocodeAndIndexResumeRequest extends GeocodeAndIndexRequest {
    /**  The resume you wish to be geocoded/indexed*/
    public ParsedResume ResumeData;
}
