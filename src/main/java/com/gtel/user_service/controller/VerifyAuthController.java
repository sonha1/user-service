package com.gtel.user_service.controller;


import com.gtel.user_service.model.request.VerifyTokenRequest;
import com.gtel.user_service.model.response.ResponseWrapper;
import com.gtel.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("verify-request")
@RequiredArgsConstructor
public class VerifyAuthController {

    private final UserService userService;


    @PostMapping("")
    public ResponseEntity<ResponseWrapper> verifyToken(@RequestBody VerifyTokenRequest request) {
        return ResponseEntity.ok(new ResponseWrapper(userService.verifyToken(request)));
    }
}
