// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.response;

/** A skill based on the given Prefix. */
public class AutoCompleteSkill {
    /** The description of the skill in the requested language. */
    public String Description;
    /** The ID of the skill in the skills taxonomy. */
    public String Id;
    /** Type of skill. Possible values are Professional, IT, Language, or Soft. */
    public String Type;
}