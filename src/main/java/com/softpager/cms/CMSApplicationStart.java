package com.softpager.cms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CMSApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(CMSApplicationStart.class, args);
    }

}

