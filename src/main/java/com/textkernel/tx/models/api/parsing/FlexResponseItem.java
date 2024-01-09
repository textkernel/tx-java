// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.


package com.textkernel.tx.models.api.parsing;

import java.util.List;

/**
 * Responses to FlexRequests
 */
public class FlexResponseItem {
    
    /**
     * Unique field name assigned to the respective FlexRequest
     */
    public String Identifier;

    /**
     * Reply to the FlexRequest Prompt
     */
    public String Reply;
    
    /**
     * List of replies to the FlexRequest Prompt if the FlexRequest had a {@link FlexRequestDataType#Enumeration} DataType
     */
    public List<String> ReplyList;

}