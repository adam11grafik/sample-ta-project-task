package com.agraf.sample;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationConverter;

import java.util.Properties;

public class ConfigurationProvider {

    private static Configuration configuration;

    private ConfigurationProvider(String envName) {
        String propertiesFileName = envName + ".properties";
        configuration = ConfigurationLoader.getConfiguration(propertiesFileName);
    }

    public static ConfigurationProvider getInstance(String envName) {
        return new ConfigurationProvider(envName);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setSystemProperties() {
        Properties properties = ConfigurationConverter.getProperties(configuration);
        Properties systemProperties = System.getProperties();
        for (String propertyName : properties.stringPropertyNames()) {
            if (systemProperties.containsKey(propertyName)) {
                String systemProperty = systemProperties.getProperty(propertyName);
                if (systemProperty != null) {
                    properties.setProperty(propertyName, systemProperty);
                    configuration.setProperty(propertyName, systemProperty);
                }
            }
        }
    }
}