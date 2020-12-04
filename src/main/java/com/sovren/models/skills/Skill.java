// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.skills;

/**
* A skill listed in a resume or job
*/
public abstract class Skill {

    /** The Id of the skill*/
    public String Id;

    /** The name of the skill*/
    public String Name;

    /** Whether or not this skill was found verbatim in the text*/
    public boolean ExistsInText;
}
