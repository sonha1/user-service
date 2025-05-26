package com.gtel.user_service.model.response;

import com.gtel.user_service.dto.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String refreshToken;
    private UserPrincipal userPrincipal;
    private String prefixToken;
}
