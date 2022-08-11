package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.InterventionDTO;

import java.util.List;

public interface InterventionService {

  InterventionDTO createIntervention(Long id, InterventionDTO interventionDTO);

  List<InterventionDTO> getInterventionsByBonsaiId(Long id);
}
