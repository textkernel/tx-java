// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

/** A base class for all 3 kinds of hooks*/
public class UserActionHook {
    /** Text to display on the button for the user action. */
    public String LinkText;

    /**
     * Set to {@code true} to allow users to select multiple documents and perform this action on all of them at once.
     * <br>NOTE: this can only be set to {@code true} when you use a {@link JsAction}. {@link UrlAction}s are not supported.
     * <br>See https://developer.textkernel.com/tx-platform/v10/matching-ui/overview/#ui-match-hooks for more info.
     * <br>This value is not supported for Sourcing hooks, yet
    */
    public boolean IsBulk;
}
