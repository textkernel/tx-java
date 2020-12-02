package com.sovren.integration;

import com.google.gson.JsonParseException;
import com.sovren.TestBase;
import com.sovren.TestData;
import com.sovren.exceptions.*;
import com.sovren.models.Document;
import com.sovren.models.api.geocoding.GeocodeOptions;
import com.sovren.models.api.geocoding.GeocodeProvider;
import com.sovren.models.api.indexes.IndexSingleDocumentInfo;
import com.sovren.models.api.parsing.*;
import com.sovren.models.api.parsing.ParseJobResponseValue;

import static org.junit.jupiter.api.Assertions.*;

import com.sovren.models.job.ParsedJob;
import com.sovren.models.matching.IndexType;
import com.sovren.models.resume.ParsedResume;
import com.sovren.models.resume.metadata.ResumeQualityLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;


public class ParsingTests extends TestBase {
    private static Stream<Arguments> provideBadDocuments() {
        return Stream.of(
                Arguments.of(null, IllegalArgumentException.class),
                Arguments.of(new Document(new byte[1], LocalDate.now()), SovrenException.class));
    }

    @ParameterizedTest
    @MethodSource("provideBadDocuments")
    public <T extends Exception> void testParseBadInput(Document doc, Class<T> classOfExpectedException) {
        assertThrows(classOfExpectedException, () -> {
            ParseRequest request = new ParseRequest(doc, null);
            Client.parseResume(request);
        });

        assertThrows(classOfExpectedException, () -> {
            ParseRequest request = new ParseRequest(doc, null);
            Client.parseJob(request);
        });
    }

    @Test
    public void testLargeDocumentParse() {
        SovrenException e = assertThrows(SovrenException.class, () -> {
                Client.parseResume(new ParseRequest(new Document(new byte[32_000_000], LocalDate.now()), null));
            });

        assertEquals(e.getMessage(), "Request body was too large.");
    }

    @Test
    public void verifyParseResumeSuccess() throws Exception
    {
        AtomicReference<ParseResumeResponseValue> responseAtomic = new AtomicReference<>();

        assertDoesNotThrow(() -> {
            ParseOptions opts = new ParseOptions();
            opts.OutputCandidateImage = true;
            opts.OutputHtml = true;
            opts.OutputPdf = true;
            opts.OutputRtf = true;
            responseAtomic.set(Client.parseResume(new ParseRequest(TestData.Resume, opts)).Value);
        });

        ParseResumeResponseValue response = responseAtomic.get();

        assertTrue(response.ParsingResponse.isSuccess());
        assertNotNull(response.ResumeData);
        assertNotNull(response.Conversions);
        assertNotNull(response.Conversions.HTML);
        assertNotNull(response.Conversions.PDF);
        assertNotNull(response.Conversions.RTF);
        assertNotNull(response.ConversionMetadata);
        assertNotNull(response.ParsingMetadata);
        assertNotNull(response.RedactedResumeData);
    }
    
    @Test
    public void verifyParseResumeGeocodeError() throws Exception
    {
        SovrenGeocodeResumeException e = 
                assertThrows(SovrenGeocodeResumeException.class, () -> {
                    ParseOptions opts = new ParseOptions();
                    opts.GeocodeOptions = new GeocodeOptions();
                    opts.GeocodeOptions.IncludeGeocoding = true;
                    opts.GeocodeOptions.Provider= GeocodeProvider.Google;
                    
                    ParseResumeResponseValue r = Client.parseResume(new ParseRequest(TestData.Resume, opts)).Value;
                });
        
        assertTrue(!e.Response.Value.GeocodeResponse.isSuccess());
        
        assertTrue(e.Response.Value.ParsingResponse.isSuccess());
        assertNotNull(e.Response.Value.ResumeData);
        assertNotNull(e.Response.Value.ConversionMetadata);
        assertNotNull(e.Response.Value.ParsingMetadata);
        assertNotNull(e.Response.Value.RedactedResumeData);
    }
    
