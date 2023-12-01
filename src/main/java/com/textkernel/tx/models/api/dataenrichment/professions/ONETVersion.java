// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.dataenrichment.professions;

import com.google.gson.annotations.SerializedName;

/**
*  Available ONET Versions
*/
public enum ONETVersion {
    /**  ONET 2010 */
    @SerializedName("2010")
    ONET2010,
     /**  ONET 2010 */
    @SerializedName("2019")
    ONET2019
}