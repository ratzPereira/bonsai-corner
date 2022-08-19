package com.ratz.bonsaicorner.DTO.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class PasswordChangeDTO {


  @Email
  private String email;

  @Size(min = 7, max = 255)
  private String newPassword;

  @Size(min = 7, max = 255)
  private String oldPassword;
}
