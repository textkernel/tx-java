// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

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