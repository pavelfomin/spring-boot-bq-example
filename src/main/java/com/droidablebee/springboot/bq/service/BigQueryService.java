package com.droidablebee.springboot.bq.service;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.AbstractCollection;
import java.util.List;

@Service
public class BigQueryService {

    @Autowired
    BigQuery bigQuery;

    @Value("${app.query.project}")
    String project;

    @SneakyThrows
    public List<String> queryAsJob() {

        QueryJobConfiguration queryJobConfiguration = QueryJobConfiguration
            .newBuilder("SELECT * FROM dataset.table")
            .build();

        JobId jobId = JobId.newBuilder().setProject(project).setRandomJob().build();
        Job job = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration)
            .setJobId(jobId)
            .build()
        );

        TableResult tableResult = job.getQueryResults();

        return tableResult.streamAll().map(AbstractCollection::toString).toList();
    }

}
