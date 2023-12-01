// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

/** A hook that does some client-side action (opening a tab/window, running some javascript)*/
public class ClientSideHook extends UserActionHook {
    /**
    * A Javascript action to perform when the user clicks the button. This will post a Javascript
    * message back to the parent/opener window so that the integrator can run some Javascript.
    * <br>This uses <code>window.postMessage()</code>
    * <br>NOTE: you can use this or {@link #UrlAction} but not both
    */
    public JsAction JsAction;

    /**
    * A URL action to perform when the user clicks the button. This can open a new window or redirect an existing window to a URL.
    * <br>NOTE: you can use this or {@link #JsAction} but not both
    */
    public UrlAction UrlAction;
}
