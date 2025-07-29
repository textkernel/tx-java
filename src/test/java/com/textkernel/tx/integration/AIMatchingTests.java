// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;

import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.api.matching.MatchResponseValue;
import com.textkernel.tx.models.api.matching.SearchResponseValue;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import com.textkernel.tx.models.api.matching.request.PaginationSettings;
import com.textkernel.tx.models.matching.IndexType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AIMatchingTests extends TestBase {
    private static final String _jobIndexId = "Java-SDK-job-AIMatchingTests";
    private static final String _resumeIndexId = "Java-SDK-resume-AIMatchingTests";
    private static final String _documentId = "1";

    private static final List<String> _resumesIndexes;
    private static final List<String> _jobsIndexes;
    static {
        _resumesIndexes = new ArrayList<>();
        _resumesIndexes.add(_resumeIndexId);

        _jobsIndexes = new ArrayList<>();
        _jobsIndexes.add(_jobIndexId);
    }

    @BeforeAll
    static void setup() throws TxException {
        // create indexes
        Client.searchMatchV1().createIndex(IndexType.Job, _jobIndexId);
        Client.searchMatchV1().createIndex(IndexType.Resume, _resumeIndexId);
        delayForIndexSync();

        // add a document to each index
        Client.searchMatchV1().indexDocument(TestParsedJobTech, _jobIndexId, _documentId, null);
        Client.searchMatchV1().indexDocument(TestParsedResume, _resumeIndexId, _documentId, null);
        delayForIndexSync();
    }

    @AfterAll
    static void done() {
        cleanUpIndex(_jobIndexId);
        cleanUpIndex(_resumeIndexId);
    }

    private static Stream<Arguments> provideSearchTerms() {
        return Stream.of(
                Arguments.of(_jobIndexId, "Developer"),
                Arguments.of(_resumeIndexId, "VB6")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSearchTerms")
    public void testSearch(String indexId, String validSearchTerm) {
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().search(null, null, null, null);
        });

        List<String> indexesToQuery = new ArrayList<>();
        indexesToQuery.add(indexId);
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().search(indexesToQuery, null, null, null);
        });

        FilterCriteria filterCritera = new FilterCriteria();
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().search(null, filterCritera, null, null);
        });

        assertThrows(TxException.class, () -> {
            List<String> indexes = new ArrayList<>();
            indexes.add("fake-index-id");
            Client.searchMatchV1().search(indexes, filterCritera, null, null);
        });

        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().search(indexesToQuery, filterCritera, null, null);
        });

        filterCritera.SearchExpression = validSearchTerm;
        assertDoesNotThrow(() -> {
            PaginationSettings pageSettings = new PaginationSettings();
            pageSettings.Take = 10;
            SearchResponseValue response = Client.searchMatchV1().search(indexesToQuery, filterCritera, null, pageSettings).Value;
            assertEquals(1, response.CurrentCount);
            assertEquals(1, response.TotalCount);
        });

        filterCritera.SearchExpression = "ThisIsATermThatIsntInTheDocument";
        assertDoesNotThrow(() -> {
            SearchResponseValue response = Client.searchMatchV1().search(indexesToQuery, filterCritera, null, null).Value;
            assertEquals(0, response.CurrentCount);
            assertEquals(0, response.TotalCount);
        });
    }
    
    @Test
    public void testMatchJob()
    {
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().match(TestParsedJobTech, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(TestParsedJobTech, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(TestParsedJobTech, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }

    @Test
    public void testMatchResume() {
        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().match(TestParsedResume, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(TestParsedResume, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(TestParsedResume, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }

    @Test
    public void testMatchIndexedDocument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match("", null, null, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match(null, _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match("", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match(" ", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match(_resumeIndexId, null, _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match(_resumeIndexId, "", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.searchMatchV1().match(_resumeIndexId, " ", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().match(_resumeIndexId, _documentId, null, null, null, null, 0); ;
        });

        assertThrows(TxException.class, () -> {
            Client.searchMatchV1().match(_resumeIndexId, _documentId, new ArrayList<String>(), null, null, null, 0); ;
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(_resumeIndexId, _documentId, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(_resumeIndexId, _documentId, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(_jobIndexId, _documentId, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.searchMatchV1().match(_jobIndexId, _documentId, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }
}
