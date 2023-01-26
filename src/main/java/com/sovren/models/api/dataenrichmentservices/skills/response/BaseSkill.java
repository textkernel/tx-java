// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.skills.response;

/** A skill from the skill taxonomy. */
public class BaseSkill {
    /** Type of skill. Possible values are Professional, IT, Language, or Soft. */
    public String Type;
    /** The ID for the skill in the skills taxonomy. */
    public String Id;
    /** Overall confidence that the skill was normalized to the correct skill. */
    public float Confidence;
    /** The description of the normalized skill concept in the requested language. */
    public String Description;
    /** The language ISO 639-1 code. This will only appear for language skills (Type = Language). */
    public String IsoCode;
}