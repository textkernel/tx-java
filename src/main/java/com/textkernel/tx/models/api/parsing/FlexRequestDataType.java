package com.textkernel.tx.models.api.parsing;

/**
 * Possible data types for FlexRequests
 */
public enum FlexRequestDataType {
    /** Text/string response expected */
    Text,
    /** Numeric response expected */
    Numeric,
    /** Boolean response expected */
    Bool,
    /** List of text responses expected */
    List,
    /** Text response should be from a provided list of options. See {@link FlexRequest#EnumerationValues} */
    Enumeration
}
