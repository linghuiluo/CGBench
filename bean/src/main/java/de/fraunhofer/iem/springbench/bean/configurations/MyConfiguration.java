package de.fraunhofer.iem.springbench.bean.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MyConfiguration {
    @Bean("defaultEncryptSettings")
    public HashMap<String, String> getServerDefaultConfiguration() {
        HashMap<String, String> defaultConfig = new HashMap<>();

        defaultConfig.put("crypto_algorithm", "rsa");
        defaultConfig.put("key_size", "256");

        return defaultConfig;
    }
}