    @Test
    public void verifyparseJobSuccess() throws Exception
    {
        AtomicReference<ParseJobResponseValue> responseAtomic = new AtomicReference<>();

        assertDoesNotThrow(() -> {
            ParseOptions opts = new ParseOptions();
            opts.OutputCandidateImage = true;
            opts.OutputHtml = true;
            opts.OutputPdf = true;
            opts.OutputRtf = true;
            responseAtomic.set(Client.parseJob(new ParseRequest(TestData.JobOrder, opts)).Value);
        });

        ParseJobResponseValue response = responseAtomic.get();
        
        assertTrue(response.ParsingResponse.isSuccess());
        assertNotNull(response.JobData);
        assertNotNull(response.Conversions);
        assertNotNull(response.Conversions.HTML);
        assertNotNull(response.Conversions.PDF);
        assertNotNull(response.Conversions.RTF);
        assertNotNull(response.ConversionMetadata);
        assertNotNull(response.ConversionMetadata);
        assertNotNull(response.ParsingMetadata);
    }
    
    @Test
    public void testParseResumeGeocodeIndex() throws SovrenException {
        String indexId = "SDK-testParseResumeGeocodeIndex";
        String documentId = "1";
    
        GeocodeOptions geocodeOptions = new GeocodeOptions();
        geocodeOptions.IncludeGeocoding = true;
    
        IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
        indexingOptions.IndexId = indexId;
    
        // since there isn't an address this will throw an exception
        assertThrows(SovrenGeocodeResumeException.class, () -> {
            ParseRequest request = new ParseRequest(TestData.Resume, null);
            request.GeocodeOptions = geocodeOptions;
            request.IndexingOptions = indexingOptions;
            Client.parseResume(request);
        });
    
    
        // confirm you can geocode but indexing fails
        assertThrows(SovrenIndexResumeException.class, () -> {
            ParseRequest request = new ParseRequest(TestData.ResumeWithAddress, null);
            request.GeocodeOptions = geocodeOptions;
            request.IndexingOptions = indexingOptions;
            Client.parseResume(request);
        });
    
        try {
            // set the document id and create the index
            indexingOptions.DocumentId = documentId;
            Client.createIndex(IndexType.Resume, indexId);
            delayForIndexSync();
    
            // confirm you can parse/geocode/index
            assertDoesNotThrow(() -> {
                ParseRequest request = new ParseRequest(TestData.ResumeWithAddress, null);
                request.GeocodeOptions = geocodeOptions;
                request.IndexingOptions = indexingOptions;
                Client.parseResume(request);
            });
    
            // verify the resume exists in the index
            delayForIndexSync();
            Client.getResume(indexId, documentId);
        }
        catch (SovrenException e) { throw e; }
        finally {
            cleanUpIndex(indexId);
        }
    }

    @Test
    public void testResumeToFromJson() throws IOException {
        String tempFile1 = UUID.randomUUID().toString();
        String tempFile2 = UUID.randomUUID().toString();

        try {
            ParseResumeResponse response = Client.parseResume(new ParseRequest(TestData.Resume, null));

            String unformatted = response.Value.ResumeData.toJson(false);
            String formatted = response.Value.ResumeData.toJson(true);

            response.Value.ResumeData.toFile(tempFile1, true);
            response.Value.ResumeData.toFile(tempFile2, false);

            ParsedResume resume1 = ParsedResume.fromJson(unformatted);
            ParsedResume resume2 = ParsedResume.fromJson(formatted);

            assertNotNull(resume1);
            assertNotNull(resume2);

            assertNotNull(resume1.ContactInformation.CandidateName.FormattedName);
            assertNotNull(resume2.ContactInformation.CandidateName.FormattedName);

            resume1 = ParsedResume.fromFile(tempFile1);
            resume2 = ParsedResume.fromFile(tempFile2);

            assertNotNull(resume1);
            assertNotNull(resume2);

            assertNotNull(resume1.ContactInformation.CandidateName.FormattedName);
            assertNotNull(resume2.ContactInformation.CandidateName.FormattedName);

            assertThrows(JsonParseException.class, () -> { ParsedResume.fromJson("{}"); });
        }
        catch (Exception e) {}
        finally {
            Files.delete(Paths.get(tempFile1));
            Files.delete(Paths.get(tempFile2));
        }
    }

