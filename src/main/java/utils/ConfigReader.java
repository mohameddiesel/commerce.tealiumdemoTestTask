package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads configuration from .properties files
 */
public class ConfigReader {
    private static Properties configProps;
    private static Properties testDataProps;

    static {
        configProps = loadProperties("config.properties");
        testDataProps = loadProperties("testdata.properties");
    }

    private static Properties loadProperties(String fileName) {
        Properties props = new Properties();

        // 1. Try to load from classpath (src/main/resources)
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is != null) {
                props.load(is);
                return props;
            }
        } catch (IOException ignored) {
        }

        // 2. Fallback: try src/main/java/resources
        try (InputStream fis = new FileInputStream("src/main/java/resources/" + fileName)) {
            props.load(fis);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Property file '" + fileName + "' not found in classpath or src/main/java/resources", e);
        }
    }

    public static String getConfigProperty(String key) {
        return configProps.getProperty(key);
    }

    public static String getTestDataProperty(String key) {
        return testDataProps.getProperty(key);
    }
}
