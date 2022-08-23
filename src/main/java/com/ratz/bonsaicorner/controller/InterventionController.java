package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.ImageLinkDTO;
import com.ratz.bonsaicorner.DTO.ImagesSetDTO;
import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.DTO.UpdateInterventionDTO;
import com.ratz.bonsaicorner.service.impl.InterventionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

  @GetMapping("/{id}/images")
  public ImagesSetDTO getAllInterventionsImagesByBonsaiId(@PathVariable Long id) {

    return interventionService.getInterventionImagesByBonsaiId(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteInterventionById(@PathVariable Long id) {


    interventionService.deleteInterventionById(id);
    return new ResponseEntity<>("Intervention with the id " + id + " has been deleted", HttpStatus.OK);
  }

  @DeleteMapping("/{id}/image")
  public ResponseEntity<String> deleteInterventionImage(@PathVariable Long id, @Valid @RequestBody ImageLinkDTO link) {

    interventionService.deleteInterventionImage(id, link);

    return new ResponseEntity<>("Image has been deleted", HttpStatus.OK);
  }

  @DeleteMapping("/{id}/images")
  public ResponseEntity<String> deleteInterventionImages(@PathVariable Long id) {

    interventionService.deleteAllInterventionImages(id);

    return new ResponseEntity<>("All Images has been deleted", HttpStatus.OK);
  }

  @PostMapping("/{id}/images")
  public ResponseEntity<String> addImagesForIntervention(@PathVariable Long id, @RequestBody ImagesSetDTO images) {

    interventionService.addImagesForIntervention(id, images);

    return new ResponseEntity<>("Images added with success", HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<InterventionDTO> updateIntervention(@PathVariable Long id, @Valid @RequestBody UpdateInterventionDTO interventionDTO) {

    return new ResponseEntity<>(interventionService.updateIntervention(id, interventionDTO), HttpStatus.OK);
  }
}
