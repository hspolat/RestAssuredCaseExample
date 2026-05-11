package com.booker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Attachment;

public class AllureUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Attachment(value = "{name}", type = "application/json")
    public static String attachJson(String name, Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (Exception e) {
            return String.valueOf(object);
        }
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String text) {
        return text;
    }
}