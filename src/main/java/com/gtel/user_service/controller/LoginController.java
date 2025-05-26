package com.gtel.user_service.controller;

import com.gtel.user_service.model.request.LoginRequest;
import com.gtel.user_service.model.response.LoginResponse;
import com.gtel.user_service.model.response.ResponseWrapper;
import com.gtel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ResponseWrapper> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(new ResponseWrapper(userService.login(loginRequest)));
    }
}
