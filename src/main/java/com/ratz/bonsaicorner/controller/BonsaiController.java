package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import com.ratz.bonsaicorner.service.impl.BonsaiServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_JSON;
import static com.ratz.bonsaicorner.utils.MediaTypeUtils.APPLICATION_XML;

@RestController
@RequestMapping("/api/v1/bonsai")
public class BonsaiController {

  private BonsaiServiceImpl bonsaiService;

  public BonsaiController(BonsaiServiceImpl bonsaiService) {
    this.bonsaiService = bonsaiService;
  }

  @PostMapping(produces = {APPLICATION_JSON, APPLICATION_XML}, consumes = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity<BonsaiDTO> createBonsai(@RequestBody BonsaiDTO bonsaiDTO) {

    return new ResponseEntity<>(bonsaiService.createBonsai(bonsaiDTO), HttpStatus.CREATED);
  }

  @GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity<PagedModel<EntityModel<BonsaiDTO>>> findAllBonsai
      (@RequestParam(value = "page", defaultValue = "0") Integer page,
       @RequestParam(value = "size", defaultValue = "12") Integer size,
       @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

    return ResponseEntity.ok(bonsaiService.findAll(pageable));
  }

  @GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML})
  public ResponseEntity<BonsaiDTO> findBonsaiById(@PathVariable Long id) {

    return ResponseEntity.ok(bonsaiService.findBonsaiById(id));
  }
}
