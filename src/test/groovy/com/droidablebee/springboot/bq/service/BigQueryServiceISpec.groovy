package com.droidablebee.springboot.bq.service

import com.droidablebee.springboot.bq.BaseIntegrationSpec
import com.google.cloud.bigquery.BigQuery
import com.google.cloud.bigquery.Job
import com.google.cloud.bigquery.JobId
import com.google.cloud.bigquery.JobInfo
import com.google.cloud.bigquery.QueryJobConfiguration
import com.google.cloud.bigquery.TableId
import com.google.cloud.bigquery.TableResult
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class BigQueryServiceISpec extends BaseIntegrationSpec {

    @Autowired
    BigQuery bigQuery

    @Autowired
    BigQueryService bigQueryService

    @Shared
    String project = "test-project"

    @Shared
    String dataset = "dataset"

    def "Execute Job.getQueryResults with explicit DestinationTable set"() {
        given:
        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration
            .newBuilder("SELECT * FROM dataset.table")
            .setDestinationTable(destinationTable)
            .build()

        JobId jobId = JobId.newBuilder().setProject(project).setRandomJob().build()
        Job job = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration)
            .setJobId(jobId)
            .build())

        when:
        TableResult tableResult = job.getQueryResults()
        List<String> results = tableResult.streamAll().map(d -> d.toString()).toList()

        then:
        results.size() == 2

        where:
        destinationTable << [null, TableId.of(project, dataset, "temp_table")]
    }

    def "query as Job returns expected result"() {

        when:
        List<String> result = bigQueryService.queryAsJob()

        then:
        result.size() == 2
    }
}

