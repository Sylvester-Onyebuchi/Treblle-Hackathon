package com.sylvester.trebllehackathon.service;

import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.repository.ApiUnsuccessfulLogsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ApiUnSuccessfulLogsService {

    public void save(ApiUnsuccessfulLogs apiUnsuccessfulLogs);

    List<ApiUnsuccessfulLogs> findAll();

    List<ApiUnsuccessfulLogs> findAllByDetectedAt(LocalDateTime createdAt);
    List<ApiUnsuccessfulLogs> findAllByResponseTime(long responseTime);
    List<ApiUnsuccessfulLogs> findAllByMethod(String method);
    List<ApiUnsuccessfulLogs> findAllByDetectedAtBefore(LocalDateTime createdAt);
    List<ApiUnsuccessfulLogs> findAllByDetectedAtAfter(LocalDateTime createdAt);
    List<ApiUnsuccessfulLogs> findAllByResponseTimeGreaterThan(long responseTime);
    List<ApiUnsuccessfulLogs> findAllByResponseTimeLessThan(long responseTime);

    List<ApiUnsuccessfulLogs> findAllByResponseCode(int responseCode);




}
