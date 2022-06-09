// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.request;

/**
* Settings for searching/matching
*/
public class SearchMatchSettings {

    /** Set to {@code true} to turn off variation matches in job titles.*/
    public boolean PositionTitlesMustHaveAnExactMatch;

    /**
    * Normalize the first three job titles specified in FilterCriteria.JobTitles and automatically include them in the query. Additional <a href="https://sovren.com/technical-specs/latest/rest-api/overview/#transaction-cost">charges apply</a>.
    * <br/><br/>
    * You will only benefit from using this parameter if the data in your index was parsed with <a href="https://sovren.com/technical-specs/latest/rest-api/resume-parser/api/?h=ProfessionsSettings">Professions Normalization</a> enabled.
    * <br/><br/>
    * Normalized job titles help identify more matches by looking beyond the exact job title. Normalization uses lists of synonyms behind the scenes. For example, a search for "HR Advisor" will also return results for "Human Resources Consultant".
    * <br/><br/>
    * When matching, the normalized job title is automatically included in the query if the data in your index was parsed with <a href="https://sovren.com/technical-specs/latest/rest-api/resume-parser/api/?h=ProfessionsSettings">Professions Normalization</a> enabled.
    */
    public boolean NormalizeJobTitles;

    /**
    * Specify the language (ISO 639-1 code) of the Job Title to be normalized. This defaults to <code>en</code>. See <a href="https://developer.textkernel.com/Professions/master/">list of supoprted languages.</a>
    */
    public String NormalizeJobTitlesLanguage;
}
