package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Bonsai;
import com.ratz.bonsaicorner.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonsaiRepository extends JpaRepository<Bonsai, Long> {
  Page<Bonsai> findAllByUser(User user, Pageable pageable);
}
