// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.models.api.dataenrichment.TaxonomyFormat;
import com.textkernel.tx.models.api.dataenrichment.ontology.response.SkillScore;
import com.textkernel.tx.models.api.dataenrichment.skills.response.AutoCompleteSkillsResponse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

public class DataEnrichmentServiceTests extends TestBase {
    @Test
    public void testSkillsTaxonomy() {
        assertDoesNotThrow(() -> {
            Client.getSkillsTaxonomy(TaxonomyFormat.json);
        });
    }

    @Test
    public void testSkillsMetadata() {
        assertDoesNotThrow(() -> {
            Client.getSkillsTaxonomyMetadata();
        });
    }

    @Test
    public void testSkillAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            ArrayList<String> types = new ArrayList<String>();
            types.add("all");
            Client.autocompleteSkill("soft", languages, "en", types, 10);
        });
    }

    @Test
    public void testSkillAutoCompleteV2() {
        AutoCompleteSkillsResponse[] wrapper = { null};

        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            ArrayList<String> types = new ArrayList<String>();
            types.add("certification");
            wrapper[0] = Client.autocompleteSkillV2("soft", languages, "en", types, 10);
        });

        assertNotNull(wrapper[0].Value);
        assertHasItems(wrapper[0].Value.Skills);
        assertEquals("Certification", wrapper[0].Value.Skills.get(0).Type);
    }

    @Test
    public void testSkillsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120B874P2P6BK1MQ0T");
            Client.lookupSkills(skillIds, "en");
        });
    }

    @Test
    public void testSkillsNormalize() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skills = new ArrayList<String>();
            skills.add("Microsoft excel");
            Client.normalizeSkills(skills, "en", "en");
        });
    }

    @Test
    public void testSkillsExtract() {
        assertDoesNotThrow(() -> {
            Client.extractSkills("Microsoft, developer python, software, clerical office assistant, excel", "en", "en", 0.5f);
        });
    }

    @Test
    public void testProfessionsTaxonomy() {
        assertDoesNotThrow(() -> {
            Client.getProfessionsTaxonomy("en",TaxonomyFormat.json);
        });
    }

    @Test
    public void testProfessionsMetadata() {
        assertDoesNotThrow(() -> {
            Client.getProfessionsTaxonomyMetadata();
        });
    }

    @Test
    public void testProfessionsAutoComplete() {
        assertDoesNotThrow(() -> {
            ArrayList<String> languages = new ArrayList<String>();
            languages.add("en");
            Client.autocompleteProfession("soft",languages , "en", 10 );
        });
    }

    @Test
    public void testProfessionsNormalize() {
        assertDoesNotThrow(() -> {
            ArrayList<String> jobTitles = new ArrayList<String>();
            jobTitles.add("Software Engineer");
            Client.normalizeProfessions(jobTitles, "en", "en");
        });
    }

    @Test
    public void testProfessionsLookup() {
        assertDoesNotThrow(() -> {
            ArrayList<Integer> codeIds = new ArrayList<Integer>();
            codeIds.add(2000);
            Client.lookupProfessions(codeIds, "en");
        });
    }

    @Test
    public void testCompareProfessions() {
        assertDoesNotThrow(() -> {
            Client.compareProfessions(696, 3178, "en");
        });
    }

    @Test
    public void testCompareSkillsToProfessions() {
        assertDoesNotThrow(() -> {
            List<SkillScore> skills = new ArrayList<SkillScore>();
            skills.add(new SkillScore("KS120076FGP5WGWYMP0F"));
            skills.add(new SkillScore("KS04UWLJBN9X1M3N0PZ4"));

            Client.compareSkillsToProfessions(696, "en", skills);
        });
    }

    @Test
    public void testSuggestSkillsFromProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<Integer> professionCodeIds = new ArrayList<Integer>();
            professionCodeIds.add(696);
            Client.suggestSkillsFromProfessions(professionCodeIds, 10, null);
        });
    }

    @Test
    public void testSuggestSkillsFromSkills() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120076FGP5WGWYMP0F");
            skillIds.add("KS125HH5XDBPZT3RFGZZ");
            skillIds.add("KS124PR62MV42B5C9S9F");
            Client.suggestSkillsFromSkills(skillIds, null);
        });
    }

    @Test
    public void testSuggestProfessions() {
        assertDoesNotThrow(() -> {
            ArrayList<String> skillIds = new ArrayList<String>();
            skillIds.add("KS120076FGP5WGWYMP0F");
            skillIds.add("KS125HH5XDBPZT3RFGZZ");
            skillIds.add("KS124PR62MV42B5C9S9F");
            Client.suggestProfessionsFromSkills(skillIds, null);
        });
    }

    @Test
    public void testSkillsSimilarityScore() {
        assertDoesNotThrow(() -> {
            ArrayList<SkillScore> skillIds = new ArrayList<SkillScore>();
            skillIds.add(new SkillScore("KS120076FGP5WGWYMP0F"));
            skillIds.add(new SkillScore("KS125HH5XDBPZT3RFGZZ"));
            skillIds.add(new SkillScore("KS124PR62MV42B5C9S9F"));
            Client.skillsSimilarityScore(skillIds, skillIds);
        });
    }
}