// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

/** A method to use when updating user-defined tags on a document */
public enum UserDefinedTagsMethod {
    
    /** Deletes the specified user-defined tags from a document */
    Delete,

    /** Adds the specified user-defined tags to a document (in addition to any existing) */
    Add,

    /** Overwrites any existing user-defined tags with the specified tags*/
    Overwrite
}