    @Test
    public void testJobToFromJson() throws IOException {
        String tempFile1 = UUID.randomUUID().toString();
        String tempFile2 = UUID.randomUUID().toString();

        try {
            ParseJobResponse response = Client.parseJob(new ParseRequest(TestData.JobOrder, null));

            String unformatted = response.Value.JobData.toJson(false);
            String formatted = response.Value.JobData.toJson(true);

            response.Value.JobData.toFile(tempFile1, true);
            response.Value.JobData.toFile(tempFile2, false);

            ParsedJob job1 = ParsedJob.fromJson(unformatted);
            ParsedJob job2 = ParsedJob.fromJson(formatted);

            assertNotNull(job1);
            assertNotNull(job2);

            assertNotNull(job1.JobMetadata.PlainText);
            assertNotNull(job2.JobMetadata.PlainText);

            job2 = ParsedJob.fromFile(tempFile1);
            job2 = ParsedJob.fromFile(tempFile2);

            assertNotNull(job2);
            assertNotNull(job2);

            assertNotNull(job1.JobMetadata.PlainText);
            assertNotNull(job2.JobMetadata.PlainText);

            assertThrows(JsonParseException.class, () -> { ParsedJob.fromJson("{}"); });
        }
        catch (Exception e) {}
        finally {
            Files.delete(Paths.get(tempFile1));
            Files.delete(Paths.get(tempFile2));
        }
    }

    @Test
    public void testParseJobGeocodeIndex() throws SovrenException {
        String indexId = "SDK-testparseJobGeocodeIndex";
        String documentId = "1";

        GeocodeOptions geocodeOptions = new GeocodeOptions();
        geocodeOptions.IncludeGeocoding = true;

        IndexSingleDocumentInfo indexingOptions = new IndexSingleDocumentInfo();
        indexingOptions.IndexId = indexId;
    
        // since there isn't an address this will throw an exception
        assertThrows(SovrenGeocodeJobException.class, () -> {
            ParseRequest request = new ParseRequest(TestData.JobOrder, null);
            request.GeocodeOptions = geocodeOptions;
            request.IndexingOptions = indexingOptions;
            Client.parseJob(request);
        });
    
        // confirm you can geocode but indexing fails
        assertThrows(SovrenIndexJobException.class, () -> {
            ParseRequest request = new ParseRequest(TestData.JobOrderWithAddress, null);
            request.GeocodeOptions = geocodeOptions;
            request.IndexingOptions = indexingOptions;
            Client.parseJob(request);
        });
    
        try {
            // set the document id and create the index
            indexingOptions.DocumentId = documentId;
            Client.createIndex(IndexType.Job, indexId);
            delayForIndexSync();
    
            // confirm you can parse/geocode/index
            assertDoesNotThrow(() -> {
                ParseRequest request = new ParseRequest(TestData.JobOrderWithAddress, null);
                request.GeocodeOptions = geocodeOptions;
                request.IndexingOptions = indexingOptions;
                Client.parseJob(request);
            });
    
            // verify the resume exists in the index
            delayForIndexSync();
            Client.getJob(indexId, documentId);
        }
        catch (SovrenException e) { throw e; }
        finally {
            cleanUpIndex(indexId);
        }
    }

