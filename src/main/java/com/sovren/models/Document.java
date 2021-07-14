// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.sovren.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;

/** Represents an unparsed document (a file on the filesystem, byte[] from a database, etc)*/
public class Document {
    protected String _asBase64;

    /** @return The base-64 encoded byte[] for this file. This is what is sent over HTTPS to Sovren's API*/
    public String getAsBase64() {
        return _asBase64;
    }

    /** The last revised/modified date for this file.*/
    public LocalDate LastModified;

    /**
     * Create a {@link Document} from a file {@code byte[]}
     * @param fileBytes - The file byte array
     * @param lastModified - The last-revised date for this file.
     * <p>Per our AUP (https://sovren.com/policies-and-agreements/acceptable-use-policy/), you MUST pass a good-faith last-revised date for every parse transaction.
     * <p>This is extremely important so that the Parser knows how to interpret dates in the document that are 
     * expressed as "current" or "as of" (or similar) to correctly calculate date spans
     * @throws IllegalArgumentException If the fileBytes is null or empty
     */
    public Document(byte[] fileBytes, LocalDate lastModified) {
        if (fileBytes == null || fileBytes.length == 0) throw new IllegalArgumentException("'fileBytes' cannot be null");
        _asBase64 = Base64.getEncoder().encodeToString(fileBytes);
        LastModified = lastModified;

        if (LastModified == LocalDate.MIN || LastModified == LocalDate.MAX) {
            throw new IllegalArgumentException("You must provide a valid last-modified date so that parser metadata is accurate");
        }
    }

    /**
     * Create a {@link Document} from a file on the filesystem.
     * <p>NOTE: this will automatically set the {@link #LastModified} using {@link Files#getLastModifiedTime(Path, LinkOption...)}
     * <p><b>If your files do not have an accurate 'LastWrite' or 'LastModified' time, you must use a different constructor</b>
     * @param path - The path to the file
     * @throws IllegalArgumentException If the file is empty
     * @throws IOException If an error occurs reading the file contents
     */
    public Document(String path) throws IOException {
        this(Files.readAllBytes(Paths.get(path)),
             Files.getLastModifiedTime(Paths.get(path)).toInstant().atZone(ZoneId.systemDefault()));
    }

    /** simply a private ctor to help with converting an Instant to a LocalDate */
    private Document(byte[] fileBytes, ZonedDateTime time) {
        this(fileBytes, LocalDate.of(time.getYear(), time.getMonth(), time.getDayOfMonth()));
    }
}
