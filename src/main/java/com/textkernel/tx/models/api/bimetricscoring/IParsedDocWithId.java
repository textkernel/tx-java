// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.bimetricscoring;

/** Interface to simplify Bimetric Score requests */
public interface IParsedDocWithId {

    /**
     * @return The id of the document (used in the response body)
     */
    public String getDocId();
}