    @Test
    public void testSkillsData() throws SovrenException {
        ParseResumeResponseValue response = Client.parseResume(new ParseRequest(TestData.Resume, null)).Value;
    
        assertEquals(response.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).MonthsExperience.Value, 12);
        assertEquals(response.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).LastUsed.Value.toString(), "2018-07-01");
        assertEquals(response.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).Variations.get(0).MonthsExperience.Value, 12);
        assertEquals(response.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).Variations.get(0).LastUsed.Value.toString(), "2018-07-01");
    }

    @Test
    public void testPersonalInfoAndResumeQuality() throws SovrenException {
        ParseResumeResponseValue response = Client.parseResume(new ParseRequest(TestData.ResumePersonalInformation, null)).Value;
    
        assertNotNull(response.ResumeData.PersonalAttributes.Birthplace);
        assertNotNull(response.ResumeData.PersonalAttributes.DateOfBirth);
        assertEquals(response.ResumeData.PersonalAttributes.DateOfBirth.Date.toString(), "1980-05-05");
        assertNotNull(response.ResumeData.PersonalAttributes.DrivingLicense);
        assertNotNull(response.ResumeData.PersonalAttributes.FathersName);
        assertNotNull(response.ResumeData.PersonalAttributes.Gender);
        assertNotNull(response.ResumeData.PersonalAttributes.MaritalStatus);
        assertNotNull(response.ResumeData.PersonalAttributes.MotherTongue);
        assertNotNull(response.ResumeData.PersonalAttributes.Nationality);
        assertNotNull(response.ResumeData.PersonalAttributes.PassportNumber);
    
        //fatal 413
        assertHasItems(response.ResumeData.ResumeMetadata.ResumeQuality);
        assertNotNull(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Level);
        assertEquals(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Level, ResumeQualityLevel.FatalProblem.Value);
        assertNotNull(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Findings);
        assertHasItems(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Findings);
        assertNotNull(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Findings.get(0).Message);
        assertNotEquals(0, response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Findings.get(0).QualityCode);
        assertEquals(response.ResumeData.ResumeMetadata.ResumeQuality.get(0).Findings.get(0).QualityCode, 413);
        assertNotNull(response.ResumeData.ResumeMetadata.ResumeQuality.get(3).Findings.get(0).Identifiers);
        assertHasItems(response.ResumeData.ResumeMetadata.ResumeQuality.get(3).Findings.get(0).Identifiers);
    }
    
    @Test
    public void testGeneralOutput() throws Exception {
        Document document = getTestFileAsDocument("resume.docx");
        ParseResumeResponse response = Client.parseResume(new ParseRequest(document, null));

        assertTrue(response.Info.isSuccess());

        assertNotNull(response.Info);
        assertTrue(response.Info.isSuccess());
        assertNotNull(response.Info.Message);
        assertNotNull(response.Info.Code);
        assertNotNull(response.Info.TransactionId);
        assertNotEquals(0, response.Info.TotalElapsedMilliseconds);
        assertNotNull(response.Info.ApiVersion);
        assertNotNull(response.Info.EngineVersion);
        assertNotNull(response.Info.CustomerDetails);
        assertNotNull(response.Info.CustomerDetails.AccountId);
        assertNotEquals(0, response.Info.CustomerDetails.CreditsRemaining);
        assertNotEquals(0, response.Info.CustomerDetails.CreditsUsed);
        assertNotNull(response.Info.CustomerDetails.ExpirationDate);
        assertNotNull(response.Info.CustomerDetails.IPAddress);
        assertNotEquals(0, response.Info.CustomerDetails.MaximumConcurrentRequests);
        assertNotNull(response.Info.CustomerDetails.Name);
        assertNotNull(response.Info.CustomerDetails.Region);

        assertNotNull(response.Value);

        assertNotNull(response.Value.ConversionMetadata);
        assertNotNull(response.Value.ParsingMetadata);
        assertNotNull(response.Value.ResumeData);

        assertEquals(response.Value.ConversionMetadata.DetectedType, "WordDocx");
        assertEquals(response.Value.ConversionMetadata.SuggestedFileExtension, "docx");
        assertEquals(response.Value.ConversionMetadata.OutputValidityCode, "ovIsProbablyValid");
        assertNotEquals(0, response.Value.ConversionMetadata.ElapsedMilliseconds);

        assertEquals(response.Value.ResumeData.ResumeMetadata.DocumentCulture, "en-US");
        assertNotEquals(0, response.Value.ParsingMetadata.ElapsedMilliseconds);
        assertEquals(response.Value.ParsingMetadata.TimedOut, false);
        assertNull(response.Value.ParsingMetadata.TimedOutAtMilliseconds);

        assertNotNull(response.Value.ResumeData.Certifications);
        assertHasItems(response.Value.ResumeData.Certifications);
        assertNotNull(response.Value.ResumeData.Certifications.get(0).Name);
        assertNotNull(response.Value.ResumeData.Certifications.get(0).FoundInContext);

        assertNotNull(response.Value.ResumeData.ContactInformation);
        assertNotNull(response.Value.ResumeData.ContactInformation.CandidateName);
        assertNotNull(response.Value.ResumeData.ContactInformation.CandidateName.FamilyName);
        assertNotNull(response.Value.ResumeData.ContactInformation.CandidateName.FormattedName);
        assertNotNull(response.Value.ResumeData.ContactInformation.CandidateName.GivenName);
        assertNotNull(response.Value.ResumeData.ContactInformation.CandidateName.MiddleName);
        assertNotNull(response.Value.ResumeData.ContactInformation.EmailAddresses);
        assertHasItems(response.Value.ResumeData.ContactInformation.EmailAddresses);
        assertNotNull(response.Value.ResumeData.ContactInformation.EmailAddresses.get(0));
        assertLocationNotNull(response.Value.ResumeData.ContactInformation.Location, true, false);
        assertNotNull(response.Value.ResumeData.ContactInformation.Telephones);
        assertHasItems(response.Value.ResumeData.ContactInformation.Telephones);
        assertNotNull(response.Value.ResumeData.ContactInformation.Telephones.get(0).Normalized);
        assertNotNull(response.Value.ResumeData.ContactInformation.Telephones.get(0).Raw);
        assertNotNull(response.Value.ResumeData.ContactInformation.WebAddresses);
        assertHasItems(response.Value.ResumeData.ContactInformation.WebAddresses);
        assertNotNull(response.Value.ResumeData.ContactInformation.WebAddresses.get(0).Address);
        assertNotNull(response.Value.ResumeData.ContactInformation.WebAddresses.get(0).Type);

        assertNotNull(response.Value.ResumeData.Education);
        assertDegreeNotNull(response.Value.ResumeData.Education.HighestDegree);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails);
        assertHasItems(response.Value.ResumeData.Education.EducationDetails);
        assertDegreeNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).Degree);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).GPA);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).GPA.MaxScore);
        assertNotEquals(0, response.Value.ResumeData.Education.EducationDetails.get(0).GPA.NormalizedScore);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).GPA.Score);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).GPA.ScoringSystem);
        assertNull(response.Value.ResumeData.Education.EducationDetails.get(0).Graduated);
        assertDateNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).LastEducationDate);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).Majors);
        assertHasItems(response.Value.ResumeData.Education.EducationDetails.get(0).Majors);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).SchoolName);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).SchoolName.Normalized);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).SchoolName.Raw);
        assertNotNull(response.Value.ResumeData.Education.EducationDetails.get(0).Text);

        assertNotNull(response.Value.ResumeData.EmploymentHistory);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.ExperienceSummary);
        assertNotEquals(0, response.Value.ResumeData.EmploymentHistory.ExperienceSummary.AverageMonthsPerEmployer);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.ExperienceSummary.CurrentManagementLevel);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.ExperienceSummary.Description);
        assertNotEquals(0, response.Value.ResumeData.EmploymentHistory.ExperienceSummary.FulltimeDirectHirePredictiveIndex);
        assertNotEquals(0, response.Value.ResumeData.EmploymentHistory.ExperienceSummary.ManagementScore);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.ExperienceSummary.ManagementStory);
        assertNotEquals(0, response.Value.ResumeData.EmploymentHistory.ExperienceSummary.MonthsOfWorkExperience);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions);
        assertHasItems(response.Value.ResumeData.EmploymentHistory.Positions);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Description);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Employer);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Employer.Name);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Employer.Name.Normalized);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Employer.Name.Raw);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Employer.Name.Probability);
        assertDateNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).EndDate);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).Id);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobLevel);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle.Normalized);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle.Probability);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle.Raw);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle.Variations);
        assertHasItems(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobTitle.Variations);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).JobType);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).SubTaxonomyName);
        assertNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).TaxonomyName);
        assertDateNotNull(response.Value.ResumeData.EmploymentHistory.Positions.get(0).StartDate);

        assertNotNull(response.Value.ResumeData.ResumeMetadata);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.FoundSections);
        assertHasItems(response.Value.ResumeData.ResumeMetadata.FoundSections);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.FoundSections.get(0).SectionType);
        assertNotEquals(0, response.Value.ResumeData.ResumeMetadata.FoundSections.get(0).LastLine);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.ReservedData);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.SovrenSignature);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.ParserSettings);
        assertNotNull(response.Value.ResumeData.ResumeMetadata.ResumeQuality);


        assertNotNull(response.Value.ResumeData.ProfessionalSummary);
        assertNotNull(response.Value.ResumeData.SkillsData);
        assertHasItems(response.Value.ResumeData.SkillsData);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Root);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies);
        assertHasItems(response.Value.ResumeData.SkillsData.get(0).Taxonomies);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).Id);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).Name);
        assertNotEquals(0, response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).PercentOfOverall);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies);
        assertHasItems(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies);
        assertNotEquals(0, response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).PercentOfOverall);
        assertNotEquals(0, response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).PercentOfParent);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).SubTaxonomyId);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).SubTaxonomyName);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills);
        assertHasItems(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).FoundIn);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).Id);
        assertNotNull(response.Value.ResumeData.SkillsData.get(0).Taxonomies.get(0).SubTaxonomies.get(0).Skills.get(0).Name);
    }
}
