package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.ForgotPasswordDTO;
import com.ratz.bonsaicorner.model.User;

public interface UserService {

  //User findById(Long id);
  String getCurrentUsernameFromContext();

  User findByUsername(String username);

  Boolean isTheResourceOwner(Long bonsaiUserId);

  boolean checkIfValidOldPassword(User user, String oldPassword);

  void changeUserPassword(User user, String newPassword);


}
