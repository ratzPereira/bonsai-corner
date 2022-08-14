package com.ratz.bonsaicorner.DTO.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentialsDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -5761938655044589959L;


  private String username;
  private String password;

}
