package de.fraunhofer.iem.springbench.sessionattributes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SessionAttributesApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SessionAttributesApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SessionAttributesApplication.class, args);
    }

}
