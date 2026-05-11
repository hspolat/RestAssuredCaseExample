package com.booker.tests;

import com.booker.utils.SpecBuilder;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Restful Booker API")
@Feature("Health Check")
public class PingTests extends BaseTest {

    @Test
    @Story("Servis ayakta mı kontrolü")
    public void healthCheck() {
        SpecBuilder.getBaseSpec()
                .when()
                .get("/ping")
                .then()
                .statusCode(201);
    }
}