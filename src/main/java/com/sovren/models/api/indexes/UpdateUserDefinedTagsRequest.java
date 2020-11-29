package com.sovren.models.api.indexes;

import java.util.List;

/**
 * Request body to update (add/remove/overwrite) user-defined tags on an indexed
 * document
 */
public class UpdateUserDefinedTagsRequest {
    
    /** The user-defined tags to add/delete/etc */
    public List<String> UserDefinedTags;

    /** Which method to use for the specified user-defined tags */
    public UserDefinedTagsMethod Method;
}
