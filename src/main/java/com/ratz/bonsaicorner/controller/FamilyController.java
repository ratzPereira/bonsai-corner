package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.FamilyDTO;
import com.ratz.bonsaicorner.service.impl.FamilyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/family")
public class FamilyController {

  private FamilyServiceImpl familyService;

  public FamilyController(FamilyServiceImpl familyService) {
    this.familyService = familyService;
  }

  @PostMapping
  public ResponseEntity<FamilyDTO> saveFamily(@RequestBody FamilyDTO familyDTO) {

    return new ResponseEntity<>(familyService.saveFamily(familyDTO), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FamilyDTO> getFamilyById(@PathVariable Long id) {

    return ResponseEntity.ok(familyService.findFamilyById(id));
  }
}
