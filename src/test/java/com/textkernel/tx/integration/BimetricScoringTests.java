// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.exceptions.SovrenException;
import com.textkernel.tx.models.api.bimetricscoring.ParsedJobWithId;
import com.textkernel.tx.models.api.bimetricscoring.ParsedResumeWithId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BimetricScoringTests extends TestBase {
    private static final ParsedJobWithId TestParsedJobWithId;
    private static final ParsedResumeWithId TestParsedResumeWithId;
    static {
        TestParsedJobWithId = new ParsedJobWithId();
        TestParsedJobWithId.Id = "1";
        TestParsedJobWithId.JobData = TestParsedJob;

        TestParsedResumeWithId = new ParsedResumeWithId();
        TestParsedResumeWithId.Id = "1";
        TestParsedResumeWithId.ResumeData = TestParsedResume;
    }

    @Test
    public void testBimetricScoringResume() {
        assertThrows(SovrenException.class, () -> {
            Client.bimetricScore(new ParsedResumeWithId(), new ArrayList<ParsedResumeWithId>(), null, null);
        });

        assertThrows(SovrenException.class, () -> {
            Client.bimetricScore(TestParsedResumeWithId, new ArrayList<ParsedResumeWithId>(), null, null);
        });

        assertThrows(SovrenException.class, () -> {
            List<ParsedResumeWithId> list = new ArrayList<>();
            list.add(TestParsedResumeWithId);
            Client.bimetricScore(new ParsedResumeWithId(), list, null, null);
            });

        assertDoesNotThrow(() -> {
            List<ParsedResumeWithId> list = new ArrayList<>();
            list.add(TestParsedResumeWithId);
            Client.bimetricScore(TestParsedResumeWithId, list, null, null);
        });
    }

    @Test
    public void testBimetricScoringJob() {
        assertThrows(SovrenException.class, () -> {
            Client.bimetricScore(new ParsedJobWithId(), new ArrayList<ParsedJobWithId>(), null, null);
        });

        assertThrows(SovrenException.class, () -> {
            Client.bimetricScore(TestParsedJobWithId, new ArrayList<ParsedJobWithId>(), null, null);
        });

        assertThrows(SovrenException.class, () -> {
            List<ParsedJobWithId> list = new ArrayList<>();
            list.add(TestParsedJobWithId);
            Client.bimetricScore(new ParsedJobWithId(), list, null, null);
        });

        assertDoesNotThrow(() -> {
            List<ParsedJobWithId> list = new ArrayList<>();
            list.add(TestParsedJobWithId);
            Client.bimetricScore(TestParsedJobWithId, list, null, null);
        });
    }
}
