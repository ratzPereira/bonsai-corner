package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.ProfileDTO;
import com.ratz.bonsaicorner.DTO.ProfileResponseDTO;
import com.ratz.bonsaicorner.controller.ProfileController;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Profile;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.ProfileRepository;
import com.ratz.bonsaicorner.service.ProfileService;
import com.ratz.bonsaicorner.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private ProfileRepository profileRepository;
  private final ModelMapper modelMapper;
  private final UserService userService;

  @Override
  public ProfileResponseDTO getUserProfile() {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());
    Profile profile = profileRepository.findByUserId(user.getId());

    if (profile == null)
      throw new ResourceNotFoundException("Profile does not exist for the user. Please create your profile");

    ProfileDTO profileDTO = createProfileDTOResponse(profile);
    profileDTO.add(linkTo(methodOn(ProfileController.class)).withSelfRel());

    return getProfileResponseDTO(user, profileDTO);
  }

  //helper methods

  private ProfileResponseDTO getProfileResponseDTO(User user, ProfileDTO profileDTO) {
    ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
    profileResponseDTO.setAge(profileDTO.getAge());
    profileResponseDTO.setBirthDate(profileDTO.getBirthDate());
    profileResponseDTO.setCountry(profileDTO.getCountry());
    profileResponseDTO.setName(user.getFirstName());
    profileResponseDTO.setExperienceYears(profileDTO.getExperienceYears());
    profileResponseDTO.setUsername(user.getUsername());
    profileResponseDTO.setNumberOfTrees(profileDTO.getNumberOfTrees());
    return profileResponseDTO;
  }

  private ProfileDTO createProfileDTOResponse(Profile profile) {

    return modelMapper.map(profile, ProfileDTO.class);
  }
}
