package com.sylvester.trebllehackathon.repository;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ApiSuccessLogsRepository extends JpaRepository<ApiSuccessLogs, Long> {

    List<ApiSuccessLogs> findApiSuccessLogsByMethodContainingIgnoreCase(String method);

    List<ApiSuccessLogs> findApiSuccessLogsByResponseCode(int responseCode);

    List<ApiSuccessLogs> findAllByCreatedAtBefore(LocalDateTime createdAt);

    List<ApiSuccessLogs> findAllByCreatedAtAfter(LocalDateTime createdAtAfter);

    List<ApiSuccessLogs> findAllByResponseTimeGreaterThan(long responseTimeIsGreaterThan);

    List<ApiSuccessLogs> findAllByResponseTimeLessThan(long responseTimeIsLessThan);

    List<ApiSuccessLogs> findAllByResponseTime(long responseTime);

    List<ApiSuccessLogs> findAllByCreatedAt(LocalDateTime createdAt);


}
