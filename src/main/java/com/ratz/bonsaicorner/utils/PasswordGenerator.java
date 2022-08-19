package com.ratz.bonsaicorner.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public final class PasswordGenerator {
  private SecureRandom random = new SecureRandom();

  @Bean
  public String nextPassword() {
    return new BigInteger(130, random).toString(32);
  }
}
