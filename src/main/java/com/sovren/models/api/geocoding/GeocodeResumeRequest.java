package com.sovren.models.api.geocoding;

import com.sovren.models.resume.ParsedResume;

/**
* Options for geocoding a resume (specifying some location on Earth)
*/
public class GeocodeResumeRequest extends GeocodeOptionsBase {
    /**  The resume you wish to be geocoded */
    public ParsedResume ResumeData;
}
