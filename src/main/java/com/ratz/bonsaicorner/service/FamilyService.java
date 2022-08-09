package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.FamilyDTO;

public interface FamilyService {
  FamilyDTO saveFamily(FamilyDTO familyDTO);

  FamilyDTO findFamilyById(Long id);
}
