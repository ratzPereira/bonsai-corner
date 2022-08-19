package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.service.impl.InterventionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/intervention")
public class InterventionController {

  private InterventionServiceImpl interventionService;

  @PostMapping("/bonsai/{id}")
  public ResponseEntity<InterventionDTO> createIntervention(@PathVariable Long id, @RequestBody InterventionDTO interventionDTO) {

    return new ResponseEntity<>(interventionService.createIntervention(id, interventionDTO), HttpStatus.CREATED);
  }

  @GetMapping("/bonsai/{id}")
  public List<InterventionDTO> getAllInterventionsByBonsaiId(@PathVariable Long id) {

    return interventionService.getInterventionsByBonsaiId(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InterventionDTO> getInterventionById(@PathVariable Long id) {

    return new ResponseEntity<>(interventionService.getInterventionById(id), HttpStatus.OK);
  }

  //TODO: get intervention images, delete intervention by id, delete intervention image, delete all intervention images,
  //TODO: add image to intervention, update intervention
}
