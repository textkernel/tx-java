// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.formatter;

import java.io.IOException;
import java.time.LocalDate;

import com.textkernel.tx.models.Document;
import com.textkernel.tx.models.resume.ParsedResume;

/**
* Request body for the Format Resume With Template endpoint
*/
public class FormatResumeRequest {
    /**  The resume to use to populate the template */
    public ParsedResume ResumeData;

    /** 
     * A base64-encoded string of the DOCX template document file bytes. This should use the standard 'base64'
     * encoding as defined in RFC 4648 Section 4 (not the 'base64url' variant).
     * <p>Java users can use {@link java.util.Base64.Encoder#encodeToString(byte[])}
     * <p>For more information on creating custom templates, see
     * <a href="https://developer.textkernel.com/tx-platform/v10/resume-formatter/creating-custom-templates/">here</a>
     */
    public String Template;

    /** The output document type */
    public OutputDocumentFormat OutputType;

    /**
     * Any data that the template needs that is not in the extracted CV data. For example:
     * <pre>
     *JSONObject myCustomData = new JSONObject();
     *myCustomData.put("CandidateId", "12345");
     *myCustomData.put("DateApplied", LocalDate.now());
     *formatRequest.CustomData = myCustomData;
     *</pre>
     */
    public Object CustomData;

    /**
     * Creates a request to use when calling the Resume Formatter endpoint with a provided template document.
     * @param resume The resume to use to populate the template
     * @param templatePath The path to the template DOCX file on disk
     * @param docType The output document type
     * @throws IOException if an error occurs reading the file contents
     */
    public FormatResumeRequest(ParsedResume resume, String templatePath, OutputDocumentFormat docType) throws IOException {
        ResumeData = resume;
        OutputType = docType;
        Template = new Document(templatePath).getAsBase64();
    }

    /**
     * Creates a request to use when calling the Resume Formatter endpoint with a provided template document.
     * @param resume The resume to use to populate the template
     * @param templateFileBytes The bytes of the template DOCX file
     * @param docType The output document type
     */
    public FormatResumeRequest(ParsedResume resume, byte[] templateFileBytes, OutputDocumentFormat docType) {
        ResumeData = resume;
        OutputType = docType;
        Template = new Document(templateFileBytes, LocalDate.now()).getAsBase64();
    }
}
