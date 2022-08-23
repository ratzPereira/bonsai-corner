package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.SpeciesDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface SpeciesService {
    SpeciesDTO getSpeciesById(Long id);

    SpeciesDTO createSpecies(SpeciesDTO speciesDTO);

    void deleteSpecies(Long id);

    PagedModel<EntityModel<SpeciesDTO>> findAll(Pageable pageable);
}
