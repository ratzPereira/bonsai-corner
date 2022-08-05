package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import com.ratz.bonsaicorner.service.impl.BonsaiServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bonsai")
public class BonsaiController {

  private BonsaiServiceImpl bonsaiService;

  public BonsaiController(BonsaiServiceImpl bonsaiService) {
    this.bonsaiService = bonsaiService;
  }

  @GetMapping
  public ResponseEntity<PagedModel<EntityModel<BonsaiDTO>>> findAllBonsai(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                          @RequestParam(value = "size", defaultValue = "12") Integer size,
                                                                          @RequestParam(value = "direction", defaultValue = "asc") String direction){

    Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

    return ResponseEntity.ok(bonsaiService.findAll(pageable));
  }
}
