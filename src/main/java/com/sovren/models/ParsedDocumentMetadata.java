package com.sovren.models;

import java.time.LocalDate;
import java.util.List;

/**
* Metadata about a parsed document
*/
public class ParsedDocumentMetadata {

    /** The plain text that was used for parsing*/
    public String PlainText;

    /** The two-letter ISO 639-1 code for the language the document was written in */
    public String DocumentLanguage;

    /** The xx-XX language/culture value for the parsed document. See also {@link #DocumentLanguage}*/
    public String DocumentCulture;

    /** The full parser settings that were used during parsing */
    public String ParserSettings;

    /** 
     * The last-revised/last-modified date that was provided for the document.
     * This was used to calculate all of the important metrics about skills and jobs.
     */
    public LocalDate DocumentLastModified;

    /** 
     * A digital signature used to ensure there is no tampering between parsing and indexing. This
     * prevents Sovren from storing any PII in the AI Matching engine.
     */
    public List<String> SovrenSignature;
}
