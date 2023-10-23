// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui;

import com.textkernel.tx.models.api.matching.ui.hooks.UserActionHookCollection;

import java.util.List;

/** Options for creating the Matching UI*/
public class UIOptions {

    /**
     * The username of the user for which you are generating a Matching UI session.
     * <br><b>If you do not provide this, the user will be required to login when they view the page</b>
     * */
    public String Username;

    /**
     * Specifies custom style options for the Matching UI session.
     */
    public Style Style;

    /**
    * {@code true} to allow the user to see/modify the filter criteria (default = {@code true}).
    */
    public boolean ShowFilterCriteria = true;

    /**
     * If specified, only allows the user to see/modify certain filter categories.
     * Also, the order specified here is the order in which the filters will appear in the UI.
     */
    public List<FilterToShow> FiltersToShow;

    /**
    * {@code true} to execute the query as soon as the page loads (default = {@code false}). This is only applicable for Searching.
    * All matches/bimetric scoring are executed immediately even if this is {@code false}.
    */
    public boolean ExecuteImmediately;

    /**
    * {@code true} to show the banner containing your account logo inside the matching UI (default = {@code true}).
    */
    public boolean ShowBanner = true;

    /**
    * {@code true} to allow the user to see/modify the category weights (default = {@code true}).
    */
    public boolean ShowWeights = true;

    /**
    * {@code true} to show a button that opens the 'details' popup containing detailed job/resume info (default = {@code true}).
    */
    public boolean ShowDetailsButton = true;

    /**
    * {@code true} to add a button in the Actions menu that matches other jobs/resumes similar to the current one (default = {@code true}).
    */
    public boolean ShowFindSimilar = true;

    /**
    * {@code true} to include Sovren custom web sourcing in search/match results. Cannot be used for bimetric scoring (default = {@code false}).
    */
    public boolean ShowWebSourcing;

    /**
    * {@code true} to include job boards in search/match results. Cannot be used for bimetric scoring (default = {@code true}).
    * Must add credentials in the <a href="https://portal.sovren.com">Sovren Portal</a>
    */
    public boolean ShowJobBoards = true;

    /**
    * {@code true} to allow the user to save custom searches or select from pre-made criteria templates (default = {@code false}).
    */
    public boolean ShowSavedSearches;

    /**
    * Contains all the <a href="https://sovren.com/technical-specs/latest/rest-api/matching-ui/overview/#ui-match-hooks">User Action Hooks</a>
    * for the Match UI session. These are used to make a seamless integration between your system and the Sovren Matching UI.
    */
    public UserActionHookCollection Hooks;

    /**
    * Picklists shown in the UI for your users to filter on your user-defined tags.
    * <br>See also: https://sovren.com/technical-specs/latest/rest-api/ai-matching/overview/user-defined-tags/
    */
    public List<UserDefinedTagsPicklist> UserDefinedTagsPicklists;

    /**
    * If you are using custom skills, provide your custom skills list names here. The builtin Sovren skills lists are always included.
    */
    public List<String> SkillsAutoCompleteCustomSkillsList;
}
