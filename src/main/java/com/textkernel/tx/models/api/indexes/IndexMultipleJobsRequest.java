// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/** Request body for an 'index multiple jobs' request */
public class IndexMultipleJobsRequest {
    
    /** The jobs to add into the index */
    public List<IndexJobInfo> Jobs;
}
