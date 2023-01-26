// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.integration;

import com.sovren.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

public class DataEnrichmentServiceTests extends TestBase {
    @Test
    public void testSkillsTaxonomy() {
        assertDoesNotThrow(() -> {
            Client.getSkillsTaxonomy("json");
        });
    }

    @Test
    public void testSkillsMetadata() {
        assertDoesNotThrow(() -> {
            Client.getSkillsMetadata();
        });
    }

    @Test
    public void testSkillAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            ArrayList<String> types = new ArrayList<String>();
            types.add("all");
            Client.skillsAutoComplete("soft", 10, types, languages, "en");
        });
    }

    @Test
    public void testSkillsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120B874P2P6BK1MQ0T");
            Client.skillsLookup(skillIds, "en");
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
    public void testProfessionsMetadata() {
        assertDoesNotThrow(() -> {
            Client.getProfessionsMetadata();
        });
    }

    @Test
    public void testProfessionsAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            Client.professionsAutoComplete("soft", 10, languages, "en");
        });
    }

    @Test
    public void testProfessionsNormalize() {
        assertDoesNotThrow(() -> {
            ArrayList<String> jobTitles = new ArrayList<String>();
            jobTitles.add("Software Engineer");
            Client.professionsNormalize(jobTitles, "en", "en");
        });
    }

    @Test
    public void testProfessionsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<Integer> codeIds = new ArrayList<Integer>();
            codeIds.add(2000);
            Client.professionsLookup(codeIds, "en");
        });
    }

    @Test
    public void testCompareProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<Integer> professionCodeIds = new ArrayList<Integer>();
            professionCodeIds.add(696);
            professionCodeIds.add(3178);
            Client.compareProfessions(professionCodeIds);
        });
    }

    @Test
    public void testCompareSkillsToProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120076FGP5WGWYMP0F");
            skillIds.add("KS04UWLJBN9X1M3N0PZ4");
            Client.compareSkillsToProfessions(skillIds, 696);
        });
    }

    @Test
    public void testSuggestSkills() {
        assertDoesNotThrow(() -> {
            ArrayList<Integer> professionCodeIds = new ArrayList<Integer>();
            professionCodeIds.add(696);
            Client.suggestSkills(professionCodeIds, 10);
        });
    }

    @Test
    public void testSuggestProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120076FGP5WGWYMP0F");
            skillIds.add("KS125HH5XDBPZT3RFGZZ");
            skillIds.add("KS124PR62MV42B5C9S9F");
            Client.suggestProfessions(skillIds, 10, false);
        });
    }
}