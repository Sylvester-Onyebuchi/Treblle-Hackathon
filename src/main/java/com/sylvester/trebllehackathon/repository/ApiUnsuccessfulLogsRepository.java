package com.sylvester.trebllehackathon.repository;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApiUnsuccessfulLogsRepository extends JpaRepository<ApiUnsuccessfulLogs, Long> {
    List<ApiUnsuccessfulLogs> findAllByMethodContainingIgnoreCase(String method);

    List<ApiUnsuccessfulLogs> findAllByResponseCode(int responseCode);

    List<ApiUnsuccessfulLogs> findAllByDetectedAtBefore(LocalDateTime detectedAt);

    List<ApiUnsuccessfulLogs> findAllByDetectedAtAfter(LocalDateTime detectedAtAfter);

    List<ApiUnsuccessfulLogs> findAllByResponseTimeGreaterThan(long responseTimeIsGreaterThan);

    List<ApiUnsuccessfulLogs> findAllByResponseTimeLessThan(long responseTimeIsLessThan);

    List<ApiUnsuccessfulLogs> findAllByResponseTime(long responseTime);

    List<ApiUnsuccessfulLogs> findAllByDetectedAt(LocalDateTime createdAt);
}
