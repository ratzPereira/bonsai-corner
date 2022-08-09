package com.ratz.bonsaicorner.service.impl;


import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import com.ratz.bonsaicorner.controller.SpeciesController;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.mapper.DozerMapper;
import com.ratz.bonsaicorner.model.Species;
import com.ratz.bonsaicorner.repository.SpeciesRepository;
import com.ratz.bonsaicorner.service.SpeciesService;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SpeciesServiceImpl implements SpeciesService {

  private SpeciesRepository speciesRepository;

  public SpeciesServiceImpl(SpeciesRepository speciesRepository) {
    this.speciesRepository = speciesRepository;
  }

  @Override
  public SpeciesDTO getSpeciesById(Long id) {

    Species species = speciesRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Species with the ID " + id + " not found"));

    SpeciesDTO speciesDTO = DozerMapper.parseObject(species, SpeciesDTO.class);
    speciesDTO.add(linkTo(methodOn(SpeciesController.class).getSpeciesById(id)).withSelfRel());

    return speciesDTO;
  }
}
