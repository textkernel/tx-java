// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.request;

import java.util.List;

/** Request body for a 'NormalizeProfessions' request */
public class NormalizeProfessionsRequest {
        /** The list of job titles to normalize (up to 10 job titles, each job title may not exceed 400 characters). */
        public List<String> JobTitles;
        /** The language of the input job titles. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>. */
        public String Language;
        /** The language to use for descriptions of the returned normalized professions. Must be one of the supported <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-languages">ISO codes</a>. */
        public String OutputLanguage;
}