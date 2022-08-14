package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import com.ratz.bonsaicorner.DTO.security.TokenDTO;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.UserRepository;
import com.ratz.bonsaicorner.security.JwtTokenProvider;
import com.ratz.bonsaicorner.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private AuthenticationManager authenticationManager;
  private JwtTokenProvider tokenProvider;
  private UserRepository userRepository;

  @Override
  @SuppressWarnings("rawtypes")
  public ResponseEntity singIn(AccountCredentialsDTO data) {

    try {
      String username = data.getUsername();
      String password = data.getPassword();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));

      User user = userRepository.findByUsername(username);

      TokenDTO tokenResponse;
      if (user != null) {

        tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
      } else {

        throw new UsernameNotFoundException("Username " + username + " not found!");
      }

      return ResponseEntity.ok(tokenResponse);

    } catch (Exception e) {

      throw new BadCredentialsException("Invalid username or password!");
    }
  }

  @Override
  @SuppressWarnings("rawtypes")
  public ResponseEntity refreshToken(String username, String refreshToken) {

    User user = userRepository.findByUsername(username);

    TokenDTO tokenResponse;

    if (user != null) {

      tokenResponse = tokenProvider.refreshToken(refreshToken);

    } else {

      throw new UsernameNotFoundException("Username " + username + " not found!");
    }

    return ResponseEntity.ok(tokenResponse);
  }
}
