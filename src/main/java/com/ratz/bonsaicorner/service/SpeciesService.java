package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.SpeciesDTO;

public interface SpeciesService {
  SpeciesDTO getSpeciesById(Long id);

  SpeciesDTO createSpecies(SpeciesDTO speciesDTO);
}
