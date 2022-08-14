package com.ratz.bonsaicorner.service.impl;


import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.UserRepository;
import com.ratz.bonsaicorner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {


  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


    User user = userRepository.findByUsername(username).get();

    if (user != null) {
      return user;

    } else {

      throw new UsernameNotFoundException("User not found with the provided id.");
    }
  }

  @Override
  public User findByUsername(String username) {

    return userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User with username " + username + " not found!"));
  }

  @Override
  public String getCurrentUsernameFromContext() {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ((UserDetails) principal).getUsername();
  }
  
}
