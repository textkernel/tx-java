// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import com.textkernel.tx.models.job.ParsedJob;

/** Request body for an 'index job' request */
public class IndexJobRequest extends IndexDocumentRequest {
    
    /**  A job to index */
    public ParsedJob JobData;
}
