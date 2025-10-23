package com.sylvester.trebllehackathon.serviceImplementation;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.repository.ApiSuccessLogsRepository;
import com.sylvester.trebllehackathon.service.ApiSuccessLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiSuccessLogServiceImplementation implements ApiSuccessLogsService {

    private final ApiSuccessLogsRepository apiSuccessLogsRepository;


    @Override
    public void save(ApiSuccessLogs apiSuccessLogs) {
        apiSuccessLogsRepository.save(apiSuccessLogs);

    }

    @Override
    public List<ApiSuccessLogs> getApiSuccessLogs() {
        return apiSuccessLogsRepository.findAll();
    }

    public List<ApiSuccessLogs> findApiSuccessLogsByMethodContainingIgnoreCase(String method) {
        return apiSuccessLogsRepository.findApiSuccessLogsByMethodContainingIgnoreCase(method);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByResponseCode(int responseCode) {
        return apiSuccessLogsRepository.findApiSuccessLogsByResponseCode(responseCode);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByCreatedAtBefore(LocalDateTime createdAt) {
        return apiSuccessLogsRepository.findAllByCreatedAtBefore(createdAt);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByCreatedAtAfter(LocalDateTime createdAt) {
        return apiSuccessLogsRepository.findAllByCreatedAtAfter(createdAt);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByResponseTimeGreaterThan(long responseTimeIsGreaterThan) {
        return apiSuccessLogsRepository.findAllByResponseTimeGreaterThan(responseTimeIsGreaterThan);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByResponseTimeLessThan(long responseTimeIsLessThan) {
        return apiSuccessLogsRepository.findAllByResponseTimeLessThan(responseTimeIsLessThan);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByResponseTime(long responseTime) {
        return apiSuccessLogsRepository.findAllByResponseTime(responseTime);
    }

    @Override
    public List<ApiSuccessLogs> findApiSuccessLogsByCreatedAt(LocalDateTime createdAt) {
        return apiSuccessLogsRepository.findAllByCreatedAt(createdAt);
    }
}
