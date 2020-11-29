package com.sovren.models.api.geocoding;

import com.sovren.models.resume.ParsedResume;

/** {@inheritDoc} */
public class GeocodeAndIndexResumeResponseValue extends GeocodeAndIndexResponseValue {
    
    /** The resume you sent to be geocoded/indexed with geolocation coordinates added */
    public ParsedResume ResumeData;
}
