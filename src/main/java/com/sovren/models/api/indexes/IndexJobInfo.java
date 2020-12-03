// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

import com.sovren.models.job.ParsedJob;

/**
 * Information for adding a single job to an index as part of a 'batch upload'
 */
public class IndexJobInfo extends IndexMultipleDocumentInfo {
    
    /**  A job to index */
    public ParsedJob JobData;
}
