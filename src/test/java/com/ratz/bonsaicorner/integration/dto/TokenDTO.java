package com.ratz.bonsaicorner.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
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
