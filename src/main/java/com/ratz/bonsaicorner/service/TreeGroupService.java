package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.TreeGroupDTO;

public interface TreeGroupService {
  TreeGroupDTO saveTreeGroup(TreeGroupDTO treeGroupDTO);

  TreeGroupDTO findTreeGroupById(Long id);
}
