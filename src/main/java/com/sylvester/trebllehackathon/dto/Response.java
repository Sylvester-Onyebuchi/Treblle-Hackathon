package com.sylvester.trebllehackathon.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String message;
    private String name;
    private String email;
}
