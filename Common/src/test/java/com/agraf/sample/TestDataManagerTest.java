package com.agraf.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDataManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        Assertions.assertNotNull(TestDataManager.getInstance());
    }

    @Test
    void readString() {
        String expectedJson = "{\r\n" + "  \"name\": \"TST example\"\r\n" + "}";
        String json = TestDataManager.getInstance().readString("example.json");
        Assertions.assertNotNull(json);
        Assertions.assertEquals(expectedJson, json);
    }
}