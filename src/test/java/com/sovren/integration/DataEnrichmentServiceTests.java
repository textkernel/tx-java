// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.integration;

import com.sovren.TestBase;
import com.sovren.exceptions.SovrenException;
import com.sovren.models.api.dataenrichmentservices.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class DataEnrichmentServiceTests extends TestBase {
    @Test
    public void testSkillsTaxonomy() {
        assertDoesNotThrow(() -> {
            Client.getSkillsTaxonomy("json", "en");
        });
    }

    @Test
    public void testSkillAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            ArrayList<String> categories = new ArrayList<String>();
            categories.add("all");
            Client.skillsAutoComplete("soft", 10, categories, languages, "en");
        });
    }

    @Test
    public void testSkillsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<String> lookupList = new ArrayList<String>();
            lookupList.add("KS120B874P2P6BK1MQ0T");
            ArrayList<String> categories = new ArrayList<String>();
            categories.add("all");
            Client.skillsLookup(lookupList, categories, "en");
        });
    }

    @Test
    public void testSkillsNormalize() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skills = new ArrayList<String>();
            skills.add("Microsoft excel");
            Client.skillsNormalize(skills, "en", "en");
        });
    }

    @Test
    public void testSkillsExtract() {
        assertDoesNotThrow(() -> {
            Client.skillsExtract("Microsoft, developer python, software, clerical office assistant, excel", "en", 0.5f, "en");
        });
    }

    @Test
    public void testProfessionsTaxonomy() {
        assertDoesNotThrow(() -> {
            Client.getProfessionsTaxonomy("json", "en");
        });
    }

    @Test
    public void testProfessionsAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> categories = new ArrayList<String>();
            categories.add("all");
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            Client.professionsAutoComplete("soft", 10, categories, languages, "en");
        });
    }

    @Test
    public void testProfessionsNormalize() {
        assertDoesNotThrow(() -> {
            ArrayList<String> jobTitlesToNormalize = new ArrayList<String>();
            jobTitlesToNormalize.add("Software Engineer");
            Client.professionsNormalize(jobTitlesToNormalize, "en", "en");
        });
    }

    @Test
    public void testProfessionsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<String> professionCodeIds = new ArrayList<String>();
            professionCodeIds.add("2000");
            Client.professionsLookup(professionCodeIds, "en");
        });
    }

    @Test
    public void testCompareSkills() {
        assertDoesNotThrow(() -> {
            ArrayList<String> professionCodeIds = new ArrayList<String>();
            professionCodeIds.add("696");
            professionCodeIds.add("3178");
            Client.compareSkills(professionCodeIds);
        });
    }

    @Test
    public void testCompareSkillsToProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillCodeIds = new ArrayList<String>();
            skillCodeIds.add("KS120076FGP5WGWYMP0F");
            skillCodeIds.add("KS04UWLJBN9X1M3N0PZ4");
            Client.compareSkillsToProfessions(skillCodeIds, "696");
        });
    }

    @Test
    public void testSuggestSkills() {
        assertDoesNotThrow(() -> {
            ArrayList<String> professionCodeIds = new ArrayList<String>();
            professionCodeIds.add("696");
            Client.suggestSkills(professionCodeIds, 10);
        });
    }

    @Test
    public void testSuggestProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillCodeIds = new ArrayList<String>();
            skillCodeIds.add("KS120076FGP5WGWYMP0F");
            skillCodeIds.add("KS125HH5XDBPZT3RFGZZ");
            skillCodeIds.add("KS124PR62MV42B5C9S9F");
            Client.suggestProfessions(skillCodeIds, 10, false);
        });
    }
}