package com.sovren.models.job.skills;

import java.util.List;

/**
* A container for skills taxonomies found in a job
*/
public class JobTaxonomyRoot {

    /** The name of the skills list that these taxonomies belong to*/
    public String Root;

    /** The taxonomies found in a job*/
    public List<JobTaxonomy> Taxonomies;
}
