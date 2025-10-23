package com.sylvester.trebllehackathon.aspect;


import com.sylvester.trebllehackathon.entity.ApiSuccessLogs;
import com.sylvester.trebllehackathon.entity.ApiUnsuccessfulLogs;
import com.sylvester.trebllehackathon.serviceImplementation.ApiSuccessLogServiceImplementation;
import com.sylvester.trebllehackathon.serviceImplementation.ApiUnsuccessfulLogServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class ApiLogAspect {

    private final ApiSuccessLogServiceImplementation apiSuccessLogs;
    private final ApiUnsuccessfulLogServiceImplementation apiUnsuccessfulLogs;

    @Around("execution(* com.sylvester.trebllehackathon.controller..*(..))")
    public Object apiLogs(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();


        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


        if (servletRequestAttributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();


        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            int statusCode = HttpStatus.OK.value();
            if (result instanceof ResponseEntity<?> response) {
                statusCode = response.getStatusCode().value();
            }

            saveLogs(request,statusCode,duration,null);

            return result;
        }catch (Exception e){
            long duration = System.currentTimeMillis() - startTime;
            int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            String errorMessage = e.getMessage();
            if (e instanceof ResponseStatusException rse){
                statusCode = rse.getStatusCode().value();
                errorMessage = rse.getReason();
            }
            saveLogs(request,statusCode,duration,errorMessage);
            throw e;
        }

    }

    private void saveLogs( HttpServletRequest request, int statusCode, long duration, String message) {
        ApiSuccessLogs successLogs = ApiSuccessLogs.builder()
                .method(request.getMethod())
                .path(request.getRequestURI())
                .responseCode(statusCode)
                .responseTime(duration)
                .build();
        apiSuccessLogs.save(successLogs);

        if (statusCode >= 400 || duration > 2000){
            String errorType = statusCode >= 400 ? "Server Error" : "Slow Response";
            String description = statusCode >= 400
                    ? "Error: " + message
                    : "Slow response (" + duration + " ms)";


            ApiUnsuccessfulLogs unsuccessful = ApiUnsuccessfulLogs.builder()
                    .errorType(errorType)
                    .method(request.getMethod())
                    .description(description)
                    .responseCode(statusCode)
                    .responseTime(duration)
                    .detectedAt(LocalDateTime.now())
                    .build();
            apiUnsuccessfulLogs.save(unsuccessful);
        }


    }
}
