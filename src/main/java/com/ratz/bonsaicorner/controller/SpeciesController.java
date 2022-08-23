package com.ratz.bonsaicorner.controller;


import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import com.ratz.bonsaicorner.service.impl.SpeciesServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('MANAGER')")
    public SpeciesDTO createNewSpecies(@RequestBody SpeciesDTO speciesDTO) {

        return speciesService.createSpecies(speciesDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> deleteSpecies(@PathVariable Long id) {

        speciesService.deleteSpecies(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML})
    public ResponseEntity<PagedModel<EntityModel<SpeciesDTO>>> findAllSpecies
            (@RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "size", defaultValue = "12") Integer size,
             @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "speciesName"));

        return ResponseEntity.ok(speciesService.findAll(pageable));
    }
}
