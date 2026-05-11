package com.booker.utils;

import com.booker.config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class SpecBuilder {

    public static RequestSpecification getBaseSpec() {
        return given()
                .baseUri(ConfigManager.getBaseUrl())
                .contentType(ContentType.JSON);
    }

    public static RequestSpecification getAuthSpec(String token) {
        return getBaseSpec()
                .cookie("token", token);
    }
}