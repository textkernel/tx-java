// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.indexes;

import java.util.List;

/**
 * Request body to update (add/remove/overwrite) user-defined tags on an indexed
 * document
 */
public class UpdateUserDefinedTagsRequest {
    
    /** The user-defined tags to add/delete/etc */
    public List<String> UserDefinedTags;

    /** Which method to use for the specified user-defined tags */
    public UserDefinedTagsMethod Method;
}
