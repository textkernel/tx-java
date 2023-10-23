// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

/** A hook that does some server-side action (performs an HTTP POST to your server)*/
public class ServerSideHook extends UserActionHook {
    /** The URL for an HTTP POST call to perform some action in your system.*/
    public String Url;

    /**
     * Any data from your system that you need to associate with this session/action.
     * This is sent (in addition to document information) in the POST body.
     * <br>For more information see https://developer.textkernel.com/Sovren/v10/matching-ui/overview/#ui-match-hooks
    */
    public Object CustomInfo;
}
