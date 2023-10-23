// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

import com.sovren.models.resume.ParsedResume;

/**
 * Information for adding a single resume to an index as part of a 'batch upload'
 */
public class IndexResumeInfo extends IndexMultipleDocumentInfo {
    
    /**  A resume to index */
    public ParsedResume ResumeData;
}
