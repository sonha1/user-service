package com.gtel.user_service.controller;

import com.gtel.user_service.model.request.RegisterRequest;
import com.gtel.user_service.model.response.ResponseWrapper;
import com.gtel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ResponseWrapper> register(RegisterRequest request) {
        return ResponseEntity.ok(new ResponseWrapper(userService.register(request)));
    }

}
