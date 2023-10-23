// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.resume.ParsedResume;

/** Request body for geocoding a resume and then adding into an index */
public class GeocodeAndIndexResumeRequest extends GeocodeAndIndexRequest {
    /**  The resume you wish to be geocoded/indexed*/
    public ParsedResume ResumeData;
}
