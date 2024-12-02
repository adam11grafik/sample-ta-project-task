package com.taf.tests;

import com.agraf.sample.groups.TestTypeTags;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class GitHubJunit5Test {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Tag(TestTypeTags.Sanity)
    @Tag(TestTypeTags.Regression)
    @Test
    public void getToken() {
        RestAssured.baseURI = "https://api.github.com/applications";

        Response response = RestAssured
                .with()
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .body("{\"access_token\": \"ACCESS_TOKEN_TO_CHECK\"}")
                .when()
                .post("/YOUR_CLIENT_ID/token")
                .then()
                .log().all()
                .extract().response();
    }

    @Tag(TestTypeTags.Sanity)
    @Tag(TestTypeTags.Regression)
    @Test
    public void getOctocat() {
        RestAssured.baseURI = "https://api.github.com";

        Response response = RestAssured
                .with()
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .when()
                .get("/octocat")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();
    }

}