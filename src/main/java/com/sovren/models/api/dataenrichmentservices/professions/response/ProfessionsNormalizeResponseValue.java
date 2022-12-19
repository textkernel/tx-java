// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.professions.response;

public class ProfessionsNormalizeResponseValue {
    public NormalizedProfessionClassification<Integer> Profession;
    public NormalizedProfessionClassification<Integer> Group;
    public NormalizedProfessionClassification<Integer> Class;
    public VersionedNormalizedProfessionClassification<Integer> ISCO;
    public VersionedNormalizedProfessionClassification<String> ONET;
    public float Confidence;
}