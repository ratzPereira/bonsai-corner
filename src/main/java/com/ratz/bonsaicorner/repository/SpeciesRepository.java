package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {

  boolean existsBySpeciesName(String species);

  Species findBySpeciesName(String name);
}
