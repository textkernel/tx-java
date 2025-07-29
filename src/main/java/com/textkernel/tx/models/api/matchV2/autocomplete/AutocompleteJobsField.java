// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.autocomplete;

import com.google.gson.annotations.SerializedName;

/** Which field should be used to generate completions for an autocomplete request */
public enum AutocompleteJobsField {
    /**
     * Generate completions from multiple dictionaries
     */
    @SerializedName("FULLTEXT")
    FullText,
    /**
     * Generate completions from IT skills in the index
     */
    @SerializedName("compskills")
    ITSkills,
    /**
     * Generate completions from language skills in the index
     */
    @SerializedName("langskills")
    LanguageSkills,
    /**
     * Generate completions from all job titles in the index
     */
    @SerializedName("job_title")
    JobTitle,
    /**
     * Generate completions from vacancy locations (addresses) in the index
     */
    @SerializedName("location")
    Location,
    /**
     * Generate completions from profession groups in the index
     */
    @SerializedName("work_field.profession_group")
    ProfessionGroup,
    /**
     * Generate completions from normalized international education levels in the
     * index
     */
    @SerializedName("education_level_international")
    EducationLevel
}
