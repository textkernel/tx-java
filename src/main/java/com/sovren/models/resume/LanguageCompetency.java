package com.sovren.models.resume;

/**
* A language competency (fluent in, can read, can write, etc) found on a resume
*/
public class LanguageCompetency {

    /** The language name*/
    public String Language;

    /** The two-letter ISO 639-1 code for the language*/
    public String LanguageCode;

    /** The full text where Sovren found this language competency*/
    public String FoundInContext;
}
