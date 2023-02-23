package com.sovren.models.api.dataenrichment.professions.response;
import java.util.List;

/**
 * A skill object.
 */
public class ProfessionMultipleDescriptions extends Profession {
    /**
     * A list of descriptions of the skill in all supported/requested languages.
     */
    public List<LangDescription> Descriptions;

    /**
     * The description of the skill in the requested language.<br/><b>NOTE: if multiple languages were requested, this is simply the first from {@link Descriptions}</b>
     */
    public ProfessionMultipleDescriptions(){
        this.Description = Descriptions != null ? Descriptions.get(0).Description : null;
    }
}
