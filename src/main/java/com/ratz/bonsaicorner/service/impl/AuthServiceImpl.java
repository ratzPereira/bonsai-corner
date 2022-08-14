package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import com.ratz.bonsaicorner.DTO.security.SignUpDTO;
import com.ratz.bonsaicorner.DTO.security.TokenDTO;
import com.ratz.bonsaicorner.exceptions.ResourceAlreadyExistException;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.PermissionRepository;
import com.ratz.bonsaicorner.repository.UserRepository;
import com.ratz.bonsaicorner.security.JwtTokenProvider;
import com.ratz.bonsaicorner.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  private AuthenticationManager authenticationManager;
  private JwtTokenProvider tokenProvider;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private PermissionRepository permissionRepository;

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

  @Override
  public void registerUser(SignUpDTO signUpDTO) {


    if (userRepository.existsByUserName(signUpDTO.getUserName())) {
      throw new ResourceAlreadyExistException("User with this username already exist!");
    }

    if (userRepository.existsByEmail(signUpDTO.getEmail())) {
      throw new ResourceAlreadyExistException("Email already used!");
    }

    User user = new User();
    user.setEmail(signUpDTO.getEmail());
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setUserName(signUpDTO.getUserName());
    user.setFirstName(signUpDTO.getFirstName());
    user.setLastName(signUpDTO.getLastName());
    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
    user.setEnabled(true);
    user.setCredentialsNonExpired(true);

    permissionRepository.findByDescription("COMMON_USER").ifPresent(role -> user.setPermissions(List.of(role)));

    userRepository.save(user);

  }
}
