// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.matching.ui.hooks;

/** An action performed with a URL (opening a new window, displaying some webpage)*/
public class UrlAction {
    /**
    * The URL to show (either in another tab/window or in an iFrame inside the Matching UI).
    * <br>NOTE: the UI will do a string.replace() on this URL to replace {id} with the document
    * id (that this action was performed on) and {indexId} with the index id (containing the document the action
    * was performed on).
    * <br>
    * For example: https://my-ats.com/contact-info/{id} gets transformed to https://my-ats.com/contact-info/34879
    */
    public String Url;

    /**
    * Use "sovren" to open a popup inside the Matching UI and display the webpage.
    * Any other value will be used just like the target attribute on a normal anchor tag ("_blank" for a new tab/window, etc).
    */
    public String Target;
}
