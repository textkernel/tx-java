// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
            Client.geocode(TestParsedResume, GeocodeCredentials);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedResume, null);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedResume, new Address(), GeocodeCredentials);
            });

        assertThrows(SovrenException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocode(TestParsedResume, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocode(TestParsedResume, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Client.geocode(TestParsedResumeWithAddress, GeocodeCredentials);
        });
    }

    @Test
    public void testJobNoAddress() {
        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob, GeocodeCredentials);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob, null, GeocodeCredentials);
            });

        assertThrows(SovrenException.class, () -> {
            Client.geocode(TestParsedJob, new Address(), GeocodeCredentials);
            });

        assertThrows(SovrenException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocode(TestParsedJob, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocode(TestParsedJob, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Client.geocode(TestParsedJobWithAddress, GeocodeCredentials);
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
                Client.geocodeAndIndex(TestParsedResumeWithAddress, null, false, GeocodeCredentials);
            });

            // empty indexing options
            IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedResume, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
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
                Client.geocodeAndIndex(TestParsedJobWithAddress, null, false, GeocodeCredentials);
            });

            // empty indexing options
            IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(SovrenException.class, () -> {
                Client.geocodeAndIndex(TestParsedJob, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
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
