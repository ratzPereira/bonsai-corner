package com.ratz.bonsaicorner.service.impl;


import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import com.ratz.bonsaicorner.controller.SpeciesController;
import com.ratz.bonsaicorner.exceptions.ResourceAlreadyExistException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Family;
import com.ratz.bonsaicorner.model.Species;
import com.ratz.bonsaicorner.model.TreeGroup;
import com.ratz.bonsaicorner.repository.FamilyRepository;
import com.ratz.bonsaicorner.repository.SpeciesRepository;
import com.ratz.bonsaicorner.repository.TreeGroupRepository;
import com.ratz.bonsaicorner.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

  private SpeciesRepository speciesRepository;
  private FamilyRepository familyRepository;
  private TreeGroupRepository treeGroupRepository;
  private ModelMapper mapper;


  @Override
  public SpeciesDTO getSpeciesById(Long id) {

    Species species = speciesRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Species with the ID " + id + " not found"));

    SpeciesDTO speciesDTO = speciesEntityToDto(species);
    speciesDTO.add(linkTo(methodOn(SpeciesController.class).getSpeciesById(id)).withSelfRel());

    return speciesDTO;
  }


  @Override
  public SpeciesDTO createSpecies(SpeciesDTO speciesDTO) {

    Family family = familyRepository.findByFamilyName(speciesDTO.getFamily());
    TreeGroup treeGroup = treeGroupRepository.findTreeGroupByTreeGroupName(speciesDTO.getGroup());

    if (family == null || treeGroup == null)
      throw new ResourceNotFoundException("Family or TreeGroup does not exist!");

    if (speciesRepository.existsBySpeciesName(speciesDTO.getSpeciesName()))
      throw new ResourceAlreadyExistException("That species name already exist!");


    Species species = speciesDtoToEntity(speciesDTO);
    species.setFamily(family);
    species.setTreeGroup(treeGroup);

    speciesRepository.save(species);

    SpeciesDTO returnSpeciesDTO = speciesEntityToDto(species);
    returnSpeciesDTO.add(linkTo(methodOn(SpeciesController.class).getSpeciesById(returnSpeciesDTO.getId())).withSelfRel());

    return returnSpeciesDTO;
  }

  @Override
  public void deleteSpecies(Long id) {

    Species species = speciesRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Species with ID " + id + " not found"));

    speciesRepository.delete(species);
  }

  private Species speciesDtoToEntity(SpeciesDTO speciesDTO) {
    return mapper.map(speciesDTO, Species.class);
  }

  private SpeciesDTO speciesEntityToDto(Species species) {
    return mapper.map(species, SpeciesDTO.class);
  }
}
