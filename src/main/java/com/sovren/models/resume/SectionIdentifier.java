package com.sovren.models.resume;

import com.sovren.models.resume.education.EducationDetails;
import com.sovren.models.resume.employment.Position;

/** Information about a particular section of a resume*/
public class SectionIdentifier {
    /** The section type */
    public String SectionType;

    /**
     * If applicable, the {@link Position#Id} or {@link EducationDetails#Id}
     */
    public String Id;
}
