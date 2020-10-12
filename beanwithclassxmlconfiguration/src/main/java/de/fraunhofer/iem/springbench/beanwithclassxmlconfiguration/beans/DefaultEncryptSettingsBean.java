package de.fraunhofer.iem.springbench.beanwithclassxmlconfiguration.beans;

import java.util.HashMap;

public class DefaultEncryptSettingsBean {
    public HashMap<String, String> getServerDefaultConfiguration() {
        HashMap<String, String> defaultConfig = new HashMap<>();

        defaultConfig.put("crypto_algorithm", "rsa");
        defaultConfig.put("key_size", "256");

        return defaultConfig;
    }
}
