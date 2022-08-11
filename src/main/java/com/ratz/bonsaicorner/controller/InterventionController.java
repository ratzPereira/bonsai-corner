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
}
