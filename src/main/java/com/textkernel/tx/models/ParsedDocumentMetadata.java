// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Sovren products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models;

import java.time.LocalDate;
import java.util.List;

/**
* Metadata about a parsed document
*/
public class ParsedDocumentMetadata {

    /** The plain text that was used for parsing*/
    public String PlainText;

    /**
     * An ISO 639-1 code that represents the primary language of the parsed text. When the
     * language could not be automatically determined, it is reported as the special value 
     * <code>iv</code>(invariant/unknown). Note that the two-letter ISO codes reported by the
     * Parser - such as <code>zh</code> for Chinese - do not differentiate between language
     * variants, such as Mandarin and Cantonese.
     */
    public String DocumentLanguage;

    /**
     * An ISO 3066 code that represents the cultural context of the document regarding formatting of
     * numbers, dates, character symbols, etc. This value is usually a simple concatenation of the 
     * language and country codes, such as <code>en-US</code> for US English; however, note that culture 
     * can be set independently of language and country to achieve fine-tuned cultural control over parsing,
     * so if you use this value you should not assume that it always matches the language and country.
     */
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
