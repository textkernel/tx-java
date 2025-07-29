// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.autocomplete;

/** 
 * This object describes a single auto-completion result. The auto-completion service returns a
 * list of these completions. Besides the actual suggested completion item, it contains the field
 * name and field label of search field this suggestion should be targeted to.
*/
public class Completion {
    /** The field name. */
    public String Field;
    /** The display label of the field. */
    public String FieldLabel;
    /** The completion suggestion. */
    public String Item;
    /** Same as the item, but with highlighting tags showing the matching parts of the suggestion. */
    public String ItemHighlighted;
}
