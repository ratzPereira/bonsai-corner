package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT u FROM User u WHERE u.userName =:userName")
  Optional<User> findByUsername(@Param("userName") String userName);

  boolean existsByEmail(String email);

  boolean existsByUserName(String userName);
}
