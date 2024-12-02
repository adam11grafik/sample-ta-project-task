package com.agraf.sample;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getConfiguration() {
        Configuration configuration = ConfigurationLoader.getConfiguration("env.properties");
        Assertions.assertNotNull(configuration);
        Assertions.assertEquals("value", configuration.getString("key"));
    }

    @Test
    void getConfigurationWhenFileDoesNotExists() {
        Assertions.assertThrows(RuntimeException.class,
                () -> ConfigurationLoader.getConfiguration("not_existing_file.properties"));
    }
}