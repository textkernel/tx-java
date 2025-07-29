// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.indexes;

import com.textkernel.tx.TxClient;

/** The Search &amp; Match Version to use for indexing. */
public enum SearchAndMatchVersion {
    /** V1, see {@link TxClient#searchMatchV1()} */
    V1,
    /** V2, see {@link TxClient#searchMatchV2()} */
    V2
}
