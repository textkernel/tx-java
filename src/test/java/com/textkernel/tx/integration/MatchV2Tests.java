package com.textkernel.tx.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.Document;
import com.textkernel.tx.models.api.indexes.IndexingOptionsGeneric;
import com.textkernel.tx.models.api.matchV2.MatchV2Environment;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteCandidatesField;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteJobsField;
import com.textkernel.tx.models.api.matchV2.autocomplete.AutocompleteResponse;
import com.textkernel.tx.models.api.matchV2.documents.DocumentSource;
import com.textkernel.tx.models.api.matchV2.documents.DocumentType;
import com.textkernel.tx.models.api.matchV2.querying.Options;
import com.textkernel.tx.models.api.matchV2.querying.SearchQuery;
import com.textkernel.tx.models.api.matchV2.querying.results.ResultItem;
import com.textkernel.tx.models.api.matchV2.querying.results.SearchResult;
import com.textkernel.tx.models.api.parsing.ParseOptions;
import com.textkernel.tx.models.api.parsing.ParseRequest;
import com.textkernel.tx.models.api.parsing.ParseResumeResponse;

public class MatchV2Tests  extends TestBase {

    private static final String _documentId = "1";

    @BeforeAll
    static void setup() throws TxException {
        // add a document to each index
        ClientDESv2.searchMatchV2().addJob(_documentId, TestParsedJobTech, null, null);
        ClientDESv2.searchMatchV2().addCandidate(_documentId, TestParsedResume, null, false, null);
        delayForIndexSync(5);
    }

    @AfterAll
    static void done() throws TxException {
        ClientDESv2.searchMatchV2().deleteCandidates(List.of(_documentId));
        ClientDESv2.searchMatchV2().deleteJobs(List.of(_documentId));
    }

    private static Stream<Arguments> provideSearchTerms() {
        return Stream.of(
                Arguments.of("Developer"),
                Arguments.of("VB6")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSearchTerms")
    public void testSearch(String validSearchTerm) {
        assertThrows(TxException.class, () -> {
            ClientDESv2.searchMatchV2().searchCandidates(null, null);
        });

        SearchQuery query = new SearchQuery();
        assertThrows(TxException.class, () -> {
            ClientDESv2.searchMatchV2().searchCandidates(query, null);
        });

        Options opts = new Options();
        query.QueryString = validSearchTerm;
        assertDoesNotThrow(() -> {
            SearchResult response = ClientDESv2.searchMatchV2().searchCandidates(query, opts).Value;
            assertNotEquals(0, response.MatchSize);
        });

        query.QueryString = "ThisIsATermThatIsntInTheDocument";
        assertDoesNotThrow(() -> {
            SearchResult response = ClientDESv2.searchMatchV2().searchCandidates(query, opts).Value;
            assertEquals(0, response.MatchSize);
        });
    }

    @Test
    public void testParseAndUpload() throws TxException, IOException {
        Document document = getTestFileAsDocument("resume.docx");
        String docId = UUID.randomUUID().toString();

        ParseOptions options = new ParseOptions();
        options.IndexingOptions = new IndexingOptionsGeneric(MatchV2Environment.PROD, docId, null, null);

        ParseResumeResponse parseResponse = ClientDESv2.parser().parseResume(new ParseRequest(document, options));
        assertTrue(parseResponse.Value.IndexingResponse.isSuccess());
        delayForIndexSync(5);

        Options opts = new Options();
        SearchQuery query = new SearchQuery();
        query.QueryString = "Developer";

        assertDoesNotThrow(() -> {
            SearchResult response = ClientDESv2.searchMatchV2().searchCandidates(query, opts).Value;
            assertNotEquals(0, response.MatchSize);
            boolean foundDocId = false;
            for (ResultItem item : response.ResultItems) {
                foundDocId |= item.DocID.equals(docId);
            }
            assertTrue(foundDocId);
        });

        ClientDESv2.searchMatchV2().deleteCandidates(List.of(docId));
        delayForIndexSync(5);
    }

    @Test
    public void testMatch() throws TxException {
        assertThrows(TxException.class, () -> {
            ClientDESv2.searchMatchV2().matchJobs(null, null, null);
        });

        Options options = new Options();
        assertThrows(TxException.class, () -> {
            ClientDESv2.searchMatchV2().matchJobs(null, options, null);
        });

        DocumentSource docSrc = new DocumentSource();
        docSrc.Id = "fake-doc-id";

        assertThrows(TxException.class, () -> {
            ClientDESv2.searchMatchV2().matchJobs(docSrc, options, null);
        });

        docSrc.Id = _documentId;
        docSrc.Type = DocumentType.vacancy;

        assertDoesNotThrow(() -> {
            ClientDESv2.searchMatchV2().matchJobs(docSrc, options, null);
        });

        assertDoesNotThrow(() -> {
            ClientDESv2.searchMatchV2().matchCandidates(docSrc, options, null);
        });
    }

    @Test
    public void testAutocomplete() throws TxException {
        assertDoesNotThrow(() -> {
            AutocompleteResponse response = ClientDESv2.searchMatchV2().autocompleteJobs(AutocompleteJobsField.Location, "York");
            assertHasItems(response.Value.Return);
        });

        assertDoesNotThrow(() -> {
            AutocompleteResponse response = ClientDESv2.searchMatchV2().autocompleteCandidates(AutocompleteCandidatesField.AllJobTitles, "Softwa");
            assertHasItems(response.Value.Return);
        });
    }
}
