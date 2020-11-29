package com.sovren.models.api.geocoding;

import com.sovren.models.api.ApiResponse;
import com.sovren.models.resume.ParsedResume;

/** The {@link ApiResponse#Value} from a 'geocode resume' response */
public class GeocodeResumeResponseValue {
    
    /** The resume you sent to be geocoded with geolocation coordinates added */
    public ParsedResume ResumeData;
}
