package com.ratz.bonsaicorner.controller;


import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import com.ratz.bonsaicorner.service.impl.SpeciesServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/species")
public class SpeciesController {

  private SpeciesServiceImpl speciesService;

  public SpeciesController(SpeciesServiceImpl speciesService) {
    this.speciesService = speciesService;
  }

  @GetMapping("/{id}")
  public SpeciesDTO getSpeciesById(@PathVariable Long id) {

    return speciesService.getSpeciesById(id);
  }
}