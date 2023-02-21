// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.skills.response;
import java.util.List;

import com.sovren.models.api.dataenrichment.professions.response.LangDescription;
/** A skill object. */
public class Code {
    /** The ID for the skill in the taxonomy. */
    public String Id;
    /** A list of descriptions of the skill in all supported/requested languages. */
    public List<LangDescription> Descriptions;
    /** Type of skill. Possible values are Professional, IT, Language, or Soft. */
    public String Type;
    /** The language ISO 639-1 code. This will only appear for language skills (Type = Language). */
    public String IsoCode;
}
