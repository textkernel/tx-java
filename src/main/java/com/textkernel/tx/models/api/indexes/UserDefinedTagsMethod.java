// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.indexes;

/** A method to use when updating user-defined tags on a document */
public enum UserDefinedTagsMethod {
    
    /** Deletes the specified user-defined tags from a document */
    Delete,

    /** Adds the specified user-defined tags to a document (in addition to any existing) */
    Add,

    /** Overwrites any existing user-defined tags with the specified tags*/
    Overwrite
}
