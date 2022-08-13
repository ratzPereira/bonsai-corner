package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.FamilyDTO;

import java.util.List;

public interface FamilyService {
  FamilyDTO saveFamily(FamilyDTO familyDTO);

  FamilyDTO findFamilyById(Long id);

  List<FamilyDTO> findAllFamilies();
}
