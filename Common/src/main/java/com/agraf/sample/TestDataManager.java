package com.agraf.sample;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestDataManager {

    private final static String DATA_BASE_FOLDER = "data";

    private String envName;

    private TestDataManager() {
        envName = Configuration.getInstance().getEnvName();
    }

    public static TestDataManager getInstance() {
        return new TestDataManager();
    }

    public String readString(String fileName) {
        Path path = generateTestDataPath(fileName);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Error when read file: " + path, e);
        }
    }

    private Path generateTestDataPath(String fileName) {
        String resourceName = DATA_BASE_FOLDER + "/" + envName + "/" + fileName;
        URL url = this.getClass().getClassLoader().getResource(resourceName);
        if (url == null) {
            throw new RuntimeException("Resource file : '" + resourceName + "' not found");
        }
        try {
            return Path.of(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("URISyntaxException for resource name: " + resourceName, e);
        }
    }
}
