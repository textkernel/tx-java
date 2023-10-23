// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren;

import com.google.gson.Gson;
import com.sovren.models.Document;
import com.sovren.models.Location;
import com.sovren.models.SovrenDate;
import com.sovren.models.api.geocoding.GeocodeCredentials;
import com.sovren.models.api.geocoding.GeocodeProvider;
import com.sovren.models.api.parsing.ParseJobResponseValue;
import com.sovren.models.api.parsing.ParseOptions;
import com.sovren.models.api.parsing.ParseRequest;
import com.sovren.models.api.parsing.ParseResumeResponseValue;
import com.sovren.models.api.parsing.ProfessionsSettings;
import com.sovren.models.api.parsing.SkillsSettings;
import com.sovren.models.job.ParsedJob;
import com.sovren.models.resume.ParsedResume;
import com.sovren.models.resume.education.Degree;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestBase {
    protected static SovrenClient Client;
    protected static GeocodeCredentials GeocodeCredentials;

    protected static ParsedResume TestParsedResume;
    protected static ParsedResume TestParsedResumeV2;
    protected static ParsedResume TestParsedResumeWithAddress;
    protected static ParsedJob TestParsedJob;
    protected static ParsedJob TestParsedJobWithAddress;
    protected static ParsedJob TestParsedJobTech;
    
    private static class Credentials {
        public String AccountId;
        public String ServiceKey;
        public String GeocodeProviderKey;
    }

    private static class TestDataCenter extends DataCenter {
        public static TestDataCenter Local = new TestDataCenter("https://staging-rest.resumeparsing.com", "v10");
        protected TestDataCenter(String root, String version) {
            super(root, version, true);
        }
    }

    static {
        try {
            String jsonCreds = new String(Files.readAllBytes(Paths.get("./src/test/resources/credentials.json")), Charset.forName("utf8"));
            Credentials data = new Gson().fromJson(jsonCreds, Credentials.class);

            GeocodeCredentials = new GeocodeCredentials();
            GeocodeCredentials.Provider = GeocodeProvider.Google;
            GeocodeCredentials.ProviderKey = data.GeocodeProviderKey;

            Client = new SovrenClient(data.AccountId, data.ServiceKey, TestDataCenter.Local);

            ParseResumeResponseValue parseResumeResponseValue = Client.parseResume(new ParseRequest(TestData.Resume, null)).Value;
            TestParsedResume = parseResumeResponseValue.ResumeData;

            ParseOptions v2Options = new ParseOptions();
            v2Options.ProfessionsSettings = new ProfessionsSettings();
            v2Options.ProfessionsSettings.Normalize = true;
            v2Options.SkillsSettings = new SkillsSettings();
            v2Options.SkillsSettings.Normalize = true;
            v2Options.SkillsSettings.TaxonomyVersion = "V2";
            parseResumeResponseValue = Client.parseResume(new ParseRequest(TestData.Resume, v2Options)).Value;
            TestParsedResumeV2 = parseResumeResponseValue.ResumeData;

            parseResumeResponseValue = Client.parseResume(new ParseRequest(TestData.ResumeWithAddress, null)).Value;
            TestParsedResumeWithAddress = parseResumeResponseValue.ResumeData;

            ParseJobResponseValue parseJobResponseValue = Client.parseJob(new ParseRequest(TestData.JobOrder, null)).Value;
            TestParsedJob = parseJobResponseValue.JobData;

            parseJobResponseValue = Client.parseJob(new ParseRequest(TestData.JobOrderWithAddress, null)).Value;
            TestParsedJobWithAddress = parseJobResponseValue.JobData;

            parseJobResponseValue = Client.parseJob(new ParseRequest(TestData.JobOrderTech, null)).Value;
            TestParsedJobTech = parseJobResponseValue.JobData;
        }
        catch (Exception e) {
        }
    }

    public Document getTestFileAsDocument(String filename) throws IOException {
        return new Document("./src/test/resources/" + filename);
    }

    public static void delayForIndexSync() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e) { }
    }

    public static void cleanUpIndex(String indexName) {
        try {
            Client.deleteIndex(indexName);
        }
        catch (Exception e) { }
    }
    
    public void assertDateNotNull(SovrenDate date) {
        assertNotNull(date);
        assertNotNull(date.Date);
    }

    public void assertDegreeNotNull(Degree degree) {
        assertNotNull(degree);
        assertNotNull(degree.Name);
        assertNotNull(degree.Name.Raw);
        assertNotNull(degree.Name.Normalized);
        assertNotNull(degree.Type);
        assertNotNull(degree.Type);
    }

    public void assertLocationNotNull(Location loc, boolean checkStreetLevel, boolean checkGeo) {
        assertNotNull(loc);
        assertNotNull(loc.CountryCode);
        assertTrue(loc.Regions != null && loc.Regions.size() > 0);
        assertNotNull(loc.Municipality);

        if (checkStreetLevel)
        {
            assertNotNull(loc.PostalCode);
            assertTrue(loc.StreetAddressLines != null && loc.StreetAddressLines.size() > 0);
        }

        if (checkGeo)
        {
            assertNotNull(loc.GeoCoordinates);
            assertNotEquals(0, loc.GeoCoordinates.Latitude);
            assertNotEquals(0, loc.GeoCoordinates.Longitude);
        }
    }
    
    public void assertHasItems(List<?> list) {
        assertTrue(list != null && list.size() > 0);
    }
}