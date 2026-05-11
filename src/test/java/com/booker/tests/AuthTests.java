package com.booker.tests;

import com.booker.config.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Epic("Restful Booker API")
@Feature("Authentication")
public class AuthTests extends BaseTest {

    @Test
    @Story("Geçerli credentials ile token alınabilmeli")
    public void createToken() {
        Assert.assertNotNull(token, "Token null olmamalı");
        Assert.assertFalse(token.isEmpty(), "Token boş olmamalı");
    }
}