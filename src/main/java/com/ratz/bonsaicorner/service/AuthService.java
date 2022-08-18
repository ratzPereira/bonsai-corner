package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.ForgotPasswordDTO;
import com.ratz.bonsaicorner.DTO.PasswordChangeDTO;
import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import com.ratz.bonsaicorner.DTO.security.SignUpDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {


  ResponseEntity singIn(AccountCredentialsDTO data);

  ResponseEntity refreshToken(String username, String refreshToken);

  void registerUser(SignUpDTO signUpDTO);

  void changeUserPassword(PasswordChangeDTO passwordChangeDTO);

  void userForgotPassword(ForgotPasswordDTO forgotPasswordDTO);
}
