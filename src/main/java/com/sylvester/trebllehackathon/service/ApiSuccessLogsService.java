package com.sylvester.trebllehackathon.service;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ApiSuccessLogsService {

    void save(ApiSuccessLogs apiSuccessLogs);

    List<ApiSuccessLogs> getApiSuccessLogs();

     List<ApiSuccessLogs> findApiSuccessLogsByMethodContainingIgnoreCase(String method);

     List<ApiSuccessLogs> findApiSuccessLogsByResponseCode(int responseCode);

     List<ApiSuccessLogs> findApiSuccessLogsByCreatedAtBefore(LocalDateTime createdAt);

     List<ApiSuccessLogs> findApiSuccessLogsByCreatedAtAfter(LocalDateTime createdAt);

     List<ApiSuccessLogs> findApiSuccessLogsByResponseTimeGreaterThan(long responseTimeIsGreaterThan);

     List<ApiSuccessLogs> findApiSuccessLogsByResponseTimeLessThan(long responseTimeIsLessThan);

     List<ApiSuccessLogs> findApiSuccessLogsByResponseTime(long responseTime);

     List<ApiSuccessLogs> findApiSuccessLogsByCreatedAt(LocalDateTime createdAt);

}
