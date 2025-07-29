// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.parsing;

import java.util.List;

import com.textkernel.tx.exceptions.TxUsableJobException;
import com.textkernel.tx.exceptions.TxUsableResumeException;
import com.textkernel.tx.models.api.geocoding.GeocodeOptions;
import com.textkernel.tx.models.api.indexes.IndexingOptionsGeneric;

/** Options for parsing */
public class ParseOptions extends BasicParseOptions {
    
    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************

    /** {@code true} to output the document converted to HTML*/
    public boolean OutputHtml;

    /** {@code true} to remove any images in the converted HTML*/
    public boolean HideHtmlImages;
    
    /** {@code true} to output the document converted to RTF*/
    public boolean OutputRtf;
    
    /** {@code true} to output the document converted to PDF*/
    public boolean OutputPdf;
    
    /** Only used for resumes. {@code true} to extract/output a candidate's image if it is present in the resume.*/
    public boolean OutputCandidateImage;
    
    /**
     * Use this property to also include geocoding in this parse request. The document will be parsed and then geocoded.
    */
    public GeocodeOptions GeocodeOptions;
    
    /**
     * If you are using Search &amp; Match, use this property to also index/upload the document after it is parsed/geocoded.
     * <p>
     * <strong>NOTE: if you set this while parsing, you should try/catch for {@link TxUsableResumeException} or {@link TxUsableJobException}
     * that are thrown when parsing was successful but an error occured during indexing</strong>
     * </p>
    */
    public IndexingOptionsGeneric IndexingOptions;

    /**
     * Only used for resumes. When {@code true}, and the document is English, the LLM Parser will be used. 
     * See the <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/llm-parser/#overview">overview documentation</a> for more information.
     * <a href="https://developer.textkernel.com/tx-platform/v10/overview/#transaction-cost">Additional charges</a> will apply.
    */
    public boolean UseLLMParser;

    /**
     * Only used for resumes. Custom requests to ask during parsing. 
     * See the <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/flexrequests">overview documentation</a> for more information.
     * <a href="https://developer.textkernel.com/tx-platform/v10/overview/#transaction-cost">Additional charges</a> will apply.
    */
    public List<FlexRequest> FlexRequests;

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************
}