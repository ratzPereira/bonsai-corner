package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Bonsai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonsaiRepository extends JpaRepository<Bonsai, Long> {
}
