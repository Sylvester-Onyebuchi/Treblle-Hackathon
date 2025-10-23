package com.sylvester.trebllehackathon.serviceImplementation;

import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.repository.ApiUnsuccessfulLogsRepository;
import com.sylvester.trebllehackathon.service.ApiUnSuccessfulLogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiUnsuccessfulLogServiceImplementation implements ApiUnSuccessfulLogsService {

    private final ApiUnsuccessfulLogsRepository apiUnsuccessfulLogsRepository;

    @Override
    public void save(ApiUnsuccessfulLogs unsuccessfulLogs) {
        apiUnsuccessfulLogsRepository.save(unsuccessfulLogs);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAll() {

        return apiUnsuccessfulLogsRepository.findAll();
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByDetectedAt(LocalDateTime createdAt) {
        return apiUnsuccessfulLogsRepository.findAllByDetectedAt(createdAt);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByResponseTime(long responseTime) {
        return apiUnsuccessfulLogsRepository.findAllByResponseTime(responseTime);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByMethod(String method) {
        return apiUnsuccessfulLogsRepository.findAllByMethodContainingIgnoreCase(method);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByDetectedAtBefore(LocalDateTime createdAt) {
        return apiUnsuccessfulLogsRepository.findAllByDetectedAtBefore(createdAt);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByDetectedAtAfter(LocalDateTime createdAt) {
        return apiUnsuccessfulLogsRepository.findAllByDetectedAtAfter(createdAt);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByResponseTimeGreaterThan(long responseTime) {
        return apiUnsuccessfulLogsRepository.findAllByResponseTimeGreaterThan(responseTime);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByResponseTimeLessThan(long responseTime) {
        return apiUnsuccessfulLogsRepository.findAllByResponseTimeLessThan(responseTime);
    }

    @Override
    public List<ApiUnsuccessfulLogs> findAllByResponseCode(int responseCode) {
        return apiUnsuccessfulLogsRepository.findAllByResponseCode(responseCode);
    }
}
