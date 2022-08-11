package com.ratz.bonsaicorner.enums;


import java.util.stream.Stream;

public enum InterventionStatus {
  COMPLETED, IN_PROGRESS, TODO;

  private int codeNumber;

  InterventionStatus() {
  }

  public int getCodeNumber() {
    return codeNumber;
  }

  public static InterventionStatus of(int codeNumber) {
    return Stream.of(InterventionStatus.values())
        .filter(p -> p.getCodeNumber() == codeNumber)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}

