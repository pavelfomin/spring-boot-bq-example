# Spring Boot BQ example
Using `ghcr.io/goccy/bigquery-emulator` docker image for BQ integration testing and local development.

The [bigquery-emulator](https://github.com/goccy/bigquery-emulator) uses a local sqlite database to emulate BigQuery.
It is not a full-featured emulator, but it does support basic queries and table operations.

The test file `./src/test/resources/data/bq-simulator-data.yaml` is used to initialize the emulator with
the necessary tables and data for local development.

## Running the application locally
```shell
docker-compose up -d
./gradlew bootRun --args="--spring.profiles.active=local"
```

## Running tests
```shell
./gradlew clean build
```

## Upgrade gradlq wrapper
* change version in gradle-wrapper.properties: `./gradlew wrapper --gradle-version 9.1.0`
* update gradlew: `./gradlew wrapper`
