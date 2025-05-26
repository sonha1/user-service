package com.gtel.user_service.apo;

public class UnauthorizedException extends IllegalArgumentException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
