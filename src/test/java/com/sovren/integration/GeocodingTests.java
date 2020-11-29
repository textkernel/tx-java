package com.sovren.integration;

import com.sovren.TestBase;
import com.sovren.exceptions.SovrenException;
import com.sovren.models.api.geocoding.Address;
import com.sovren.models.api.indexes.IndexSingleDocumentInfo;
import com.sovren.models.matching.IndexType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeocodingTests extends TestBase {
    @Test
    public void testResumeNoAddress() {
        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedResume);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedResume, null);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedResume, new Address());
            });

        assertThrows(SovrenException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocode(TestParsedResume, addr);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocode(TestParsedResume, addr);
        });

        assertDoesNotThrow(() -> {
            Client.geocode(TestParsedResumeWithAddress);
        });
    }

    @Test
    public void testJobNoAddress() {
        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob, null);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob, new Address());
            });

        assertThrows(SovrenException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocode(TestParsedJob, addr);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocode(TestParsedJob, addr);
        });

        assertDoesNotThrow(() -> {
            Client.geocode(TestParsedJobWithAddress);
        });
    }

    @Test
    public void testResumeGeocodeIndex() throws SovrenException {
        String indexId = "SDK-resume-testResumeGeocodeIndex";
        String documentId = "1";

        try {
            Client.createIndex(IndexType.Resume, indexId);

            // missing indexing options
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, null, false);
            });

            // empty indexing options
            IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResume, indexingOptions, false);
            });

            assertDoesNotThrow(() -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false);
            });

            assertDoesNotThrow(() -> {
                Client.getResume(indexId, documentId);
            });
        }
        finally {
            cleanUpIndex(indexId);
        }
    }

    @Test
    public void testJobGeocodeIndex() throws SovrenException  {
        String indexId = "SDK-job-testJobGeocodeIndex";
        String documentId = "1";

        try {
            Client.createIndex(IndexType.Job, indexId);

            // missing indexing options
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, null, false);
            });

            // empty indexing options
            IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJob, indexingOptions, false);
            });

            assertDoesNotThrow(() -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false);
            });

            assertDoesNotThrow(() -> {
                Client.getJob(indexId, documentId);
            });
        }
        finally {
            cleanUpIndex(indexId);
        }
    }
}
