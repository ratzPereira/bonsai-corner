package com.ratz.bonsaicorner.controller;


import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import com.ratz.bonsaicorner.service.impl.SpeciesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_JSON;
import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_XML;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController {

  private SpeciesServiceImpl speciesService;

  public SpeciesController(SpeciesServiceImpl speciesService) {
    this.speciesService = speciesService;
  }

  @GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML})
  public SpeciesDTO getSpeciesById(@PathVariable Long id) {

    return speciesService.getSpeciesById(id);
  }

  @PostMapping(produces = {APPLICATION_JSON, APPLICATION_XML}, consumes = {APPLICATION_JSON, APPLICATION_XML})
  public SpeciesDTO createNewSpecies(@RequestBody SpeciesDTO speciesDTO) {

    return speciesService.createSpecies(speciesDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSpecies(@PathVariable Long id) {

    speciesService.deleteSpecies(id);

    return ResponseEntity.noContent().build();
  }
}
