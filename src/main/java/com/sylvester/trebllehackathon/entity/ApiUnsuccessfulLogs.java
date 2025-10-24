package com.sylvester.trebllehackathon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "UnsuccessfullLogs")
public class ApiUnsuccessfulLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String errorType;
    private String method;
    private String path;
    private int responseCode;
    private long responseTime;
    private String description;
    private LocalDateTime detectedAt;

    @OneToOne
    @JoinColumn(name = "Api_success_logs_id")
    @JsonIgnore
    private ApiSuccessLogs apiSuccessLogs;


}
