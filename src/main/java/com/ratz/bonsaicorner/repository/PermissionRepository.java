package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

  Optional<Permission> findByDescription(String description);
}
