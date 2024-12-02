package com.taf.tests;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExampleRestAssuredTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.port = 443;
//        RestAssured.reset();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void whenRequestGet_thenOK() {
        RestAssured.when().request("GET", "/users/eugenp").then().statusCode(200);
    }

    @Test
    public void whenRequestHead_thenOK() {
        RestAssured.when().request("HEAD", "/users/eugenp").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {
        Response response = RestAssured.get("/users/eugenp");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        Assertions.assertEquals(timeInS, timeInMS / 1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        RestAssured.when().get("/users/eugenp").then().time(Matchers.lessThan(5000L));
    }

    @Test
    public void whenValidateResponseTimeInSeconds_thenSuccess() {
        RestAssured.when().get("/users/eugenp").then().time(Matchers.lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void whenLogRequest_thenOK() {
        RestAssured.given().log().all()
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenLogResponse_thenOK() {
        RestAssured.when().get("/repos/eugenp/tutorials")
                .then().log().body().statusCode(200);
    }

    @Test
    public void whenLogResponseIfErrorOccurred_thenSuccess() {
        RestAssured.when().get("/users/eugenp")
                .then().log().ifError();
        RestAssured.when().get("/users/eugenp")
                .then().log().ifStatusCodeIsEqualTo(500);
        RestAssured.when().get("/users/eugenp")
                .then().log().ifStatusCodeMatches(Matchers.greaterThan(200));
    }

    @Test
    public void whenLogOnlyIfValidationFailed_thenSuccess() {
        RestAssured.when().get("/users/eugenp")
                .then().log().ifValidationFails().statusCode(200);

        RestAssured.given().log().ifValidationFails()
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

//    @Test
//    public void givenUrl_whenJsonResponseConformsToSchema_thenCorrect() {
//        RestAssured.get("/events?id=390").then().assertThat()
//                .body(matchesJsonSchemaInClasspath("event_0.json"));
//    }

    @Test
    public void whenUsePathParam_thenOK() {
        RestAssured.given().pathParam("user", "eugenp")
                .when().get("/users/{user}/repos")
                .then().statusCode(200);
    }

    @Test
    public void whenUseMultiplePathParam_thenOK() {
        RestAssured.given().pathParams("owner", "eugenp", "repo", "tutorials")
                .when().get("/repos/{owner}/{repo}")
                .then().statusCode(200);

        RestAssured.given().pathParams("owner", "eugenp")
                .when().get("/repos/{owner}/{repo}", "tutorials")
                .then().statusCode(200);
    }

    @Test
    public void whenUseQueryParam_thenOK() {
        RestAssured.given().queryParam("q", "john").when().get("/search/users")
                .then().statusCode(200);

        RestAssured.given().param("q", "john").when().get("/search/users")
                .then().statusCode(200);
    }

    @Test
    public void whenUseMultipleQueryParam_thenOK() {
        int perPage = 20;
        RestAssured.given().queryParam("q", "john").queryParam("per_page", perPage)
                .when().get("/search/users")
                .then().body("items.size()", Matchers.is(perPage));

        RestAssured.given().queryParams("q", "john", "per_page", perPage)
                .when().get("/search/users")
                .then().body("items.size()", Matchers.is(perPage));
    }

    @Test
    public void whenUseFormParam_thenSuccess() {
        RestAssured.given().formParams("username", "john", "password", "1234").post("/");

        RestAssured.given().params("username", "john", "password", "1234").post("/");
    }

    @Test
    public void whenUseCustomHeader_thenOK() {

        RestAssured.given().header("User-Agent", "MyAppName").when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenUseMultipleHeaderValues_thenOK() {

        RestAssured.given().header("My-Header", "val1", "val2")
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenUseMultipleHeaders_thenOK() {

        RestAssured.given().headers("User-Agent", "MyAppName", "Accept-Charset", "utf-8")
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenUseCookie_thenOK() {

        RestAssured.given().cookie("session_id", "1234").when().get("/users/eugenp")
                .then().statusCode(200);
    }

    @Test
    public void whenUseCookieBuilder_thenOK() {
        Cookie myCookie = new Cookie.Builder("session_id", "1234")
                .setSecured(true)
                .setComment("session id cookie")
                .build();

        RestAssured.given().cookie(myCookie)
                .when().get("/users/eugenp")
                .then().statusCode(200);
    }

    private class Odd {
        public Odd(float v, int i, float v1, String x) {
        }
    }

}
