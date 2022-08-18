package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import com.ratz.bonsaicorner.controller.BonsaiController;
import com.ratz.bonsaicorner.exceptions.RequiredObjectIsNullException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Bonsai;
import com.ratz.bonsaicorner.model.Species;
import com.ratz.bonsaicorner.model.User;
import com.ratz.bonsaicorner.repository.BonsaiRepository;
import com.ratz.bonsaicorner.repository.SpeciesRepository;
import com.ratz.bonsaicorner.service.BonsaiService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class BonsaiServiceImpl implements BonsaiService {

  private BonsaiRepository bonsaiRepository;
  private SpeciesRepository speciesRepository;
  private ModelMapper modelMapper;
  PagedResourcesAssembler<BonsaiDTO> assembler;
  private UserServiceImpl userService;


  @Override
  public PagedModel<EntityModel<BonsaiDTO>> findAll(Pageable pageable) {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());

    Page<Bonsai> bonsais = bonsaiRepository.findAllByUser(user, pageable);

    return getDTOFromEntity(pageable, bonsais);
  }

  @Override
  public BonsaiDTO findBonsaiById(Long id) {
    Bonsai bonsai = getBonsai(id);

    BonsaiDTO bonsaiDTO = mapBonsaiDTOToBonsai(bonsai);
    bonsaiDTO.add(linkTo(methodOn(BonsaiController.class).findBonsaiById(id)).withSelfRel());

    return bonsaiDTO;
  }


  @Override
  public BonsaiDTO createBonsai(BonsaiDTO bonsaiDTO) {

    User user = userService.findByUsername(userService.getCurrentUsernameFromContext());

    if (bonsaiDTO == null) throw new RequiredObjectIsNullException();

    Species species = speciesRepository.findBySpeciesName(bonsaiDTO.getSpecies().getSpeciesName());

    if (species == null) throw new ResourceNotFoundException("Species not found!");

    Bonsai bonsai = mapBonsaiToBonsaiDTO(bonsaiDTO);
    bonsai.setSpecies(species);
    bonsai.setUser(user);
    bonsaiRepository.save(bonsai);

    BonsaiDTO bonsaiDTOToReturn = mapBonsaiDTOToBonsai(bonsai);
    bonsaiDTOToReturn.add(linkTo(methodOn(BonsaiController.class).findBonsaiById(bonsaiDTOToReturn.getId())).withSelfRel());

    return bonsaiDTOToReturn;
  }

  @Override
  public void deleteBonsaiById(Long id) {

    //TODO: delete images when deleting bonsai
    Bonsai bonsai = getBonsai(id);

    bonsaiRepository.delete(bonsai);
  }

  @Override
  public BonsaiDTO updateBonsai(BonsaiDTO bonsaiDTO) {

    Bonsai bonsai = getBonsai(bonsaiDTO.getId());

    if (!Objects.equals(bonsai.getSpecies().getSpeciesName(), bonsaiDTO.getSpecies().getSpeciesName())) {

      Species species = speciesRepository.findBySpeciesName(bonsaiDTO.getSpecies().getSpeciesName());
      if (species == null) {

        throw new ResourceNotFoundException("Species with the name " + bonsaiDTO.getSpecies().getSpeciesName() + " does not exist.");
      }
      bonsai.setSpecies(species);
    }

    bonsai.setAge(bonsaiDTO.getAge());
    bonsai.setDescription(bonsaiDTO.getDescription());
    bonsai.setName(bonsaiDTO.getName());
    //bonsai.setImages(bonsaiDTO.getImages());

    bonsaiRepository.save(bonsai);
    BonsaiDTO bonsaiDTOToReturn = mapBonsaiDTOToBonsai(bonsai);
    bonsaiDTOToReturn.add(linkTo(methodOn(BonsaiController.class).findBonsaiById(bonsai.getId())).withSelfRel());

    return bonsaiDTOToReturn;
  }


  //helper methods
  private PagedModel<EntityModel<BonsaiDTO>> getDTOFromEntity(Pageable pageable, Page<Bonsai> bonsais) {

    Page<BonsaiDTO> bonsaiDTOS = bonsais.map(bonsai -> mapBonsaiDTOToBonsai(bonsai));

    bonsaiDTOS.map(bonsaiDTO
        -> bonsaiDTO.add(linkTo(methodOn(BonsaiController.class).findBonsaiById(bonsaiDTO.getId())).withSelfRel()));

    Link link = linkTo(methodOn(BonsaiController.class)
        .findAllBonsai(pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();

    return assembler.toModel(bonsaiDTOS, link);
  }

  private Bonsai getBonsai(Long id) {
    Bonsai bonsai = bonsaiRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Bonsai with the id " + id + " not found!"));

    if (!userService.isTheResourceOwner(bonsai.getUser().getId())) {

      throw new ResourceNotFoundException("Bonsai with the id " + id + " not found!");
    }

    return bonsai;
  }

  private Bonsai mapBonsaiToBonsaiDTO(BonsaiDTO bonsaiDTO) {

    return modelMapper.map(bonsaiDTO, Bonsai.class);
  }

  private BonsaiDTO mapBonsaiDTOToBonsai(Bonsai bonsai) {

    return modelMapper.map(bonsai, BonsaiDTO.class);
  }
}
