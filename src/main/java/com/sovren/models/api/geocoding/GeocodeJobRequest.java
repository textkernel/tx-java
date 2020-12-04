// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/**
* Options for geocoding a job (specifying some location on Earth)
*/
public class GeocodeJobRequest extends GeocodeOptionsBase {
    /**  The job you wish to be geocoded */
    public ParsedJob JobData;
}
