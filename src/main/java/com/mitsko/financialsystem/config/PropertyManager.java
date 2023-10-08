package com.mitsko.financialsystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);

    private static final PropertyManager instance = new PropertyManager();

    private Properties properties;

    public static PropertyManager getInstance() {
        return instance;
    }

    public String getValue(String parameter) throws IOException {
        if (properties == null) {
            initProperties();
        }
        String value = properties.getProperty(parameter);
        logger.info("Get {} property: {}", parameter, value);
        return value;
    }

    public void initProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileReader("src/main/resources/application.properties"));
    }
}
