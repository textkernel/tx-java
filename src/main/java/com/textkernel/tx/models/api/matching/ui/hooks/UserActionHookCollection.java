// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

import java.util.List;

/** A collection of Matching UI User Action Hooks*/
public class UserActionHookCollection {
    /**
    * The client-side <a href="https://developer.textkernel.com/Sovren/v10/matching-ui/overview/#ui-match-hooks">User Action Hooks</a>
    * for a Matching UI session. These can be used to do some client-side action (opening a tab/window, running some javascript)
    * when a user clicks a button on a particular match result.
    */
    public List<ClientSideHook> Client;

    /**
    * The server-side (HTTP POST)
    * <a href="https://developer.textkernel.com/Sovren/v10/matching-ui/overview/#ui-match-hooks">User Action Hooks</a>
    * for a Matching UI session. These can be used to do some server-side action (performs an HTTP POST to your server) 
    * when a user clicks a button on a particular match result.
    */
    public List<ServerSideHook> Server;

    /**
    * The server-side (HTTP POST)
    * <a href="https://developer.textkernel.com/Sovren/v10/matching-ui/overview/#ui-match-hooks">User Action Hooks</a>
    * for 'Sourcing' results during a Matching UI session. These can be used to do some server-side action (performs an HTTP POST to your server) 
    * when a user clicks a button on a particular 'Sourcing' result.
    */
    public List<SourcingHook> Sourcing;
}
