package com.ratz.bonsaicorner.DTO.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpDTO {

  @Size(min = 3, max = 25)
  private String firstName;

  @Size(min = 3, max = 25)
  private String lastName;

  @Size(min = 3, max = 25)
  private String userName;

  @Email
  @NotEmpty
  private String email;

  @Size(min = 7, max = 255)
  private String password;
}
