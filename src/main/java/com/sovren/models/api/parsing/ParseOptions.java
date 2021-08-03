// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models.api.parsing;

import com.sovren.models.api.geocoding.GeocodeOptions;
import com.sovren.models.api.indexes.IndexSingleDocumentInfo;

/** Options for parsing */
public class ParseOptions extends BasicParseOptions {
    
    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************

    /** {@code true} to output the document converted to HTML*/
    public boolean OutputHtml;
    
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
     * If you are using Sovren AI Matching, use this property to also index the document after it is parsed/geocoded.
     * This means you only need to send the document to our API once instead of twice for parsing+indexing.
    */
    public IndexSingleDocumentInfo IndexingOptions;

    //********************************
    //IF YOU ADD ANY PARAMS HERE BE SURE TO ADD THEM IN THE DEEP COPY INSIDE ParseRequest.ctor() !!
    //********************************
}
