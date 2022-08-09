package com.ratz.bonsaicorner.controller;

import com.ratz.bonsaicorner.DTO.TreeGroupDTO;
import com.ratz.bonsaicorner.service.impl.TreeGroupServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/treeGroup")
public class TreeGroupController {

  private TreeGroupServiceImpl treeGroupService;

  public TreeGroupController(TreeGroupServiceImpl treeGroupService) {
    this.treeGroupService = treeGroupService;
  }

  @PostMapping
  public ResponseEntity<TreeGroupDTO> saveTreeGroup(@RequestBody TreeGroupDTO treeGroupDTO) {

    return new ResponseEntity<>(treeGroupService.saveTreeGroup(treeGroupDTO), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TreeGroupDTO> getTreeGroupById(@PathVariable Long id) {

    return ResponseEntity.ok(treeGroupService.findTreeGroupById(id));
  }
}
