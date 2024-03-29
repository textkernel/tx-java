// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

import com.textkernel.tx.models.Document;

/**
 * The request body for a Parse request
 */
public class ParseRequest extends ParseOptions {
    
    /**
     * A Base64 encoded string of the document file bytes. This should use the standard 'base64' 
     * encoding as defined in <a href="https://tools.ietf.org/html/rfc4648#section-4"> RFC 4648 Section 4</a> 
     * (not the 'base64url' variant). 
     * <p>Java users can use {@link java.util.Base64#getEncoder()} and then {@link java.util.Base64.Encoder#encodeToString(byte[])}
    */
    public String DocumentAsBase64String;
    
    /**
     * <b>Mandatory</b> date - in ISO 8601 (yyyy-MM-dd) format - so that the Parser knows how to interpret dates in the document 
     * that are expressed as "current" or "as of" or similar. It is crucial that we know the date of the resume/CV so 
     * that we can correctly calculate date spans. For example, a resume received on January 5, 2019 should be passed 
     * with a DocumentLastModified of "2019-01-05"; if, however, it was modified on May 7, 2019, the DocumentLastModified should be 
     * passed as "2019-05-07". Failing to pass a DocumentLastModified, or passing DocumentLastModified that are clearly improbable, may 
     * result in rejection of data and/or additional charges, and will utterly decimate the usefulness of AI Matching and 
     * any generated metadata. 
    */
    public String DocumentLastModified;

    /**
     * 
     * @param doc The document (resume or job) to parse
     * @param optionsToUse Any non-default options to use ({@code null} for default)
     * @throws IllegalArgumentException if the document is null
     */
    @SuppressWarnings("deprecation")
    public ParseRequest(Document doc, ParseOptions optionsToUse) {
        if (doc == null) throw new IllegalArgumentException("Argument 'doc' cannot be null");

        this.DocumentAsBase64String = doc.getAsBase64();
        this.DocumentLastModified = doc.LastModified.toString();
        
        if (optionsToUse != null) {
            this.Configuration = optionsToUse.Configuration;
            this.GeocodeOptions = optionsToUse.GeocodeOptions;
            this.IndexingOptions = optionsToUse.IndexingOptions;
            this.NormalizerData = optionsToUse.NormalizerData;
            this.OutputCandidateImage = optionsToUse.OutputCandidateImage;
            this.OutputHtml = optionsToUse.OutputHtml;
            this.HideHtmlImages = optionsToUse.HideHtmlImages;
            this.OutputPdf = optionsToUse.OutputPdf;
            this.OutputRtf = optionsToUse.OutputRtf;
            this.SkillsData = optionsToUse.SkillsData;
            this.SkillsSettings = optionsToUse.SkillsSettings;
            this.ProfessionsSettings = optionsToUse.ProfessionsSettings;
            this.UseLLMParser = optionsToUse.UseLLMParser;
            this.FlexRequests = optionsToUse.FlexRequests;
        }
    }
}
