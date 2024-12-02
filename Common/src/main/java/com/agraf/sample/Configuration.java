package com.agraf.sample;

public class Configuration {

    public static final String PROPERTY_ENV_NAME = "envName";

    public static final String DEFAULT_ENV_NAME = "TST";

    private ConfigurationProvider configurationProvider;

    private Configuration(String envName) {
        configurationProvider = ConfigurationProvider.getInstance(envName);
        configurationProvider.setSystemProperties();
        configurationProvider.getConfiguration().setProperty(PROPERTY_ENV_NAME, envName);
    }

    public static Configuration getInstance() {
        return getInstance(getPropertyEnvNameOrDefault());
    }

    public static Configuration getInstance(String envName) {
        return new Configuration(envName);
    }

    public String getEnvName() {
        return getString(PROPERTY_ENV_NAME);
    }

    private String getString(String key) {
        return configurationProvider.getConfiguration().getString(key);
    }

    private static String getPropertyEnvNameOrDefault() {
        String envName = System.getProperty(PROPERTY_ENV_NAME);
        if (envName == null || envName.isEmpty()) {
            envName = DEFAULT_ENV_NAME;
        }
        return envName;
    }
}