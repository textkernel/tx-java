// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import com.textkernel.tx.models.job.ParsedJob;

/**
 * Information for adding a single job to an index as part of a 'batch upload'
 */
public class IndexJobInfo extends IndexMultipleDocumentInfo {
    
    /**  A job to index */
    public ParsedJob JobData;
}
