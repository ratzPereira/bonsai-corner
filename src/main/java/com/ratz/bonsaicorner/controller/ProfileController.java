package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.ProfileDTO;
import com.ratz.bonsaicorner.DTO.ProfileImageDTO;
import com.ratz.bonsaicorner.DTO.ProfileResponseDTO;
import com.ratz.bonsaicorner.DTO.UploadOutputDTO;
import com.ratz.bonsaicorner.service.impl.ProfileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
public class ProfileController {

    private ProfileServiceImpl profileService;

    @GetMapping()
    public ResponseEntity<ProfileResponseDTO> getUserProfile() {

        return new ResponseEntity<>(profileService.getUserProfile(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProfileResponseDTO> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {

        return new ResponseEntity<>(profileService.createProfile(profileDTO), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ProfileResponseDTO> editProfile(@Valid @RequestBody ProfileDTO profileDTO) {

        return new ResponseEntity<>(profileService.editProfile(profileDTO), HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public ResponseEntity<UploadOutputDTO> uploadProfilePhoto(@Valid @RequestBody ProfileImageDTO image) {

        String url = profileService.saveOrEditProfileImage(image.getImage());

        return new ResponseEntity<>(new UploadOutputDTO(url), HttpStatus.CREATED);
    }

    @DeleteMapping
    public String deleteProfile() {

        profileService.deleteProfile();
        return "Profile deleted!";
    }
}
