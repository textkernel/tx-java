// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.job.ParsedJob;

/** The {@link ApiResponse#Value} from a 'geocode job' response */
public class GeocodeJobResponseValue {
    
    /** The job you sent to be geocoded with geolocation coordinates added */
    public ParsedJob JobData;
}
