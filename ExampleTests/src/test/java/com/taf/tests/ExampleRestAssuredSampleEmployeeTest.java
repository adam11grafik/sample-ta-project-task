package com.taf.tests;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExampleRestAssuredSampleEmployeeTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//        RestAssured.port = 443;
//        RestAssured.reset();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void get() {
        RestAssured
                .given()
                .when()
                .log().all()
                .get("/posts/1")
                .then()
                .log().all()
                .assertThat().body("title", Matchers.equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    @Order(2)
    public void post() {
        String inputBody = "{\n" +
                "    \"userId\": 97,\n" +
                "    \"id\": 97123,\n" +
                "    \"title\": \"sample title AG\",\n" +
                "    \"body\": \"sample body AG\"\n" +
                "}";
        RestAssured
                .given()
                .body(inputBody)
                .when()
                .log().all()
                .post("/posts")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("id", Matchers.equalTo(101))
        ;
    }

    @Disabled
    @Test
    @Order(3)
    public void put() {
        String inputBody = "{\n" +
                "    \"userId\": 97,\n" +
                "    \"id\": 97124,\n" +
                "    \"title\": \"sample title AG 2\",\n" +
                "    \"body\": \"sample body AG 2\"\n" +
                "}";
        RestAssured
                .given()
                .accept("*/*")
//                .header()
                .body(inputBody)
                .when()
                .log().all()
                .put("/posts/101")
                .then()
                .log().all()
                .assertThat().statusCode(203);
    }

    @Test
    @Order(4)
    public void getAll() {
        RestAssured
                .given()
                .when()
                .log().all()
                .get("/posts")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    @Order(5)
    public void delete() {
        RestAssured
                .given()
                .when()
                .log().all()
                .delete("/posts/1")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

}
