// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.ui.hooks;

import java.util.List;

/** A collection of Matching UI User Action Hooks*/
public class UserActionHookCollection {
    /**
    * The client-side <a href="https://docs.sovren.com/Documentation/AIMatching#ui-match-hooks">User Action Hooks</a>
    * for a Matching UI session. These can be used to do some client-side action (opening a tab/window, running some javascript)
    * when a user clicks a button on a particular match result.
    */
    public List<ClientSideHook> Client;

    /**
    * The server-side (HTTP POST)
    * <a href="https://docs.sovren.com/Documentation/AIMatching#ui-match-hooks">User Action Hooks</a>
    * for a Matching UI session. These can be used to do some server-side action (performs an HTTP POST to your server) 
    * when a user clicks a button on a particular match result.
    */
    public List<ServerSideHook> Server;

    /**
    * The server-side (HTTP POST)
    * <a href="https://docs.sovren.com/Documentation/AIMatching#ui-match-hooks">User Action Hooks</a>
    * for 'Sovren Sourcing' results during a Matching UI session. These can be used to do some server-side action (performs an HTTP POST to your server) 
    * when a user clicks a button on a particular 'Sovren Sourcing' result.
    */
    public List<SourcingHook> Sourcing;
}
