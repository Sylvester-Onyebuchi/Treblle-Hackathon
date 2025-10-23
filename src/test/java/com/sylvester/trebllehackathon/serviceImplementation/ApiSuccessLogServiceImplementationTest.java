package com.sylvester.trebllehackathon.serviceImplementation;

import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.repository.ApiSuccessLogsRepository;
import com.sylvester.trebllehackathon.service.ApiSuccessLogsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiSuccessLogServiceImplementationTest {

    @Mock
    private ApiSuccessLogsRepository apiSuccessLogsRepository;


    @InjectMocks
    private ApiSuccessLogServiceImplementation successLogServiceImplementation;


    private List<ApiSuccessLogs> successLogs;

    @BeforeEach
    void setUp() {
        successLogs = Collections.singletonList(ApiSuccessLogs.builder()
                .id(1L)
                .method("GET")
                .path("/users")
                .responseCode(200)
                .responseTime(1L)
                .createdAt(LocalDateTime.now())
                .build());


    }

    @Test
    void getApiSuccessLogsTest(){

        when(apiSuccessLogsRepository.findAll()).thenReturn(successLogs);

        List<ApiSuccessLogs> result = successLogServiceImplementation.getApiSuccessLogs();

        assertEquals(successLogs, result);

        verify(apiSuccessLogsRepository, times(1)).findAll();
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }

    @Test
    void getApiSuccessLogsByMethodTest(){
        when(apiSuccessLogsRepository.findApiSuccessLogsByMethodContainingIgnoreCase(anyString())).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByMethodContainingIgnoreCase(anyString());
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findApiSuccessLogsByMethodContainingIgnoreCase(anyString());
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }


    @Test
    void getApiSuccessLogsByCreatedAtTest(){
        when(apiSuccessLogsRepository.findAllByCreatedAt(any())).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByCreatedAt(any());
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findAllByCreatedAt(any());
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }
    @Test
    void findApiSuccessLogsByCreatedAtBeforeTest(){
        when(apiSuccessLogsRepository.findAllByCreatedAtBefore(any())).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByCreatedAtBefore(any());
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findAllByCreatedAtBefore(any());
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }

    @Test
    void findApiSuccessLogsByCreatedAtAfterTest(){
        when(apiSuccessLogsRepository.findAllByCreatedAtAfter(any())).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByCreatedAtAfter(any());
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findAllByCreatedAtAfter(any());
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }

    @Test
    void findApiSuccessLogsByResponseTimeTest(){
        when(apiSuccessLogsRepository.findAllByResponseTime(1L)).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByResponseTime(1L);
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findAllByResponseTime(1L);
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }

    @Test
    void findApiSuccessByResponseCodeTest(){
        when(apiSuccessLogsRepository.findApiSuccessLogsByResponseCode(anyInt())).thenReturn(successLogs);
        List<ApiSuccessLogs> result = successLogServiceImplementation.findApiSuccessLogsByResponseCode(anyInt());
        assertEquals(successLogs, result);
        verify(apiSuccessLogsRepository, times(1)).findApiSuccessLogsByResponseCode(anyInt());
        verifyNoMoreInteractions(apiSuccessLogsRepository);
    }

}