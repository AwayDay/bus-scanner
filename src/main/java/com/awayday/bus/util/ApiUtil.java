package com.awayday.bus.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ApiUtil {
    public static boolean isResponseSuccess(JsonNode response) {
        return response.has("header")
                && response.get("header").get("resultCode").asText("99").equals("00")
                && response.has("body")
                && response.get("body").has("items")
                && response.get("body").get("items").has("item");
    }

    public static String getDecodedKey(String key) {
        try {
            return URLDecoder.decode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
