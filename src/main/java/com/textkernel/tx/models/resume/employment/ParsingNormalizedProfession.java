package com.textkernel.tx.models.resume.employment;

/** Normalized profession related to a specific job title. */
public class ParsingNormalizedProfession {
    
    /** Object containing the details of the profession concept. */
    public ProfessionClassification<Integer> Profession;

    /** The object of the group to which the profession concept belongs. */
    public ProfessionClassification<Integer> Group;

    /** The object of the class to which the profession concept belongs. */
    public ProfessionClassification<Integer> Class;

    /** The object of the ISCO profession concept */
    public VersionedNormalizedProfessionClassification<Integer> ISCO;

    /** The object of the ONET profession concept */
    public VersionedNormalizedProfessionClassification<String> ONET;

    /** Overall confidence that the input job title was normalized to the correct profession concept */
    public float Confidence;
}
