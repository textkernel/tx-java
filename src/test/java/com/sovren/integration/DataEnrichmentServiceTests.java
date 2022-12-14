// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package test.java.com.sovren.integration;

import com.sovren.TestBase;
import com.sovren.exceptions.SovrenException;
import com.sovren.models.api.dataenrichmentservices.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            Client.skillsAutoComplete("soft", 10, new List<String>("all"), new List<String>("en"), "en");
        });
    }

    @Test
    public void testSkillsLookup() {
        assertDoesNotThrow(() -> {
            Client.skillsLookup(new List<String>("KS120B874P2P6BK1MQ0T"), new List<String>("all"), "en");
        });
    }

    @Test
    public void testSkillsNormalize() {
        assertDoesNotThrow(() -> {
            Client.skillsNormalize(new List<String>("Microsoft excel"), "en", "en");
        });
    }

    @Test
    public void testSkillsExtract() {
        assertDoesNotThrow(() -> {
            Client.skillsExtract("Microsoft, developer python, software, clerical office assistant, excel", "en", 0.5, "en");
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
            Client.professionsAutoComplete("soft", 10, new List<String>("all"), new List<String>("en"), "en");
        });
    }

    @Test
    public void testProfessionsNormalize() {
        assertDoesNotThrow(() -> {
            Client.professionsNormalize(new List<String>("Software Engineer"), "en", "en");
        });
    }

    @Test
    public void testProfessionsLookup() {
        assertDoesNotThrow(() -> {
            Client.professionsLookup(new List<String>("2000"), "en");
        });
    }

    @Test
    public void testCompareSkills() {
        assertDoesNotThrow(() -> {
            Client.compareSkills(new List<String>("696", "3178"));
        });
    }

    @Test
    public void testCompareSkillsToProfessions() {
        assertDoesNotThrow(() -> {
            Client.compareSkillsToProfessions(new List<String>("KS120076FGP5WGWYMP0F", "KS04UWLJBN9X1M3N0PZ4"), "696");
        });
    }

    @Test
    public void testSuggestSkills() {
        assertDoesNotThrow(() -> {
            Client.suggestSkills(new List<String>("696"), 10);
        });
    }

    @Test
    public void testSuggestProfessions() {
        assertDoesNotThrow(() -> {
            Client.suggestProfessions(new List<String>("KS120076FGP5WGWYMP0F", "KS125HH5XDBPZT3RFGZZ", "KS124PR62MV42B5C9S9F"), 10, false);
        });
    }
}