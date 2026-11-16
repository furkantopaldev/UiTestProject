package com.automationexercise.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * config.properties dosyasını okur.
 * Aynı isimde bir -D sistem özelliği verilirse dosyadaki değerin yerine o kullanılır.
 */
public final class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (in == null) {
                throw new IllegalStateException(CONFIG_FILE + " classpath'te bulunamadı (src/test/resources)");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException(CONFIG_FILE + " okunamadı", e);
        }
        return props;
    }

    public static String get(String key) {
        String value = System.getProperty(key, PROPERTIES.getProperty(key));
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Yapılandırma anahtarı tanımlı değil: " + key);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
