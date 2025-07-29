// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.integration;

import com.textkernel.tx.TestBase;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.models.api.geocoding.Address;
import com.textkernel.tx.models.api.indexes.IndexingOptionsGeneric;
import com.textkernel.tx.models.matching.IndexType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeocodingTests extends TestBase {
    @Test
    public void testResumeNoAddress() {
        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedResume, GeocodeCredentials);
            });

        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedResume, null);
            });

        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedResume, new Address(), GeocodeCredentials);
            });

        assertThrows(TxException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocoder().geocode(TestParsedResume, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocoder().geocode(TestParsedResume, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Client.geocoder().geocode(TestParsedResumeWithAddress, GeocodeCredentials);
        });
    }

    @Test
    public void testJobNoAddress() {
        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedJob, GeocodeCredentials);
            });

        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedJob, null, GeocodeCredentials);
            });

        assertThrows(TxException.class, () -> {
            Client.geocoder().geocode(TestParsedJob, new Address(), GeocodeCredentials);
            });

        assertThrows(TxException.class, () -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            Client.geocoder().geocode(TestParsedJob, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Address addr = new Address();
            addr.CountryCode = "US";
            addr.Municipality = "Dallas";
            addr.Region = "TX";
            Client.geocoder().geocode(TestParsedJob, addr, GeocodeCredentials);
        });

        assertDoesNotThrow(() -> {
            Client.geocoder().geocode(TestParsedJobWithAddress, GeocodeCredentials);
        });
    }

    @Test
    public void testResumeGeocodeIndex() throws TxException {
        String indexId = "SDK-resume-testResumeGeocodeIndex";
        String documentId = "1";

        try {
            Client.searchMatchV1().createIndex(IndexType.Resume, indexId);

            // missing indexing options
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedResumeWithAddress, null, false, GeocodeCredentials);
            });

            // empty indexing options
            IndexingOptionsGeneric indexingOptions = new IndexingOptionsGeneric("", "", null);
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedResume, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.geocoder().geocodeAndIndex(TestParsedResumeWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.searchMatchV1().getResume(indexId, documentId);
            });
        }
        finally {
            cleanUpIndex(indexId);
        }
    }

    @Test
    public void testJobGeocodeIndex() throws TxException  {
        String indexId = "SDK-job-testJobGeocodeIndex";
        String documentId = "1";

        try {
            Client.searchMatchV1().createIndex(IndexType.Job, indexId);

            // missing indexing options
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedJobWithAddress, null, false, GeocodeCredentials);
            });

            // empty indexing options
            IndexingOptionsGeneric indexingOptions = new IndexingOptionsGeneric("", "", null);
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            // missing documentid
            indexingOptions.IndexId = indexId;
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            indexingOptions.DocumentId = documentId;

            // not enough data points to index
            assertThrows(TxException.class, () -> {
                Client.geocoder().geocodeAndIndex(TestParsedJob, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.geocoder().geocodeAndIndex(TestParsedJobWithAddress, indexingOptions, false, GeocodeCredentials);
            });

            assertDoesNotThrow(() -> {
                Client.searchMatchV1().getJob(indexId, documentId);
            });
        }
        finally {
            cleanUpIndex(indexId);
        }
    }
}
