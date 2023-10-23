// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.job.ParsedJob;

/** Request body for geocoding a job and then adding into an index */
public class GeocodeAndIndexJobRequest extends GeocodeAndIndexRequest {
    /**  The job you wish to be geocoded/indexed */
    public ParsedJob JobData;
}
