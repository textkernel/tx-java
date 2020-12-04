// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

import com.sovren.models.resume.ParsedResume;

/**
* Options for geocoding a resume (specifying some location on Earth)
*/
public class GeocodeResumeRequest extends GeocodeOptionsBase {
    /**  The resume you wish to be geocoded */
    public ParsedResume ResumeData;
}
