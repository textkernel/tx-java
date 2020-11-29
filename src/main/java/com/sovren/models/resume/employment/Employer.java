package com.sovren.models.resume.employment;

import com.sovren.models.resume.NormalizedString;
import com.sovren.models.Location;

/**
* A name/location for a company/employer
*/
public class Employer {
    
    /** The name of the employer (and an accuracy probability)*/
    public CompanyNameWithProbability Name;
    
    /**
     * Sometimes a second possible company name is found, or a department/organization
     * within a company. This is that value, if it is found.
    */
    public NormalizedString NameVariation;
    
    /** The location/address of the employer*/
    public Location Location;
}
