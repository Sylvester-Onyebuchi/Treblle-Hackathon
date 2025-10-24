package com.sylvester.trebllehackathon.controller;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.service.ApiSuccessLogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/success/logs")
@RequiredArgsConstructor
@Tag(name = "Failed APIs", description = "Failed related API logs")
public class ApiSuccessController {

    private final ApiSuccessLogsService apiSuccessLogsService;

    @PostMapping("/success-logs")
    public ResponseEntity<Void> saveSuccessLogs(@RequestBody ApiSuccessLogs apiSuccessLogs) {
        apiSuccessLogsService.save(apiSuccessLogs);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get all the success calls")
    @ApiResponse(responseCode = "200", description = "List all the success API calls")
    @GetMapping("/all-success-logs")
    public ResponseEntity<List<ApiSuccessLogs>> getAllSuccessLogs() {
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.getApiSuccessLogs();
        return ResponseEntity.ok().body(apiSuccessLogs);

    }



    @Operation(summary = "Fetching the Success logs by the method")
    @GetMapping("/filter/method")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByMethodContainingIgnoreCase(@RequestParam String method) {
        List<ApiSuccessLogs> apiSuccessLogs =  apiSuccessLogsService.findApiSuccessLogsByMethodContainingIgnoreCase(method);
        return ResponseEntity.ok().body(apiSuccessLogs);

    }


    @Operation(summary = "Fetching the Success logs by createdAT before a specific time and date")
    @GetMapping("/sort/before")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByCreatedAtBefore(@RequestParam String createdAt) {

        LocalDateTime createdAT = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByCreatedAtBefore(createdAT);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by createdAT")
    @GetMapping("/sort/")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByCreatedAt(@RequestParam String createdAt) {
        LocalDateTime createdAT = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByCreatedAt(createdAT);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by createdAT after a specific time and date ")
    @GetMapping("/sort/after")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByCreatedAtAfter(@RequestParam String createdAt) {

        LocalDateTime createdAT = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByCreatedAtAfter(createdAT);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by Response Time Greater than a given milliseconds")
    @GetMapping("/sort/greater/{responseTime}")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByResponseTimeGreaterThan(@PathVariable long responseTime) {
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByResponseTimeGreaterThan(responseTime);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by Response Time Greater than a given milliseconds")
    @GetMapping("/sort/less/{responseTime}")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByResponseTimeLessThan(@PathVariable long responseTime) {
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByResponseTimeLessThan(responseTime);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by Response Time")
    @GetMapping("/sort/{responseTime}")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByResponseTime(@PathVariable long responseTime) {
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByResponseTime(responseTime);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


    @Operation(summary = "Fetching the Success logs by Response Code")
    @GetMapping("/filter/{responseCode}")
    public ResponseEntity<List<ApiSuccessLogs>> getSuccessLogsByResponseCode(@PathVariable int responseCode) {
        List<ApiSuccessLogs> apiSuccessLogs = apiSuccessLogsService.findApiSuccessLogsByResponseCode(responseCode);
        return ResponseEntity.ok().body(apiSuccessLogs);
    }


}
