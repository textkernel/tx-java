// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.request;

import com.textkernel.tx.models.api.parsing.ProfessionsSettings;

/**
* Settings for searching/matching
*/
public class SearchMatchSettings {

    /** Set to {@code true} to turn off variation matches in job titles.*/
    public boolean PositionTitlesMustHaveAnExactMatch;

    /**
    * Normalize the first three job titles specified in {@link FilterCriteria#JobTitles} and automatically include them in the query
    * (<a href="https://developer.textkernel.com/Sovren/v10/overview/#transaction-cost">additional charges apply</a>).
    * <p>
    * You will only benefit from using this parameter if the data in your index was parsed with {@link ProfessionsSettings#Normalize} enabled.
    * <p>
    * Normalized job titles help identify more matches by looking beyond the exact job title. Normalization uses lists 
    * of synonyms behind the scenes. For example, a search for "HR Advisor" will also return results for "Human Resources Consultant".
    * <p>
    * When matching, the normalized job title is automatically included in the query if the data in your index was parsed with {@link ProfessionsSettings#Normalize} enabled.
    */
    public boolean NormalizeJobTitles;

    /**
    * Specify the language (ISO 639-1 code) of the Job Title to be normalized. This defaults to {@code en}. See <a href="https://developer.textkernel.com/Professions/master/">list of supoprted languages.</a>
    */
    public String NormalizeJobTitlesLanguage;
}
