package com.gtel.user_service.model.request;

import lombok.Data;

@Data
public class VerifyTokenRequest {

    private String url;
    private String method;
    private String system;
    private Integer deviceType; //0: web, 1: app
    private String clientIp;

}
