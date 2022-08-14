package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import com.ratz.bonsaicorner.DTO.security.SignUpDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {


  ResponseEntity singIn(AccountCredentialsDTO data);

  ResponseEntity refreshToken(String username, String refreshToken);

  void registerUser(SignUpDTO signUpDTO);
}
