package com.ratz.bonsaicorner.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends RuntimeException {

  public ResourceAlreadyExistException(String ex) {
    super(ex);
  }

  public ResourceAlreadyExistException() {
  }
}
