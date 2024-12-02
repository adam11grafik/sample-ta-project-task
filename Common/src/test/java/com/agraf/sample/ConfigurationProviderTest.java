package com.agraf.sample;

import org.apache.commons.configuration2.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationProviderTest {

    public static final String PROPERTY_ENV_NAME = "envName";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        Assertions.assertNotNull(ConfigurationProvider.getInstance("env"));
    }

    @Test
    void getInstanceWhenNotExistingEnvNameIsSet() {
        Assertions.assertThrows(RuntimeException.class, () -> ConfigurationProvider.getInstance("invalidEnvName"));
    }

    @Test
    void getConfiguration() {
        Configuration configuration = ConfigurationProvider.getInstance("env").getConfiguration();
        Assertions.assertNotNull(configuration);
        Assertions.assertEquals(null, configuration.getString(PROPERTY_ENV_NAME));
        Assertions.assertEquals("value", configuration.getString("key"));
        Assertions.assertEquals("value2", configuration.getString("key2"));
        Assertions.assertEquals(null, configuration.getString("key_not_existing"));
    }

    @Test
    void setSystemProperties() {
        System.setProperty("key2", "updated value2");

        ConfigurationProvider configurationProvider = ConfigurationProvider.getInstance("env");
        configurationProvider.setSystemProperties();
        Configuration configuration = configurationProvider.getConfiguration();
        Assertions.assertNotNull(configuration);
        Assertions.assertEquals("value", configuration.getString("key"));
        Assertions.assertEquals("updated value2", configuration.getString("key2"));
    }
}