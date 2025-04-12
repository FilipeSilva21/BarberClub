package com.BarberClub.infra.ExceptionHandler.Exceptions;

public class ServiceNotFoundException extends RuntimeException {
  public ServiceNotFoundException(String message) {
    super(message);
  }
}
