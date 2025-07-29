// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.documents;

import java.util.Map;

import com.textkernel.tx.models.resume.ParsedResume;

/** Request body for AddCandidate request */
public class AddCandidateRequest extends AddDocumentRequest {
    /** A boolean flag to strip PII data out of the requests before sending to Search &amp; Match V2 */
    public boolean Anonymize;

    /** Parsed output from the CV/Resume Parser.  */
    public ParsedResume ResumeData;

    /** A collection of custom fields represented as key-value pairs. */
    public Map<String, String> CustomFields;
}
