package com.droidablebee.springboot.bq

import groovy.util.logging.Slf4j
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.BigQueryEmulatorContainer
import org.testcontainers.utility.MountableFile
import spock.lang.Specification

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class BaseIntegrationSpec extends Specification {

    protected final static BigQueryEmulatorContainer container = new BigQueryEmulatorContainer("ghcr.io/goccy/bigquery-emulator:0.6.6")
    static {
        container
            .withCopyFileToContainer(
                MountableFile.forClasspathResource("/data"),
                "/test-data"
            )
            .withCommand(
                "--project=test-project",
                "--data-from-yaml=/test-data/bq-simulator-data.yaml"
            )
            .start()
    }

    @DynamicPropertySource
    static void configureTestContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("app.query.host", container::getEmulatorHttpEndpoint)
    }
}
