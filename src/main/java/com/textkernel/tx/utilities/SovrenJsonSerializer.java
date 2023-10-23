// Copyright © 2020 Sovren Group, Inc. All rights reserved.
// This file is provided for use by, or on behalf of, Sovren licensees
// within the terms of their license of Sovren products or Sovren customers
// within the Terms of Service pertaining to the Sovren SaaS products.

package com.textkernel.tx.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class SovrenJsonSerializer {

    private static final Gson _prettyGson;
    private static final Gson _compactGson;

    static {
        _prettyGson = getBuilder().setPrettyPrinting().create();
        _compactGson = getBuilder().create();
    }

    private static GsonBuilder getBuilder() {
        return new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>(){
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>(){
            @Override
            public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
                return new JsonPrimitive(date.toString());
            }
        });
    }
    
    public static String serialize(Object o){
        return serialize(o, false);
    }

    public static String serialize(Object o, boolean formatted){
        return formatted ? _prettyGson.toJson(o) : _compactGson.toJson(o);
    }
    
    public static <T> T deserialize(String json, Class<T> classOfT) {
        return _compactGson.fromJson(json, classOfT);
    }
}
