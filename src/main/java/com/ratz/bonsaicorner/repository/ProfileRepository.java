package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
  Profile findByUserId(Long id);
}
