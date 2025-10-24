package com.sylvester.trebllehackathon.dto;

import lombok.Builder;
import lombok.Data;

@Data

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;

    public CreateUserRequest(String aliceSmith, String mail) {
    }
}
