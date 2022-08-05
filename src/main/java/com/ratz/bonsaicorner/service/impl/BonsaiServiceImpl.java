package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import com.ratz.bonsaicorner.controller.BonsaiController;
import com.ratz.bonsaicorner.mapper.DozerMapper;
import com.ratz.bonsaicorner.model.Bonsai;
import com.ratz.bonsaicorner.repository.BonsaiRepository;
import com.ratz.bonsaicorner.service.BonsaiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BonsaiServiceImpl implements BonsaiService {

  private BonsaiRepository bonsaiRepository;
  PagedResourcesAssembler<BonsaiDTO> assembler;

  public BonsaiServiceImpl(BonsaiRepository bonsaiRepository, PagedResourcesAssembler<BonsaiDTO> assembler) {
    this.bonsaiRepository = bonsaiRepository;
    this.assembler = assembler;
  }

  @Override
  public PagedModel<EntityModel<BonsaiDTO>> findAll(Pageable pageable) {

    Page<Bonsai> bonsais = bonsaiRepository.findAll(pageable);

    return getDTOFromEntity(pageable, bonsais);
  }


  private PagedModel<EntityModel<BonsaiDTO>> getDTOFromEntity(Pageable pageable, Page<Bonsai> bonsais){

    Page<BonsaiDTO> bonsaiDTOS = bonsais.map(bonsai -> DozerMapper.parseObject(bonsai, BonsaiDTO.class));

    //bonsaiDTOS.map(bonsaiDTO -> bonsaiDTO.add(linkTo(methodOn(BonsaiController.class).findb)))

    Link link = linkTo(methodOn(BonsaiController.class).findAllBonsai(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

    return assembler.toModel(bonsaiDTOS, link);
  }
}
