// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.geocoding;

import com.sovren.models.job.ParsedJob;

/** Request body for geocoding a job and then adding into an index */
public class GeocodeAndIndexJobRequest extends GeocodeAndIndexRequest {
    /**  The job you wish to be geocoded/indexed */
    public ParsedJob JobData;
}
