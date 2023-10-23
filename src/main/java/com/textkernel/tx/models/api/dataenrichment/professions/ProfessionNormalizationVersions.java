// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.models.api.dataenrichment.professions;

/**
 * Versions to use when normalizing professions if more than one is available for a taxonomy
 */
public class ProfessionNormalizationVersions {
    /**
    *  The ONET Version to use when normalizing Professions. Defaults to ONET2010
    */
    public ONETVersion ONET;
}