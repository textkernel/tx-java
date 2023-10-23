// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

import java.util.List;

/** Request body for an 'index multiple jobs' request */
public class IndexMultipleJobsRequest {
    
    /** The jobs to add into the index */
    public List<IndexJobInfo> Jobs;
}
