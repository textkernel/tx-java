// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.api.ApiResponse;
import com.textkernel.tx.models.job.ParsedJob;

/**
* The {@link ApiResponse#Value} from a Parse response
*/
public class ParseJobResponseValue extends BaseParseResponseValue {
    
    /** The main output from the Sovren Job Parser*/
    public ParsedJob JobData;
}
