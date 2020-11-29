package com.sovren.models.resume;

import java.util.List;
import com.sovren.models.SovrenDate;

/**
* A training history found on a resume
*/
public class TrainingDetails {

    /** The text that was found on the resume*/
    public String Text;

    /** The name of the school or company where the training occurred.*/
    public String Entity;

    /** Any text within the {@link #Text} that is recognized as a qualification (such as DDS),
     * degree (such as B.S.), or a certification (such as PMP). Each qualification is listed separately.
     */
    public List<String> Qualifications;

    /** The date the training started*/
    public SovrenDate StartDate;

    /** The date the training ended*/
    public SovrenDate EndDate;
}
