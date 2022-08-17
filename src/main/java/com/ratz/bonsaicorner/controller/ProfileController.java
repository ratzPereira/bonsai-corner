package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.ProfileResponseDTO;
import com.ratz.bonsaicorner.service.impl.ProfileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
public class ProfileController {

  private ProfileServiceImpl profileService;

  @GetMapping()
  public ResponseEntity<ProfileResponseDTO> getUserProfile() {

    return new ResponseEntity<>(profileService.getUserProfile(), HttpStatus.OK);
  }

  //TODO: Create profile, edit user profile, add profile image
}
