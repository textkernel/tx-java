// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.response;

/** Information about the lookup profession. */
public class LookupProfessionGroupClassInfo {
    /** The class which this profession belongs to. */
    public LookupGroupOrClassInfo<Integer> Class;
    /** The group which this profession belongs to. */
    public LookupGroupOrClassInfo<Integer> Group;
    /** The unique code ID of the profession in the <a href="https://sovren.com/technical-specs/latest/rest-api/data-enrichment-services/overview/#professions-taxonomies">Sovren Professions Taxonomy</a>. */
    public int CodeId;
    /** The description of the profession in the desired language. */
    public String Description;
}