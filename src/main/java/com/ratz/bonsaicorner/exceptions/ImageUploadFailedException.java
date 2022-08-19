package com.ratz.bonsaicorner.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ImageUploadFailedException extends RuntimeException {

  public ImageUploadFailedException(String ex) {
    super(ex);
  }
}
