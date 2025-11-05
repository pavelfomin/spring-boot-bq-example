package com.droidablebee.springboot.bq.controller;

import com.droidablebee.springboot.bq.service.BigQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bq")
@RequiredArgsConstructor
@Slf4j
class BigQueryController {

    private final BigQueryService bigQueryService;

    @GetMapping("/query")
    public ResponseEntity<?> query() {

        return new ResponseEntity<>(bigQueryService.queryAsJob(), HttpStatus.OK);
    }

}
