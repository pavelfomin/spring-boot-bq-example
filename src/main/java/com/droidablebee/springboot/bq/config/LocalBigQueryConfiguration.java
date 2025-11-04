package com.droidablebee.springboot.bq.config;

import com.google.auth.Credentials;
import com.google.cloud.NoCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
public class LocalBigQueryConfiguration {

    @Bean
    @Primary
    Credentials localCredentials() {

        return NoCredentials.getInstance();
    }

}
