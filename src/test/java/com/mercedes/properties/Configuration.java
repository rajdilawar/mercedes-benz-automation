package com.mercedes.properties;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private Properties configuration;

    public Configuration() throws IOException {
        loadConfig();
    }
    private void loadConfig() throws IOException {
        configuration = new Properties();
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (inputStream == null) {
            System.out.println("config.properties file not found in the classpath.");
        } else {
            final BufferedInputStream stream = new BufferedInputStream(inputStream);
            configuration.load(stream);
        }
    }

    public String baseMercedesUrl() {
        return configuration.getProperty("base_url");
    }
    public String browserName() {
        return configuration.getProperty("browser");
    }
}
