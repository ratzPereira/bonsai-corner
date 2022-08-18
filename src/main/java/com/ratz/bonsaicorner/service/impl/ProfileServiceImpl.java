package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.ProfileDTO;
import com.ratz.bonsaicorner.DTO.ProfileResponseDTO;
import com.ratz.bonsaicorner.exceptions.ImageUploadFailedException;
import com.ratz.bonsaicorner.exceptions.ResourceAlreadyExistException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Profile;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.ProfileRepository;
import com.ratz.bonsaicorner.service.ProfileService;
import com.ratz.bonsaicorner.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

  private ProfileRepository profileRepository;
  private final ModelMapper modelMapper;
  private final UserService userService;
  private FileServiceImpl fileService;

  @Override
  public ProfileResponseDTO getUserProfile() {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());
    Profile profile = profileRepository.findByUserId(user.getId());

    if (profile == null)
      throw new ResourceNotFoundException("Profile does not exist for the user. Please create your profile");

    ProfileDTO profileDTO = createProfileDTOResponse(profile);
    //profileDTO.add(linkTo(methodOn(ProfileController.class).getUserProfile()).withSelfRel());

    return getProfileResponseDTO(user, profileDTO, profile);
  }

  @Override
  public ProfileResponseDTO createProfile(ProfileDTO profileDTO) {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());

    if (profileRepository.findByUserId(user.getId()) != null)
      throw new ResourceAlreadyExistException("Profile for the user already exist. Please edit instead.");

    Profile profile = profileDTOToEntity(profileDTO);
    profile.setUser(user);


    profileRepository.save(profile);

    return getProfileResponseDTO(user, profileDTO, profile);
  }

  @Override
  public ProfileResponseDTO editProfile(ProfileDTO profileDTO) {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());

    if (profileRepository.findByUserId(user.getId()) == null)
      throw new ResourceNotFoundException("Profile for the user " + user.getUsername() + " not found.");

    Profile profile = profileRepository.findByUserId(user.getId());
    profile.setAge(profileDTO.getAge());
    profile.setBirthDate(profileDTO.getBirthDate());
    profile.setCountry(profileDTO.getCountry());
    profile.setExperienceYears(profileDTO.getExperienceYears());
    profile.setNumberOfTrees(profileDTO.getNumberOfTrees());

    profileRepository.save(profile);

    return getProfileResponseDTO(user, profileDTO, profile);
  }


  @Override
  public String saveOrEditProfileImage(String image) {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());
    Profile profile = profileRepository.findByUserId(user.getId());
    
    //TODO delete previous image if the user already have one
    try {
      profile.setProfilePhoto(fileService.uploadSingleImage(image));
      profileRepository.save(profile);

      return profile.getProfilePhoto();

    } catch (Exception ex) {
      throw new ImageUploadFailedException("Image uploading failed! Please try again later.");
    }
  }

  //helper methods

  private ProfileResponseDTO getProfileResponseDTO(User user, ProfileDTO profileDTO, Profile profile) {
    ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
    profileResponseDTO.setAge(profileDTO.getAge());
    profileResponseDTO.setBirthDate(profileDTO.getBirthDate());
    profileResponseDTO.setCountry(profileDTO.getCountry());
    profileResponseDTO.setName(user.getFirstName());
    profileResponseDTO.setExperienceYears(profileDTO.getExperienceYears());
    profileResponseDTO.setUsername(user.getUsername());
    profileResponseDTO.setNumberOfTrees(profileDTO.getNumberOfTrees());
    profileResponseDTO.setProfilePhoto(profile.getProfilePhoto());
    return profileResponseDTO;
  }

  private ProfileDTO createProfileDTOResponse(Profile profile) {

    return modelMapper.map(profile, ProfileDTO.class);
  }

  private Profile profileDTOToEntity(ProfileDTO profileDTO) {

    return modelMapper.map(profileDTO, Profile.class);
  }
}
