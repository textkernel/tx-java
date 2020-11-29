package com.sovren.models.api.geocoding;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.resume.ParsedResume;

/** The {@link ApiResponse#Value} from a 'geocode and index' response */
public class GeocodeAndIndexResumeResponseValue extends GeocodeAndIndexResponseValue {
    
    /** The resume you sent to be geocoded/indexed with geolocation coordinates added */
    public ParsedResume ResumeData;
}
