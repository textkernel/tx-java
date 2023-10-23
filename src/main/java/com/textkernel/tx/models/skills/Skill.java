// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.skills;

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
