package com.booker.tests;

import com.booker.config.ConfigManager;
import org.testng.annotations.BeforeSuite;
import static io.restassured.RestAssured.given;
import com.booker.models.TokenRequest;
import io.restassured.http.ContentType;

public class BaseTest {

    protected static String token;

    @BeforeSuite
    public void initSuite() {
        TokenRequest request = TokenRequest.builder()
                .username(ConfigManager.getUsername())
                .password(ConfigManager.getPassword())
                .build();

        token = given()
                .baseUri(ConfigManager.getBaseUrl())
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token alınamadı!");
        }
    }
}