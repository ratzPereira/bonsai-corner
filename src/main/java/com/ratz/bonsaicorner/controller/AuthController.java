package com.ratz.bonsaicorner.controller;


import com.ratz.bonsaicorner.DTO.PasswordChangeDTO;
import com.ratz.bonsaicorner.DTO.security.AccountCredentialsDTO;
import com.ratz.bonsaicorner.DTO.security.SignUpDTO;
import com.ratz.bonsaicorner.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_JSON;
import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_XML;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {


  private AuthServiceImpl authService;


  @SuppressWarnings("rawtypes")
  @Operation(summary = "Authenticates a user and return the token")
  @PostMapping(value = "/signIn", produces = {APPLICATION_JSON, APPLICATION_XML}, consumes = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity signIn(@RequestBody AccountCredentialsDTO data) {

    if (isDataValid(data))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

    ResponseEntity token = authService.singIn(data);

    if (token == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");
    }

    return token;
  }

  @PostMapping(value = "/signUp", produces = {APPLICATION_JSON, APPLICATION_XML}, consumes = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity registerUser(@RequestBody SignUpDTO signUpDTO) {

    authService.registerUser(signUpDTO);

    return new ResponseEntity<>("User created with success!", HttpStatus.CREATED);
  }


  @SuppressWarnings("rawtypes")
  @Operation(summary = "Refresh token authenticated user and return the token")
  @PutMapping(value = "/refresh/{username}", produces = {APPLICATION_JSON, APPLICATION_XML}, consumes = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken) {

    if (isDataValid(username, refreshToken))
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");

    ResponseEntity token = authService.refreshToken(username, refreshToken);

    if (token == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid request");
    }

    return token;
  }

  //TODO: forgot password and change password.
  @PostMapping("/password")
  public ResponseEntity<String> changeUserPassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {

    authService.changeUserPassword(passwordChangeDTO);
    return ResponseEntity.status(HttpStatus.OK).body("Password changed with success");
  }


  private boolean isDataValid(String username, String refreshToken) {
    return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
  }


  private boolean isDataValid(AccountCredentialsDTO data) {
    return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword().isBlank();
  }
}
