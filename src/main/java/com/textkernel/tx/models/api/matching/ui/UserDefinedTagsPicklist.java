// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.ui;

import java.util.List;

/** A picklist to show to a user for filtering on user-defined tags*/
public class UserDefinedTagsPicklist {

    /** The label for this picklist in the UI */
    public String Label;

    /** A list of user-defined tags that the user will be able to pick from */
    public List<UserDefinedTagOption> Options;
}
