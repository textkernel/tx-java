// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.documents;

import java.util.Map;

import com.textkernel.tx.models.job.ParsedJob;

/** Request body for AddJob request */
public class AddJobRequest extends AddDocumentRequest {
    /** Parsed output from the Job Parser.  */
    public ParsedJob JobData;

    /** A collection of custom fields represented as key-value pairs. */
    public Map<String, String> CustomFields;
}
