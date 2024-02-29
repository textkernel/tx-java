// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.jobdescription;

import java.util.List;

/*Request body for a 'Generate Job' request */
public class GenerateJobRequest {

    /** The title of the job. */
    public String JobTitle;

    /** List of skill requirements for the job. */
    public List<GenerateJobSkill> Skills;

    /** The tone of the job description. */
    public JobTone Tone;

    /** Language of the job description, in ISO 639-1 code format. */
    public String Language;

    /** Location of the job. */
    public String Location;

    /** The organization offering the job. */
    public String Organization;

}
