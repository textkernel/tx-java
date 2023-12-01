// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.resume.ParsedResume;

/** Request body for geocoding a resume and then adding into an index */
public class GeocodeAndIndexResumeRequest extends GeocodeAndIndexRequest {
    /**  The resume you wish to be geocoded/indexed*/
    public ParsedResume ResumeData;
}
