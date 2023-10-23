// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.matching.ui.hooks;

/** A hook that does some server-side action for sourcing results (performs and HTTP POST to your server)
 * <br>NOTE: Bulk actions are not supported for Sourcing hooks, yet. Setting {@link UserActionHook#IsBulk} will have no effect.
 * */
public class SourcingHook extends ServerSideHook {
}
