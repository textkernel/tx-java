// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TxErrorCodes;
import com.textkernel.tx.TestBase;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.api.indexes.UserDefinedTagsMethod;
import com.textkernel.tx.models.api.matching.SearchResponseValue;
import com.textkernel.tx.models.api.matching.request.FilterCriteria;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.matching.Index;
import com.textkernel.tx.models.matching.IndexType;
import com.textkernel.tx.models.resume.ParsedResume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IndexTests extends TestBase {
    private static final String resumeIndexId = "Java-SDK-IntegrationTest-Resume";
    private static final String jobIndexId = "Java-SDK-IntegrationTest-Job";

    private static String getIndexName(IndexType indexType) {
        return indexType == IndexType.Resume ? resumeIndexId : jobIndexId;
    }

    private boolean doesIndexExist(String indexName) {
        List<Index> indexes = null;

        try {
            indexes = Client.getAllIndexes().Value;
        }
        catch (TxException e) {
            return false;
        }

        // check if any of the indexes found share the specified index name
        return indexes.stream().anyMatch((i) -> i.Name.equalsIgnoreCase(indexName));
    }

    private static Stream<Arguments> provideBadIndexNames() {
        return Stream.of(
            Arguments.of("invalid=index"),
            Arguments.of(" "));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("provideBadIndexNames")
    public void testCreateIndexBadInput(String indexName) {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.createIndex(IndexType.Job, indexName);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Client.createIndex(IndexType.Resume, indexName);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("provideBadIndexNames")
    public void testdeleteIndexBadInput(String indexName) {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.deleteIndex(indexName);
        });
    }

    @ParameterizedTest
    @EnumSource(IndexType.class)
    public void testIndexLifecycle(IndexType indexType) {
        String indexName = getIndexName(indexType);

        try {
            // verify index doesn't exist
            Client.getAllIndexes();
            assertFalse(doesIndexExist(indexName));

            // create index
            assertDoesNotThrow(() -> {
                Client.createIndex(indexType, indexName);
            });

            delayForIndexSync();

            // create index already exists
            TxException txException = assertThrows(TxException.class, () -> {
                Client.createIndex(indexType, indexName);
            });

            assertEquals(TxErrorCodes.DuplicateAsset, txException.TxErrorCode);

            // verify index created
            assertTrue(doesIndexExist(indexName));

            // delete the index
            assertDoesNotThrow(() -> {
                Client.deleteIndex(indexName);
            });

            delayForIndexSync();

            // verify index doesn't exist
            assertFalse(doesIndexExist(indexName));

            // try to delete an index that doesn't exist
            txException = assertThrows(TxException.class, () -> {
                Client.deleteIndex(indexName);
            });
            assertEquals(TxErrorCodes.DataNotFound, txException.TxErrorCode);
        } catch (TxException e) {
        } finally {
            // clean up assets in case the test failed someone before the delete calls were executed
            cleanUpIndex(indexName);
            delayForIndexSync();
        }
    }

    @Test
    public void testResumeLifeCycle() throws TxException {
        final String documentId = "1";
        try {
            // verify can't retrieve a document that doesn't exist
            TxException txException = assertThrows(TxException.class, () -> {
                Client.getResume(resumeIndexId, documentId);
            });

            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // verify can't add document to an index that doesn't exist
            txException = assertThrows(TxException.class, () -> {
                Client.indexDocument(TestParsedResume, resumeIndexId, documentId, null);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // create the index
            Client.createIndex(IndexType.Resume, resumeIndexId);
            delayForIndexSync();

            // verify document still doesn't exist
            txException = assertThrows(TxException.class, () -> {
                    Client.getResume(resumeIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // add resume to index
            Client.indexDocument(TestParsedResume, resumeIndexId, documentId, null);
            delayForIndexSync();

            // confirm you can now retrieve the resume
            Client.getResume(resumeIndexId, documentId);

            // add v2 skills resume to index
            Client.indexDocument(TestParsedResumeV2, resumeIndexId, documentId, null);
            delayForIndexSync();

            // confirm you can now retrieve the resume
            Client.getResume(resumeIndexId, documentId);

            // confirm the resume shows up in searches
            List<String> indexesToQuery = new ArrayList<>();
            indexesToQuery.add(resumeIndexId);

            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.DocumentIds = new ArrayList<>();
            filterCriteria.DocumentIds.add(documentId);

            SearchResponseValue searchResponse = Client.search(indexesToQuery, filterCriteria, null, null).Value;
            assertEquals(1, searchResponse.TotalCount);
            assertEquals(1, searchResponse.CurrentCount);
            assertEquals(documentId, searchResponse.Matches.get(0).Id);

            // update the resume
            List<String> userDefinedTags = new ArrayList<>();
            userDefinedTags.add("userDefinedTag1");
            Client.updateResumeUserDefinedTags(resumeIndexId, documentId,
                userDefinedTags, UserDefinedTagsMethod.Overwrite);

            delayForIndexSync();

            // verify those updates have taken effect
            filterCriteria.UserDefinedTags = userDefinedTags;
            searchResponse = Client.search(indexesToQuery, filterCriteria, null, null).Value;
            assertEquals(1, searchResponse.TotalCount);
            assertEquals(1, searchResponse.CurrentCount);
            assertEquals(documentId, searchResponse.Matches.get(0).Id);

            // confirm you can retrieve the tags
            ParsedResume resume = Client.getResume(resumeIndexId, documentId).Value;
            assertEquals(1, resume.UserDefinedTags.size());
            assertEquals(userDefinedTags.get(0), resume.UserDefinedTags.get(0));

            // delete the document
            Client.deleteDocument(resumeIndexId, documentId);
            delayForIndexSync();

            // verify can't retrieve a document that doesn't exist
            txException = assertThrows(TxException.class, () -> {
                    Client.getResume(resumeIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, txException.TxErrorCode);

            txException = assertThrows(TxException.class, () -> {
                    Client.deleteDocument(resumeIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, txException.TxErrorCode);

            Client.deleteIndex(resumeIndexId);
            delayForIndexSync();

            txException = assertThrows(TxException.class, () -> {
                    Client.deleteDocument(resumeIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);
        }
        catch(TxException e) { throw e; }
        finally {
            cleanUpIndex(resumeIndexId);
        }
    }

    @Test
    public void testJobLifeCycle() {
        final String documentId = "1";
        try {
            // verify can't retrieve a document that doesn't exist
            assertThrows(TxException.class, () -> {
                    Client.getJob(jobIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // verify can't add document to an index that doesn't exist
            assertThrows(TxException.class, () -> {
                    Client.indexDocument(TestParsedJob, jobIndexId, documentId, null);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // create the index
            Client.createIndex(IndexType.Job, jobIndexId);
            delayForIndexSync();

            // verify document still doesn't exist
            assertThrows(TxException.class, () -> {
                    Client.getJob(jobIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            // add resume to index
            Client.indexDocument(TestParsedJob, jobIndexId, documentId, null);
            delayForIndexSync();

            // confirm you can now retrieve the resume
            Client.getJob(jobIndexId, documentId);

            // confirm the resume shows up in searches
            List<String> indexesToQuery = new ArrayList<>();
            indexesToQuery.add(jobIndexId);

            FilterCriteria filterCriteria = new FilterCriteria();
            filterCriteria.DocumentIds = new ArrayList<>();
            filterCriteria.DocumentIds.add(documentId);

            SearchResponseValue searchResponse = Client.search(indexesToQuery, filterCriteria, null, null).Value;
            assertEquals(1, searchResponse.TotalCount);
            assertEquals(1, searchResponse.CurrentCount);
            assertEquals(documentId, searchResponse.Matches.get(0).Id);

            // update the resume
            List<String> userDefinedTags = new ArrayList<>();
            userDefinedTags.add("userDefinedTag1");
            Client.updateJobUserDefinedTags(jobIndexId, documentId,
                userDefinedTags, UserDefinedTagsMethod.Overwrite);

            delayForIndexSync();

            // verify those updates have taken effect
            filterCriteria.UserDefinedTags = userDefinedTags;
            searchResponse = Client.search(indexesToQuery, filterCriteria, null, null).Value;
            assertEquals(1, searchResponse.TotalCount);
            assertEquals(1, searchResponse.CurrentCount);
            assertEquals(documentId, searchResponse.Matches.get(0).Id);

            // confirm you can retrieve the tags
            ParsedJob job = Client.getJob(jobIndexId, documentId).Value;
            assertEquals(1, job.UserDefinedTags.size());
            assertEquals(userDefinedTags.get(0), job.UserDefinedTags.get(0));

            // delete the document
            Client.deleteDocument(jobIndexId, documentId);
            delayForIndexSync();

            // verify can't retrieve a document that doesn't exist
            assertThrows(TxException.class, () -> {
                    Client.getJob(jobIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            assertThrows(TxException.class, () -> {
                    Client.deleteDocument(jobIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);

            Client.deleteIndex(jobIndexId);
            delayForIndexSync();

            assertThrows(TxException.class, () -> {
                    Client.deleteDocument(jobIndexId, documentId);
            });
            assertEquals(TxErrorCodes.DataNotFound, TxErrorCodes.DataNotFound);
        }
        catch (TxException e) {}
        finally {
            cleanUpIndex(jobIndexId);
        }
    }
}
