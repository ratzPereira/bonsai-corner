package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.ProfileDTO;
import com.ratz.bonsaicorner.DTO.ProfileResponseDTO;

public interface ProfileService {
    ProfileResponseDTO getUserProfile();

    ProfileResponseDTO createProfile(ProfileDTO profileDTO);

    ProfileResponseDTO editProfile(ProfileDTO profileDTO);

    String saveOrEditProfileImage(String image);

    void deleteProfile();
}
