package com.gtel.user_service.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
