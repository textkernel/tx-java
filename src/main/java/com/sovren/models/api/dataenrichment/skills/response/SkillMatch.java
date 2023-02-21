package com.sovren.models.api.dataenrichment.skills.response;
/** A matched skill that was found in the text provided. */
public class SkillMatch {
    /** The index of the first character of the match (0-based). */
    public int BeginSpan;
    /** The index of the last character of the match (0-based). */
    public int EndSpan;
    /** Likelihood that the matched term actually refers to a skill in the context of the text. */
    public float Likelihood;
    /** The actual term that was found as evidence of this skill (the substring from BeginSpan to EndSpan). */
    public String RawText;
}
