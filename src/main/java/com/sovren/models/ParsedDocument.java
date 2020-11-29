package com.sovren.models;

import com.sovren.utilities.SovrenJsonSerializer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Base class for parsed resumes/jobs */
public abstract class ParsedDocument {
    
    @Override
    public String toString() { return toJson(true); }

    /**
     * @param formatted {@code true} for pretty-printing
     * @return a JSON string that can be saved to disk or any other data storage.
     * <br/><b>NOTE: be sure to save with UTF-8 encoding!</b>
     */
    public String toJson(boolean formatted) {
        return SovrenJsonSerializer.serialize(this, formatted);
    }

    /**
     * Save the json to disk using UTF-8 encoding
     * @param filepath The file to save to
     * @param formatted {@code true} for pretty-printing
     */
    public void toFile(String filepath, boolean formatted) throws IOException {
        String json = toJson(formatted);
        if (json != null) {
            Files.writeString(Paths.get(filepath), json, Charset.forName("utf8"));
        }
    }
}
