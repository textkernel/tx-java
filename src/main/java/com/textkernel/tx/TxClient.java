// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx;

import com.textkernel.tx.exceptions.*;
import com.textkernel.tx.services.AccountService;
import com.textkernel.tx.services.FormatterService;
import com.textkernel.tx.services.GeocoderService;
import com.textkernel.tx.services.MatchV2Service;
import com.textkernel.tx.services.ParserService;
import com.textkernel.tx.services.SearchMatchService;
import com.textkernel.tx.services.SkillsIntelligenceService;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * The SDK client to perform Tx API calls.
 */
public class TxClient {

    private static final String _sdkVersion;

    private AccountService _accountService;
    private FormatterService _formatterService;
    private GeocoderService _geocoderService;
    private MatchV2Service _searchMatchV2Service;
    private ParserService _parserService;
    private SearchMatchService _searchMatchV1Service;
    private SkillsIntelligenceService _skillsIntelligenceService;

    /**
     * @return access all endpoints/methods for Accounts
     */
    public AccountService account() { return _accountService; }

    /**
     * @return access all endpoints/methods for the Resumer Formatter
     */
    public FormatterService formatter() { return _formatterService; }

    /**
     * @return access all endpoints/methods for the Geocoder
     */
    public GeocoderService geocoder() { return _geocoderService; }

    /**
     * @return access all endpoints/methods for Search &amp; Match V2
     */
    public MatchV2Service searchMatchV2() { return _searchMatchV2Service; }

    /**
     * @return access all endpoints/methods for the Resume &amp; Job Parsers
     */
    public ParserService parser() { return _parserService; }

    /**
     * @return access all endpoints/methods for Skills Intelligence
     */
    public SkillsIntelligenceService skillsIntelligence() { return _skillsIntelligenceService; }

    /**
     * @return access all endpoints/methods for Search &amp; Match V1
     */
    public SearchMatchService searchMatchV1() { return _searchMatchV1Service; }
    
    static {
        _sdkVersion = TxClient.class.getPackage().getImplementationVersion();
    }
    
    /** 
     * Set to {@code true} for debugging API errors. It will show the full JSON request body in {@link TxException#RequestBody}
     * <br><b>NOTE: do not set this to {@code true} in your production system, as it increases the memory footprint</b>
     */
    public static boolean ShowFullRequestBodyInExceptions = false;

    /**
     * Create an SDK client to perform Tx API calls
     * @param settings - The settings for this client
     * @throws IllegalArgumentException if the accountId, serviceKey, or dataCenter are null/empty
     */
    public TxClient(TxClientSettings settings) {
        this(settings, 30);
    }

    /**
     * Create an SDK client to perform Tx API calls
     * @param settings - The settings for this client
     * @param httpTimeoutSecs - Optional override for the OkHttp client read timeout (write and connect are 10 seconds, read is 30 seconds by default)
     * @throws IllegalArgumentException if the accountId, serviceKey, or dataCenter are null/empty
     */
    public TxClient(TxClientSettings settings, long httpTimeoutSecs) {
        
        if (settings == null) {
            throw new IllegalArgumentException("'settings' cannot be null");
        }

        if (settings.AccountId == null || settings.AccountId.length() == 0) {
            throw new IllegalArgumentException("'accountId' must have a valid value");
        }

        if (settings.ServiceKey == null || settings.ServiceKey.length() == 0) {
            throw new IllegalArgumentException("'serviceKey' must have a valid value");
        }

        if (settings.DataCenter == null) {
            throw new IllegalArgumentException("'dataCenter' must not be null");
        }

        if (httpTimeoutSecs <= 0) {
            throw new IllegalArgumentException("'httpTimeoutSecs' must be greater than 0");
        }

        final String trackingTagsHeaderValue;//must be final to be passed into the interceptor below

        if (settings.TrackingTags != null && settings.TrackingTags.size() > 0) {
            trackingTagsHeaderValue = String.join(", ", settings.TrackingTags);
            if (trackingTagsHeaderValue.length() >= 75) {//API allows 100, but just to be safe, this should be way more than enough
                throw new IllegalArgumentException("'trackingTags' has too many values or the values are too long");
            }
        }
        else {
            trackingTagsHeaderValue = null;
        }

        //do not validate credentials here, as this could lead to calling GetAccount for every parse call, an AUP violation
        OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    //set all of these headers on every request
                    okhttp3.Request.Builder builder = original.newBuilder();
                    builder.header("Tx-AccountId", settings.AccountId);
                    builder.header("Tx-ServiceKey", settings.ServiceKey);
                    builder.header("User-Agent", "tx-java-" + _sdkVersion);

                    if (trackingTagsHeaderValue != null && !trackingTagsHeaderValue.isEmpty()){
                        builder.header("Tx-TrackingTag", trackingTagsHeaderValue);
                    }

                    Request request = builder.build();
                    return chain.proceed(request);
                }
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(httpTimeoutSecs, TimeUnit.SECONDS)
            .build();

        _accountService = new AccountService(httpClient, settings);
        _formatterService = new FormatterService(httpClient, settings);
        _geocoderService = new GeocoderService(httpClient, settings);
        _parserService = new ParserService(httpClient, settings);
        _searchMatchV1Service = new SearchMatchService(httpClient, settings);
        _searchMatchV2Service = new MatchV2Service(httpClient, settings);
        _skillsIntelligenceService = new SkillsIntelligenceService(httpClient, settings);
    }
}