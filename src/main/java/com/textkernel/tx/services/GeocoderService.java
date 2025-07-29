// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.services;

import com.textkernel.tx.EnvironmentSettings;
import com.textkernel.tx.TxClient;
import com.textkernel.tx.exceptions.TxException;
import com.textkernel.tx.http.HttpResponse;
import com.textkernel.tx.models.GeoCoordinates;
import com.textkernel.tx.models.api.geocoding.Address;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexJobRequest;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexJobResponse;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexJobResponseValue;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexResumeRequest;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexResumeResponse;
import com.textkernel.tx.models.api.geocoding.GeocodeAndIndexResumeResponseValue;
import com.textkernel.tx.models.api.geocoding.GeocodeCredentials;
import com.textkernel.tx.models.api.geocoding.GeocodeJobRequest;
import com.textkernel.tx.models.api.geocoding.GeocodeJobResponse;
import com.textkernel.tx.models.api.geocoding.GeocodeOptionsBase;
import com.textkernel.tx.models.api.geocoding.GeocodeProvider;
import com.textkernel.tx.models.api.geocoding.GeocodeResumeRequest;
import com.textkernel.tx.models.api.geocoding.GeocodeResumeResponse;
import com.textkernel.tx.models.api.indexes.IndexingOptionsGeneric;
import com.textkernel.tx.models.job.ParsedJob;
import com.textkernel.tx.models.resume.ParsedResume;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/** See {@link TxClient#geocoder()} */
public class GeocoderService extends ServiceBase {

    /**
     * Do not use this. See {@link TxClient#geocoder()}
     * @param httpClient The http client for API calls
     * @param settings environment settings
     */
    public GeocoderService(OkHttpClient httpClient, EnvironmentSettings settings) {
        super(httpClient, settings);
    }

    private GeocodeResumeResponse internalGeocode(ParsedResume resume, GeocodeCredentials geocodeCredentials, Address address) throws TxException {
        GeocodeResumeRequest request = new GeocodeResumeRequest();
        request.ResumeData = resume;
        request.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        request.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        request.PostalAddress = address;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeResume())
                .post(body)
                .build();

        HttpResponse<GeocodeResumeResponse> response = executeRequest(apiRequest, GeocodeResumeResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    private GeocodeJobResponse internalGeocode(ParsedJob job, GeocodeCredentials geocodeCredentials, Address address) throws TxException {
        GeocodeJobRequest request = new GeocodeJobRequest();
        request.JobData = job;
        request.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        request.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        request.PostalAddress = address;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeJob())
                .post(body)
                .build();

        HttpResponse<GeocodeJobResponse> response = executeRequest(apiRequest, GeocodeJobResponse.class, getBodyIfDebug(apiRequest));
        return response.getData();
    }

