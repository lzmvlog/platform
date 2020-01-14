package com.wisdompoint.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author： ShaoJie
 * @data： 2019年12月10日 15:44
 * @Description：
 */
@SpringBootApplication
@EnableScheduling
public class PlatFormApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PlatFormApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PlatFormApplication.class);
    }
}
