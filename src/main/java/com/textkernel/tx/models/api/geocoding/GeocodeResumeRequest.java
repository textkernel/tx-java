// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.geocoding;

import com.textkernel.tx.models.resume.ParsedResume;

/**
* Options for geocoding a resume (specifying some location on Earth)
*/
public class GeocodeResumeRequest extends GeocodeOptionsBase {
    /**  The resume you wish to be geocoded */
    public ParsedResume ResumeData;
}
