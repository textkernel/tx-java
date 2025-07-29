// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.exceptions.TxException;
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
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().bimetricScore(new ParsedResumeWithId(), new ArrayList<ParsedResumeWithId>(), null, null);
        });

        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().bimetricScore(TestParsedResumeWithId, new ArrayList<ParsedResumeWithId>(), null, null);
        });

        assertThrows(TxException.class, () -> {
            List<ParsedResumeWithId> list = new ArrayList<>();
            list.add(TestParsedResumeWithId);
            Client.searchMatchV1().bimetricScore(new ParsedResumeWithId(), list, null, null);
            });

        assertDoesNotThrow(() -> {
            List<ParsedResumeWithId> list = new ArrayList<>();
            list.add(TestParsedResumeWithId);
            Client.searchMatchV1().bimetricScore(TestParsedResumeWithId, list, null, null);
        });
    }

    @Test
    public void testBimetricScoringJob() {
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().bimetricScore(new ParsedJobWithId(), new ArrayList<ParsedJobWithId>(), null, null);
        });

        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().bimetricScore(TestParsedJobWithId, new ArrayList<ParsedJobWithId>(), null, null);
        });

        assertThrows(TxException.class, () -> {
            List<ParsedJobWithId> list = new ArrayList<>();
            list.add(TestParsedJobWithId);
            Client.searchMatchV1().bimetricScore(new ParsedJobWithId(), list, null, null);
        });

        assertDoesNotThrow(() -> {
            List<ParsedJobWithId> list = new ArrayList<>();
            list.add(TestParsedJobWithId);
            Client.searchMatchV1().bimetricScore(TestParsedJobWithId, list, null, null);
        });
    }
}