    /**
     * Uses the address in the resume (if present) to look up geocoordinates and add them into the ParsedResume object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume, GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocode(resume, geocodeCredentials, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeResumeResponse geocode(ParsedResume resume, Address address, GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocode(resume, geocodeCredentials, address);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job, GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocode(job, geocodeCredentials, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * job. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param address The address to use to retrieve geocoordinates
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeJobResponse geocode(ParsedJob job, Address address, GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocode(job, geocodeCredentials, address);
    }

    private GeocodeAndIndexResumeResponse internalGeocodeAndIndex(
            ParsedResume resume,
            GeocodeCredentials geocodeCredentials,
            IndexingOptionsGeneric indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws TxException {
        
        setEnvironment(indexingOptions);
        
        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        options.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexResumeRequest request = new GeocodeAndIndexResumeRequest();
        request.ResumeData = resume;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeAndIndexResume())
                .post(body)
                .build();

        HttpResponse<GeocodeAndIndexResumeResponse> response = executeRequest(apiRequest, GeocodeAndIndexResumeResponse.class, getBodyIfDebug(apiRequest));
        GeocodeAndIndexResumeResponseValue responseVal = response.getData().Value;

        if (!indexIfGeocodeFails && responseVal.GeocodeResponse != null && !responseVal.GeocodeResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.GeocodeResponse, response.getData().getInfo().TransactionId);
        }

        if (responseVal.IndexingResponse != null && !responseVal.IndexingResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.IndexingResponse, response.getData().getInfo().TransactionId);
        }

        return response.getData();
    }

    private GeocodeAndIndexJobResponse internalGeocodeAndIndex(
            ParsedJob job,
            GeocodeCredentials geocodeCredentials,
            IndexingOptionsGeneric indexingOptions,
            boolean indexIfGeocodeFails,
            Address address,
            GeoCoordinates coordinates) throws TxException {

        setEnvironment(indexingOptions);

        GeocodeOptionsBase options = new GeocodeOptionsBase();
        options.Provider = geocodeCredentials != null ? geocodeCredentials.Provider : GeocodeProvider.Google;
        options.ProviderKey = geocodeCredentials != null ? geocodeCredentials.ProviderKey : null;
        options.PostalAddress = address;
        options.GeoCoordinates = coordinates;

        GeocodeAndIndexJobRequest request = new GeocodeAndIndexJobRequest();
        request.JobData = job;
        request.GeocodeOptions = options;
        request.IndexingOptions = indexingOptions;
        request.IndexIfGeocodeFails = indexIfGeocodeFails;

        RequestBody body = createJsonBody(request);
        Request apiRequest = new Request.Builder()
                .url(_endpoints.geocodeAndIndexJob())
                .post(body)
                .build();

        HttpResponse<GeocodeAndIndexJobResponse> response = executeRequest(apiRequest, GeocodeAndIndexJobResponse.class, getBodyIfDebug(apiRequest));
        GeocodeAndIndexJobResponseValue responseVal = response.getData().Value;

        if (!indexIfGeocodeFails && responseVal.GeocodeResponse != null && !responseVal.GeocodeResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.GeocodeResponse, response.getData().getInfo().TransactionId);
        }

        if (responseVal.IndexingResponse != null && !responseVal.IndexingResponse.isSuccess()) {
            throw new TxException(getBodyIfDebug(apiRequest), response.getResponse(), responseVal.IndexingResponse, response.getData().getInfo().TransactionId);
        }

        return response.getData();
    }

    /**
     * Uses the address in the resume (if present) to look up geocoordinates and add them into the ParsedResume object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param resume The resume to geocode
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexingOptionsGeneric indexingOptions,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * resume. The address included in the parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexingOptionsGeneric indexingOptions,
            Address address,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed resume.
     * The coordinates will be inserted into your parsed resume, and the address included in the
     * parsed resume (if present) will not be modified.
     * @param resume The resume to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexResumeResponse geocodeAndIndex(
            ParsedResume resume,
            IndexingOptionsGeneric indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(resume, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, coordinates);
    }

    /**
     * Uses the address in the job (if present) to look up geocoordinates and add them into the ParsedJob object.
     * These coordinates are used by the AI Searching/Matching engine.
     * @param job The job to geocode
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexingOptionsGeneric indexingOptions,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, null);
    }

    /**
     * Use this if you would like to provide an address for geocoding instead of using the one in the parsed
     * rjobesume. The address included in the parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates (from the address) into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param address The address to use to retrieve geocoordinates
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexingOptionsGeneric indexingOptions,
            Address address,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, address, null);
    }

    /**
     * Use this if you already have latitude/longitude coordinates and simply wish to add them to your parsed job.
     * The coordinates will be inserted into your parsed job, and the address included in the
     * parsed job (if present) will not be modified.
     * @param job The job to insert the geocoordinates into
     * @param indexingOptions What index/document id to use to index the document after geocoding
     * @param coordinates The geocoordinates to use
     * @param indexIfGeocodeFails Indicates whether or not the document should still be added to the index if the geocode request fails.
     * @param geocodeCredentials - The credentials you want to use for geocoding (use {@code null} for built-in credentials)
     * @return The API response body
     * @throws TxException Thrown when an API error occurred
     */
    public GeocodeAndIndexJobResponse geocodeAndIndex(
            ParsedJob job,
            IndexingOptionsGeneric indexingOptions,
            GeoCoordinates coordinates,
            boolean indexIfGeocodeFails,
            GeocodeCredentials geocodeCredentials) throws TxException {
        return internalGeocodeAndIndex(job, geocodeCredentials, indexingOptions, indexIfGeocodeFails, null, coordinates);
    }
}
