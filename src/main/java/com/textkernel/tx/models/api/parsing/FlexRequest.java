// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.


package com.textkernel.tx.models.api.parsing;

import java.util.List;

/**
 * Custom requests to ask during parsing. 
 * See the <a href="https://developer.textkernel.com/tx-platform/v10/resume-parser/overview/llm-parser/#flex-requests">overview documentation</a> for more details.
 * <a href="https://developer.textkernel.com/tx-platform/v10/overview/#transaction-cost">Additional charges</a> will apply.
 */
public class FlexRequest {
    /**
     * The prompt to be sent to the LLM Parsing Engine
     */
    public String Prompt;

    /**
     * Unique field name to be returned alongside the reply in the response
     */
    public String Identifier;

    /**
     * The data type for the reply
     */
    public FlexRequestDataType DataType;

    /**
     * If DataType is {@link FlexRequestDataType#Enumeration}, this is the list of possible replies. This is limited to a maximum of 50 values.
     */
    public List<String> EnumerationValues;
}