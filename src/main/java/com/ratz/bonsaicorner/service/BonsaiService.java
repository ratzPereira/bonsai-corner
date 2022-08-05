package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface BonsaiService {

  PagedModel<EntityModel<BonsaiDTO>> findAll(Pageable pageable);
}
