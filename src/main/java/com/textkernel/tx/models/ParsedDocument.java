// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

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
     * <br><b>NOTE: be sure to save with UTF-8 encoding!</b>
     */
    public String toJson(boolean formatted) {
        return SovrenJsonSerializer.serialize(this, formatted);
    }

    /**
     * Save the json to disk using UTF-8 encoding
     * @param filepath The file to save to
     * @param formatted {@code true} for pretty-printing
     * @throws IOException When an error occurs writing the file
     */
    public void toFile(String filepath, boolean formatted) throws IOException {
        String json = toJson(formatted);
        if (json != null) {
            Files.write(Paths.get(filepath), json.getBytes(Charset.forName("utf8")));
        }
    }
}
