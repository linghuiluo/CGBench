package de.fraunhofer.iem.springbench.springmvcwithjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringMvcWithJspApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringMvcWithJspApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcWithJspApplication.class, args);
    }

}
