package de.fraunhofer.iem.springbench.beanwithclassxmlconfiguration.beans;

import java.util.HashMap;

public class DefaultEncryptSettingsBean {
    public HashMap<String, String> getServerDefaultConfiguration() {
        HashMap<String, String> defaultConfig = getSettings();

        defaultConfig.put("encoder", "base64");

        return defaultConfig;
    }

    private HashMap<String, String> getSettings() {
        HashMap<String, String> settings = new HashMap<>();

        settings.put("crypto_algorithm", "rsa");
        settings.put("key_size", "256");

        return settings;
    }
}
