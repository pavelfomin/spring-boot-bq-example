package com.droidablebee.springboot.bq.config;

import com.google.auth.Credentials;
import com.google.cloud.NoCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestBigQueryConfiguration {

    @Bean
    @Primary
    Credentials testCredentials() {

        return NoCredentials.getInstance();
    }

}
