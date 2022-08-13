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
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class TreeGroupServiceImpl implements TreeGroupService {

  private ModelMapper modelMapper;
  private TreeGroupRepository treeGroupRepository;


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

  @Override
  public List<TreeGroupDTO> findAllTreeGroups() {

    List<TreeGroup> treeGroups = treeGroupRepository.findAll();

    if (treeGroups.isEmpty())
      throw new ResourceNotFoundException("Tree Groups not found");

    List<TreeGroupDTO> treeGroupDTOS = treeGroups.stream().map(this::treeGroupToDTO).toList();

    for (TreeGroupDTO treeGroupDTO : treeGroupDTOS) {
      treeGroupDTO.add(linkTo(methodOn(TreeGroupController.class).getTreeGroupById(treeGroupDTO.getId())).withSelfRel());
    }

    return treeGroupDTOS;
  }


  private TreeGroup treeGroupDTOToEntity(TreeGroupDTO treeGroupDTO) {

    return modelMapper.map(treeGroupDTO, TreeGroup.class);
  }

  private TreeGroupDTO treeGroupToDTO(TreeGroup treeGroup) {

    return modelMapper.map(treeGroup, TreeGroupDTO.class);
  }
}
