// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.assistants.jobdescription;

/** Request body for 'Suggest Skills for Job Title' request */
public class SuggestSkillsFromJobTitleRequest {
    
    /** The title of the job for which skills are being suggested. */
    public String JobTitle;

    /** Language of the suggested skills in ISO 639-1 code format. */
    public String Language;

    /** Maximum number of skills to suggest. Must be within [1 - 50]. Default is 10. */
    public Integer Limit;

}
