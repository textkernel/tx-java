// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matching.ui;

/** The response body from a request to generate the Matching UI*/
public class GenerateUIResponse {

    /**
     * The URL to navigate to (or set as the src for an iFrame) that will
     * display the Matching UI. This URL will be valid for at least 24 hours.
     * <br><b>NOTE: for security purposes, you should redirect a user to the session
     * immediately after generating the session for that user</b>
     * <br>For more info, see https://sovren.com/technical-specs/latest/rest-api/matching-ui/overview/#authentication
     * */
    public String url;

    /**
     * The number of seconds until the URL auto-authentication expires. If you try
     * to use this URL after that period of time, the user will be asked to
     * login (if not already authenticated)
     * */
    public int expires_in;
}
