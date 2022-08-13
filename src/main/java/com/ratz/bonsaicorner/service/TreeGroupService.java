package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.TreeGroupDTO;

import java.util.List;

public interface TreeGroupService {
  TreeGroupDTO saveTreeGroup(TreeGroupDTO treeGroupDTO);

  TreeGroupDTO findTreeGroupById(Long id);

  List<TreeGroupDTO> findAllTreeGroups();
}
