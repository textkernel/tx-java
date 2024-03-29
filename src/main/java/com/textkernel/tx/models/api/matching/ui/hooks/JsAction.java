// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

/** An action performed in Javascript*/
public class JsAction {

    /**
    * Any data you want to be sent (in addition to document information) in the 'message'
    * parameter for the window.postMessage() call. For more information see 
    * https://developer.mozilla.org/en-US/docs/Web/API/Window/postMessage
    */
    public Object Data;

    /**
    * The 'targetOrigin' parameter for the window.postMessage() call. For more information see
    * https://developer.mozilla.org/en-US/docs/Web/API/Window/postMessage
    * <br>NOTE: while this is optional, it is recommended for security purposes in the window.postMessage() protocol
    */
    public String TargetOrigin;

    /**
    * One of "parent" or "opener", depending if you use an iFrame to show the Matching UI 
    * in your system, or if you open it in a separate tab/window.
    */
    public String Target;
}
