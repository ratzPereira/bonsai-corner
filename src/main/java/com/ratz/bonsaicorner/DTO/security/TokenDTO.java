package com.ratz.bonsaicorner.DTO.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = -4309830824311976270L;

  private String username;
  private Boolean authenticated;
  private Date created;
  private Date expiration;
  private String accessToken;
  private String refreshToken;

}
