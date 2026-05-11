package com.booker.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()  // CI/CD ortamında env var'dan okur
            .load();

    public static String get(String key) {
        // Önce sistem env'e bak (CI/CD), sonra .env dosyasına
        String value = System.getenv(key);
        return (value != null) ? value : dotenv.get(key);
    }

    public static String getBaseUrl()  { return get("BASE_URL"); }
    public static String getUsername() { return get("ADMIN_USERNAME"); }
    public static String getPassword() { return get("ADMIN_PASSWORD"); }
}