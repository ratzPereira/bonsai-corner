package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.TreeGroupDTO;
import com.ratz.bonsaicorner.controller.TreeGroupController;
import com.ratz.bonsaicorner.exceptions.RequiredObjectIsNullException;
import com.ratz.bonsaicorner.exceptions.ResourceAlreadyExistException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.mapper.DozerMapper;
import com.ratz.bonsaicorner.model.TreeGroup;
import com.ratz.bonsaicorner.repository.TreeGroupRepository;
import com.ratz.bonsaicorner.service.TreeGroupService;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TreeGroupServiceImpl implements TreeGroupService {

  private TreeGroupRepository treeGroupRepository;

  public TreeGroupServiceImpl(TreeGroupRepository treeGroupRepository) {
    this.treeGroupRepository = treeGroupRepository;
  }

  @Override
  public TreeGroupDTO saveTreeGroup(TreeGroupDTO treeGroupDTO) {

    if (treeGroupDTO == null) throw new RequiredObjectIsNullException();
    TreeGroup treeGroupExist = treeGroupRepository.findTreeGroupByTreeGroupName(treeGroupDTO.getTreeGroupName());

    if (treeGroupExist != null) throw new ResourceAlreadyExistException("TreeGroup with that name already exist!");

    TreeGroup treeGroup = DozerMapper.parseObject(treeGroupDTO, TreeGroup.class);
    treeGroupRepository.save(treeGroup);

    TreeGroupDTO responseTreeGroupDTO = DozerMapper.parseObject(treeGroup, TreeGroupDTO.class);
    responseTreeGroupDTO.add(linkTo(methodOn(TreeGroupController.class).getTreeGroupById(responseTreeGroupDTO.getId())).withSelfRel());

    return responseTreeGroupDTO;
  }

  @Override
  public TreeGroupDTO findTreeGroupById(Long id) {

    TreeGroup treeGroup = treeGroupRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("TreeGroup with the ID " + id + " not found!"));

    TreeGroupDTO treeGroupDTO = DozerMapper.parseObject(treeGroup, TreeGroupDTO.class);
    treeGroupDTO.add(linkTo(methodOn(TreeGroupController.class).getTreeGroupById(id)).withSelfRel());

    return treeGroupDTO;
  }
}
