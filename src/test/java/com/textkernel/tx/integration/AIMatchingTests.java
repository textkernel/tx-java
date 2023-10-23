// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;

import com.textkernel.tx.exceptions.SovrenException;
import com.textkernel.tx.models.api.matching.MatchResponseValue;
import com.textkernel.tx.models.api.matching.SearchResponseValue;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import com.textkernel.tx.models.api.matching.request.PaginationSettings;
import com.textkernel.tx.models.api.matching.ui.GenerateUIResponse;
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
    static void setup() throws SovrenException {
        // create indexes
        Client.createIndex(IndexType.Job, _jobIndexId);
        Client.createIndex(IndexType.Resume, _resumeIndexId);
        delayForIndexSync();

        // add a document to each index
        Client.indexDocument(TestParsedJobTech, _jobIndexId, _documentId, null);
        Client.indexDocument(TestParsedResume, _resumeIndexId, _documentId, null);
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
        assertThrows(SovrenException.class, () -> {
            Client.search(null, null, null, null);
        });

        List<String> indexesToQuery = new ArrayList<>();
        indexesToQuery.add(indexId);
        assertThrows(SovrenException.class, () -> {
            Client.search(indexesToQuery, null, null, null);
        });

        FilterCriteria filterCritera = new FilterCriteria();
        assertThrows(SovrenException.class, () -> {
            Client.search(null, filterCritera, null, null);
        });

        assertThrows(SovrenException.class, () -> {
            List<String> indexes = new ArrayList<>();
            indexes.add("fake-index-id");
            Client.search(indexes, filterCritera, null, null);
        });

        assertThrows(SovrenException.class, () -> {
            Client.search(indexesToQuery, filterCritera, null, null);
        });

        filterCritera.SearchExpression = validSearchTerm;
        assertDoesNotThrow(() -> {
            PaginationSettings pageSettings = new PaginationSettings();
            pageSettings.Take = 10;
            SearchResponseValue response = Client.search(indexesToQuery, filterCritera, null, pageSettings).Value;
            assertEquals(1, response.CurrentCount);
            assertEquals(1, response.TotalCount);
        });

        filterCritera.SearchExpression = "ThisIsATermThatIsntInTheDocument";
        assertDoesNotThrow(() -> {
            SearchResponseValue response = Client.search(indexesToQuery, filterCritera, null, null).Value;
            assertEquals(0, response.CurrentCount);
            assertEquals(0, response.TotalCount);
        });
    }
    
    @Test
    public void testMatchJob()
    {
        assertThrows(SovrenException.class, () -> {
            Client.match(TestParsedJobTech, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(TestParsedJobTech, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(TestParsedJobTech, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }

    @Test
    public void testMatchResume() {
        assertThrows(SovrenException.class, () -> {
            Client.match(TestParsedResume, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(TestParsedResume, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(TestParsedResume, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }

    @Test
    public void testMatchIndexedDocument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.match("", null, null, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match(null, _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match("", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match(" ", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match(_resumeIndexId, null, _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match(_resumeIndexId, "", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.match(_resumeIndexId, " ", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(SovrenException.class, () -> {
            Client.match(_resumeIndexId, _documentId, null, null, null, null, 0); ;
        });

        assertThrows(SovrenException.class, () -> {
            Client.match(_resumeIndexId, _documentId, new ArrayList<String>(), null, null, null, 0); ;
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(_resumeIndexId, _documentId, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(_resumeIndexId, _documentId, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(_jobIndexId, _documentId, _resumesIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });

        assertDoesNotThrow(() -> {
            MatchResponseValue matchResponse = Client.match(_jobIndexId, _documentId, _jobsIndexes, null, null, null, 0).Value;
            assertEquals(1, matchResponse.CurrentCount);
            assertEquals(1, matchResponse.TotalCount);
            assertEquals(1, matchResponse.Matches.size());
        });
    }

    @Test
    public void TestMatchUISearch() {
        AtomicReference<GenerateUIResponse> uiResponse = new AtomicReference<>();

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).search(null, null, null, null);
        });

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).search(new ArrayList<String>(), null, null, null);
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).search(_resumesIndexes, null, null, null));
        });

        assertTrue(DoesURLExist(uiResponse.get().url));
    }

    @Test
    public void testMatchUIMatchJob() {
        AtomicReference<GenerateUIResponse> uiResponse = new AtomicReference<>();

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).match(TestParsedJobTech, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(TestParsedJobTech, _resumesIndexes, null, null, null, 0));
        });

        assertTrue(DoesURLExist(uiResponse.get().url));

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(TestParsedJobTech, _jobsIndexes, null, null, null, 0));
        });

        assertTrue(DoesURLExist(uiResponse.get().url));
    }

    @Test
    public void testMatchUIMatchResume() {
        AtomicReference<GenerateUIResponse> uiResponse = new AtomicReference<>();

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).match(TestParsedResume, null, null, null, null, 0);
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(TestParsedResume, _resumesIndexes, null, null, null, 0));
            assertTrue(DoesURLExist(uiResponse.get().url));
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(TestParsedResume, _jobsIndexes, null, null, null, 0));
            assertTrue(DoesURLExist(uiResponse.get().url));
        });
    }

    @Test
    public void TestMatchUIMatchIndexedDocument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match("", null, null, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match(null, _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match("", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match(" ", _documentId, _resumesIndexes, null, null, null, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match(_resumeIndexId, null, _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match(_resumeIndexId, "", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.ui(null).match(_resumeIndexId, " ", _resumesIndexes, null, null, null, 0); ;
        });

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).match(_resumeIndexId, _documentId, null, null, null, null, 0); ;
        });

        assertThrows(SovrenException.class, () -> {
            Client.ui(null).match(_resumeIndexId, _documentId, new ArrayList<String>(), null, null, null, 0); ;
        });

        AtomicReference<GenerateUIResponse> uiResponse = new AtomicReference<>();

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(_resumeIndexId, _documentId, _resumesIndexes, null, null, null, 0)); ;
            assertTrue(DoesURLExist(uiResponse.get().url));
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(_resumeIndexId, _documentId, _jobsIndexes, null, null, null, 0)); ;
            assertTrue(DoesURLExist(uiResponse.get().url));
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(_jobIndexId, _documentId, _resumesIndexes, null, null, null, 0)); ;
            assertTrue(DoesURLExist(uiResponse.get().url));
        });

        assertDoesNotThrow(() -> {
            uiResponse.set(Client.ui(null).match(_jobIndexId, _documentId, _jobsIndexes, null, null, null, 0)); ;
            assertTrue(DoesURLExist(uiResponse.get().url));
        });
    }

    private boolean DoesURLExist(String url) {
        Request apiRequest = new Request.Builder()
                .url(url)
                .build();
        try {
            return new OkHttpClient().newCall(apiRequest).execute().isSuccessful();
        }
        catch (Exception e){
            return false;
        }
    }
}
