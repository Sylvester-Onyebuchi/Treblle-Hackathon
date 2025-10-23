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
@Table(name = "ApiSuccessLogs")
public class ApiSuccessLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String path;
    private int responseCode;
    private long responseTime;
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "apiSuccessLogs", cascade = CascadeType.ALL)
    @JsonIgnore
    private ApiUnsuccessfulLogs apiUnsuccessfulLogs;

    public ApiSuccessLogs(long l, String get, String s, int i, int i1, LocalDateTime localDateTime) {
    }

    public ApiSuccessLogs(long l, String s, int i, String ok) {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
