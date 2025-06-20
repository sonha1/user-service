package com.gtel.user_service.model.request;

import lombok.Data;

@Data
public class SendNotificationRequest {
    private String  message;
    private Integer type;
}
