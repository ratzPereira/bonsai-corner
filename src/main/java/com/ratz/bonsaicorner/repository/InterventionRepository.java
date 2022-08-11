package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

  List<Intervention> findByBonsaiId(Long id);
}
