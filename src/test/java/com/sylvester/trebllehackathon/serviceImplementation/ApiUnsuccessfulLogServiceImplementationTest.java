package com.sylvester.trebllehackathon.serviceImplementation;

import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.repository.ApiUnsuccessfulLogsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiUnsuccessfulLogServiceImplementationTest {

    @Mock
    private ApiUnsuccessfulLogsRepository unsuccessfulLogsRepository;

    @InjectMocks
    private ApiUnsuccessfulLogServiceImplementation unsuccessfulLogServiceImplementation;

    private List<ApiUnsuccessfulLogs> apiUnsuccessfulLogs;

    @BeforeEach
    void setUp() {

        apiUnsuccessfulLogs = Collections.singletonList(
                ApiUnsuccessfulLogs.builder()
                        .id(1L)
                        .method("GET")
                        .errorType("Server Error")
                        .responseTime(1L)
                        .description("Description")
                        .responseCode(404)
                        .detectedAt(LocalDateTime.now())
                        .build());

    }

    @Test
    void findAllFailedLogsTest() {
        when(unsuccessfulLogsRepository.findAll()).thenReturn(apiUnsuccessfulLogs);
        List<ApiUnsuccessfulLogs> result = unsuccessfulLogServiceImplementation.findAll();
        assertEquals(apiUnsuccessfulLogs, result);
        verify(unsuccessfulLogsRepository, times(1)).findAll();
        verifyNoMoreInteractions(unsuccessfulLogsRepository);
    }

    @Test
    void findAllFailedLogsByMethodTest() {
        when(unsuccessfulLogsRepository.findAllByMethodContainingIgnoreCase("TEST")).thenReturn(apiUnsuccessfulLogs);
        List<ApiUnsuccessfulLogs> result = unsuccessfulLogServiceImplementation.findAllByMethod("TEST");
        assertEquals(apiUnsuccessfulLogs, result);
        verify(unsuccessfulLogsRepository, times(1)).findAllByMethodContainingIgnoreCase("TEST");
        verifyNoMoreInteractions(unsuccessfulLogsRepository);
    }

    @Test
    void findAllFailedLogsByResponseTimeTest() {
        when(unsuccessfulLogsRepository.findAllByResponseTime(1L)).thenReturn(apiUnsuccessfulLogs);
        List<ApiUnsuccessfulLogs> result = unsuccessfulLogServiceImplementation.findAllByResponseTime(1L);
        assertEquals(apiUnsuccessfulLogs, result);
        verify(unsuccessfulLogsRepository, times(1)).findAllByResponseTime(1L);
        verifyNoMoreInteractions(unsuccessfulLogsRepository);
    }

    @Test
    void findAllFailedLogsByDetectedAtTest() {
        when(unsuccessfulLogsRepository.findAllByDetectedAt(any())).thenReturn(apiUnsuccessfulLogs);
        List<ApiUnsuccessfulLogs> result = unsuccessfulLogServiceImplementation.findAllByDetectedAt(any());
        assertEquals(apiUnsuccessfulLogs, result);
        verify(unsuccessfulLogsRepository, times(1)).findAllByDetectedAt(any());
        verifyNoMoreInteractions(unsuccessfulLogsRepository);
    }

    @Test
    void findAllFailedLogsByResponseCodeTest() {
        when(unsuccessfulLogsRepository.findAllByResponseCode(anyInt())).thenReturn(apiUnsuccessfulLogs);
        List<ApiUnsuccessfulLogs> result = unsuccessfulLogServiceImplementation.findAllByResponseCode(anyInt());
        assertEquals(apiUnsuccessfulLogs, result);
        verify(unsuccessfulLogsRepository, times(1)).findAllByResponseCode(anyInt());
        verifyNoMoreInteractions(unsuccessfulLogsRepository);
    }

}