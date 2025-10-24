package com.sylvester.trebllehackathon.controller;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.service.ApiUnSuccessfulLogsService;
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
@RequestMapping("/api/failed/logs")
@RequiredArgsConstructor
@Tag(name = "Success API", description = "Success related API logs")
public class ApiUnsuccessfulController {

    private final ApiUnSuccessfulLogsService apiUnSuccessfulLogsService;

    @Operation(summary = "Get all the failed calls")
    @ApiResponse(responseCode = "200", description = "List all the failed API calls")
    @GetMapping("/all-failed-logs")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getAllApiUnsuccessfulLogs() {
        List<ApiUnsuccessfulLogs> apiUnsuccessfulLogs = apiUnSuccessfulLogsService.findAll();
        return ResponseEntity.ok().body(apiUnsuccessfulLogs);
    }

    @PostMapping("/add-failed-log")
    public ResponseEntity<Void> saveFailedLogs(@RequestBody ApiUnsuccessfulLogs apiUnsuccessfulLogs) {
        apiUnSuccessfulLogsService.save(apiUnsuccessfulLogs);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Fetching the failed logs by the method")
    @GetMapping("/method")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByMethodContainingIgnoreCase(@RequestParam String method) {
        List<ApiUnsuccessfulLogs> failedLogs =  apiUnSuccessfulLogsService.findAllByMethod(method);
        return ResponseEntity.ok().body(failedLogs);

    }

    @Operation(summary = "Fetching the failed logs by detectedAt before a specific time and date")
    @GetMapping("/sort/before")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByDetectedAtBefore(@RequestParam String detectedAt) {

        LocalDateTime detected = LocalDateTime.parse(detectedAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByDetectedAtBefore(detected);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the failed logs by detectedAt")
    @GetMapping("/sort/")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByDetectedAt(@RequestParam String detectedAt) {
        LocalDateTime detected = LocalDateTime.parse(detectedAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByDetectedAt(detected);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the Success logs by detectedAt after a specific time and date ")
    @GetMapping("/sort/after")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByCreatedAtAfter(@RequestParam String detectedAt) {

        LocalDateTime detected = LocalDateTime.parse(detectedAt, DateTimeFormatter.ISO_DATE_TIME);
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByDetectedAtAfter(detected);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the failed logs by Response Time Greater than a given milliseconds")
    @GetMapping("/sort/greater/{responseTime}")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByResponseTimeGreaterThan(@PathVariable long responseTime) {
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByResponseTimeGreaterThan(responseTime);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the failed logs by Response Time less than a given milliseconds")
    @GetMapping("/sort/less/{responseTime}")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByResponseTimeLessThan(@PathVariable long responseTime) {
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByResponseTimeLessThan(responseTime);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the failed logs by Response Time")
    @GetMapping("/sort/{responseTime}")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByResponseTime(@PathVariable long responseTime) {
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByResponseTime(responseTime);
        return ResponseEntity.ok().body(failedLogs);
    }


    @Operation(summary = "Fetching the failed logs by Response Code")
    @GetMapping("/filter/{responseCode}")
    public ResponseEntity<List<ApiUnsuccessfulLogs>> getFailedLogsByResponseCode(@PathVariable int responseCode) {
        List<ApiUnsuccessfulLogs> failedLogs = apiUnSuccessfulLogsService.findAllByResponseCode(responseCode);
        return ResponseEntity.ok().body(failedLogs);
    }

}
