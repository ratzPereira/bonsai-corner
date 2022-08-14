package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.model.User;

public interface UserService {

  //User findById(Long id);
  String getCurrentUsernameFromContext();

  User findByUsername(String username);

  Boolean isTheResourceOwner(Long bonsaiUserId);
}
