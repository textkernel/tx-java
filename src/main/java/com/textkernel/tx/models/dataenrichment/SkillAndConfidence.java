package com.textkernel.tx.models.dataenrichment;

/** A skill from the skill taxonomy, and the confidence that this is the correct skill for the given input. */
public class SkillAndConfidence extends Skill {
    /**  Overall confidence that the skill was normalized to the correct skill. */
    public float Confidence;
}
