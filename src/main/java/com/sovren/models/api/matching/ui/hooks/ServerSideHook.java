package com.sovren.models.api.matching.ui.hooks;

/** A hook that does some server-side action (performs an HTTP POST to your server)*/
public class ServerSideHook extends UserActionHook {
    /** The URL for an HTTP POST call to perform some action in your system.*/
    public String Url;

    /**
     * Any data from your system that you need to associate with this session/action.
     * This is sent (in addition to document information) in the POST body.
     * <br/>For more information see <see href="https://docs.sovren.com/Documentation/AIMatching#ui-match-hooks">here</see>.
    */
    public Object CustomInfo;
}
