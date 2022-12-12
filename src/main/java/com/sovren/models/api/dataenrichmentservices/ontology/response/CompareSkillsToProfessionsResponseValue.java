// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.dataenrichmentservices.ontology.response;

public class CompareSkillsToProfessionsResponseValue {
    
    public OntologyMetadata Meta;
    public float SimilarityScore;
    public List<BaseProfession> CommonSkills;
    public CompareProfessionsSkills ExclusiveSkills;
}