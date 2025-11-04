package com.droidablebee.springboot.bq

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class ApplicationISpec extends BaseIntegrationSpec {

    @Autowired
    ApplicationContext context

    def "context initializes successfully"() {

        expect:
        context
        context.environment
    }
}
