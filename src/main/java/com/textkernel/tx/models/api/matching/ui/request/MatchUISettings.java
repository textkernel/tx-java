// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.ui.request;

import com.sovren.models.api.geocoding.GeocodeOptions;
import com.sovren.models.api.matching.ui.UIOptions;
import com.sovren.models.api.parsing.BasicParseOptions;

/** Settings for generating a Matching UI session*/
public class MatchUISettings {

    /** Various options for the Matching UI user experience*/
    public UIOptions UIOptions;

    /**
     * Options for parsing documents from external sources such as job boards
     * and Sovren custom web sourcing. You only need to use this if you are using Sovren Sourcing
    */
    public BasicParseOptions ParseOptions;

    /**
     * Settings for geocoding within the Matching UI. This is used
     * when you allow your users to perform radius filtering.
    */
    public GeocodeOptions GeocodeOptions;

    void copyFrom(MatchUISettings other) {
        if (other != null) {
            UIOptions = other.UIOptions;
            ParseOptions = other.ParseOptions;
            GeocodeOptions = other.GeocodeOptions;
        }
    }
}
