package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
