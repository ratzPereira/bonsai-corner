package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
