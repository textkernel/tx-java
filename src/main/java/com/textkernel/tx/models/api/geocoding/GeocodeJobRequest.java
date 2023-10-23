// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.job.ParsedJob;

/**
* Options for geocoding a job (specifying some location on Earth)
*/
public class GeocodeJobRequest extends GeocodeOptionsBase {
    /**  The job you wish to be geocoded */
    public ParsedJob JobData;
}
