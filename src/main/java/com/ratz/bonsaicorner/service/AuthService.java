package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {


  ResponseEntity singIn(AccountCredentialsDTO data);

  ResponseEntity refreshToken(String username, String refreshToken);
}
