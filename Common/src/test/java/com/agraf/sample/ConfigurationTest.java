package com.agraf.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        Assertions.assertNotNull(Configuration.getInstance());
    }

    @Test
    void getInstanceWithEnvName() {
        Assertions.assertNotNull(Configuration.getInstance("env"));
    }

    @Test
    void getInstanceWhenNotExistingEnvNameIsSet() {
        Assertions.assertThrows(RuntimeException.class, () -> Configuration.getInstance("notExistingEnvName"));
    }

    @Test
    void getEnvName() {
        Configuration configuration = Configuration.getInstance("env");
        Assertions.assertEquals("env", configuration.getEnvName());
    }
}