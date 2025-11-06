package com.droidablebee.springboot.bq.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BigQueryConfiguration {

    @Value("${app.query.host}")
    private String host;

    @Value("${app.query.project}")
    private String project;

    @Bean
    @SneakyThrows
    @ConditionalOnProperty(name = "app.query.use-default-credentials", havingValue = "true")
    Credentials credentials() {
        return GoogleCredentials.getApplicationDefault();
    }

    @Bean
    BigQuery bigQuery(Credentials credentials) {
        return BigQueryOptions.newBuilder()
            .setCredentials(credentials)
            .setProjectId(project)
            .setHost(host)
            .build()
            .getService();
    }
}
