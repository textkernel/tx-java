// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.professions.request;

import java.util.List;

/** Request body for a 'NormalizeProfessions' request */
public class NormalizeProfessionsRequest {
        /** The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters). */
        public List<String> JobTitles;
        /** The language of the input job titles. Must be one of the supported <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
        public String Language;
        /** The language to use for descriptions of the returned normalized professions. Must be one of the supported <a href="https://developer.textkernel.com/Sovren/v10/data-enrichment/overview/#professions-languages">ISO codes</a>. */
        public String OutputLanguage;
}