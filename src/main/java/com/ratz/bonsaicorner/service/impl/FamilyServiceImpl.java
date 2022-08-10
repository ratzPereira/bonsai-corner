package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.FamilyDTO;
import com.ratz.bonsaicorner.controller.FamilyController;
import com.ratz.bonsaicorner.exceptions.RequiredObjectIsNullException;
import com.ratz.bonsaicorner.exceptions.ResourceAlreadyExistException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.mapper.DozerMapper;
import com.ratz.bonsaicorner.model.Family;
import com.ratz.bonsaicorner.repository.FamilyRepository;
import com.ratz.bonsaicorner.service.FamilyService;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FamilyServiceImpl implements FamilyService {

  private FamilyRepository familyRepository;

  public FamilyServiceImpl(FamilyRepository familyRepository) {
    this.familyRepository = familyRepository;
  }

  @Override
  public FamilyDTO saveFamily(FamilyDTO familyDTO) {

    if (familyDTO == null) throw new RequiredObjectIsNullException();
    Family familyExist = familyRepository.findByFamilyName(familyDTO.getFamilyName());

    if (familyExist != null) throw new ResourceAlreadyExistException("Family with that name already exist!");

    Family family = DozerMapper.parseObject(familyDTO, Family.class);
    familyRepository.save(family);

    FamilyDTO responseFamilyDTO = DozerMapper.parseObject(family, FamilyDTO.class);
    responseFamilyDTO.add(linkTo(methodOn(FamilyController.class).getFamilyById(responseFamilyDTO.getId())).withSelfRel());

    return responseFamilyDTO;
  }

  @Override
  public FamilyDTO findFamilyById(Long id) {

    Family family = familyRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Family with the ID " + id + " not found!"));

    FamilyDTO familyDTO = DozerMapper.parseObject(family, FamilyDTO.class);
    familyDTO.add(linkTo(methodOn(FamilyController.class).getFamilyById(id)).withSelfRel());

    return familyDTO;
  }
}
