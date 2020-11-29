package com.sovren.models.api.matching.ui;

import java.util.List;

/** A picklist to show to a user for filtering on user-defined tags*/
public class UserDefinedTagsPicklist {

    /** The label for this picklist in the UI */
    public String Label;

    /** A list of user-defined tags that the user will be able to pick from */
    public List<UserDefinedTagOption> Options;
}
