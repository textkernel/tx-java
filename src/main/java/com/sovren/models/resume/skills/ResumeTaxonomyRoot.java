package com.sovren.models.resume.skills;

import java.util.List;

/**
* A container for skills taxonomies found in a resume
*/
public class ResumeTaxonomyRoot {

    /** The name of the skills list that these taxonomies belong to*/
    public String Root;

    /** The skills taxonomies found in a resume*/
    public List<ResumeTaxonomy> Taxonomies;
}
