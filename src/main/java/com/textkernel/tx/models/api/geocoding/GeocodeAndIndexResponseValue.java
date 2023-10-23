// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.api.ApiResponseInfoLite;

/** The {@link ApiResponse#Value} from a 'geocode and index' response */
public class GeocodeAndIndexResponseValue {
    
    /** If geocoding was requested, the status of the geocode transaction will be output here */
    public ApiResponseInfoLite GeocodeResponse;

    /** If indexing was requested, the status of the index transaction will be output here */
    public ApiResponseInfoLite IndexingResponse;
}
