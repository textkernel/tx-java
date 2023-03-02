// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichment.professions;

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