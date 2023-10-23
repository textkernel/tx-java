// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/** Request body for an 'index multiple resumes' request */
public class IndexMultipleResumesRequest {
    
    /** The resumes to add into the index */
    public List<IndexResumeInfo> Resumes;
}
