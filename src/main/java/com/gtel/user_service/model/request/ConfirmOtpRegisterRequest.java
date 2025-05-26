package com.gtel.user_service.model.request;

import lombok.Data;

@Data
public class ConfirmOtpRegisterRequest {
    private String transactionId;
    private String otp;
}
