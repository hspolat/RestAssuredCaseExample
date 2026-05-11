package com.booker.tests;

import com.booker.models.*;
import com.booker.utils.AllureUtils;
import com.booker.utils.SpecBuilder;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Restful Booker API")
@Feature("Booking CRUD")
public class BookingTests extends BaseTest {

    @Test
    @Story("Tüm rezervasyonları listele")
    public void getAllBookings() {
        List<?> bookings = SpecBuilder.getBaseSpec()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(List.class);

        Assert.assertFalse(bookings.isEmpty(), "Rezervasyon listesi boş olmamalı");
    }

    @Test
    @Story("ID ile rezervasyon getir")
    public void getBookingById() {

        int bookingId = createBookingAndReturnId();
        Booking booking = SpecBuilder.getBaseSpec()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);

        Assert.assertEquals(booking.getFirstname(), "Ali");
        Assert.assertEquals(booking.getLastname(), "Veli");
    }

    @Test
    @Story("Yeni rezervasyon oluştur")
    public void createBooking() {
        Booking request = buildSampleBooking();

        AllureUtils.attachJson(
                "Create Booking Request",
                request.toString()
        );

        BookingResponse response = SpecBuilder.getBaseSpec()
                .body(buildSampleBooking())
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);

        Assert.assertTrue(response.getBookingid() > 0, "Geçerli bir bookingId dönmeli");
        Assert.assertEquals(response.getBooking().getFirstname(), "Ali");

    }

    @Test
    @Story("Rezervasyonu tamamen güncelle (PUT)")
    public void updateBooking() {
        int bookingId = createBookingAndReturnId();
        Booking updated = Booking.builder()
                .firstname("Mehmet")
                .lastname("Yilmaz")
                .totalprice(200)
                .depositpaid(false)
                .bookingdates(BookingDates.builder()
                        .checkin("2025-01-01")
                        .checkout("2025-01-10")
                        .build())
                .additionalneeds("Lunch")
                .build();

        Booking response = SpecBuilder.getAuthSpec(token)
                .body(updated)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);

        Assert.assertEquals(response.getFirstname(), "Mehmet");
        Assert.assertEquals(response.getLastname(), "Yilmaz");
    }

    @Test
    @Story("Rezervasyonu kısmen güncelle (PATCH)")
    public void partialUpdateBooking() {
        int bookingId = createBookingAndReturnId();
        String partialBody = """
                {
                    "firstname": "Ayse",
                    "lastname": "Kaya"
                }
                """;

        Booking response = SpecBuilder.getAuthSpec(token)
                .body(partialBody)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);

        Assert.assertEquals(response.getFirstname(), "Ayse");
        Assert.assertEquals(response.getLastname(), "Kaya");
    }

    @Test
    @Story("Rezervasyonu sil (DELETE)")
    public void deleteBooking() {
        int bookingId = createBookingAndReturnId();
        BookingResponse temp = SpecBuilder.getBaseSpec()
                .body(buildSampleBooking())
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);

        SpecBuilder.getAuthSpec(token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }

    private Booking buildSampleBooking() {
        return Booking.builder()
                .firstname("Ali")
                .lastname("Veli")
                .totalprice(150)
                .depositpaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin("2025-06-01")
                        .checkout("2025-06-07")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }

    private int createBookingAndReturnId() {

        BookingResponse response = SpecBuilder.getBaseSpec()
                .body(buildSampleBooking())
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);

        return response.getBookingid();
    }
}