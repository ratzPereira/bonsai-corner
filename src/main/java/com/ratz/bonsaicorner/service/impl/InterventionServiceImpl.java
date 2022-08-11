package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.controller.InterventionController;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Bonsai;
import com.ratz.bonsaicorner.model.Intervention;
import com.ratz.bonsaicorner.repository.BonsaiRepository;
import com.ratz.bonsaicorner.repository.InterventionRepository;
import com.ratz.bonsaicorner.service.InterventionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class InterventionServiceImpl implements InterventionService {

  private ModelMapper modelMapper;
  private BonsaiRepository bonsaiRepository;
  private InterventionRepository interventionRepository;


  @Override
  public InterventionDTO createIntervention(Long id, InterventionDTO interventionDTO) {

    Bonsai bonsai = bonsaiRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Bonsai with the ID " + id + " not found!"));

    Intervention intervention = interventionDTOToEntity(interventionDTO);
    intervention.setBonsai(bonsai);

    interventionRepository.save(intervention);

    InterventionDTO interventionDTOToReturn = interventionToDTO(intervention);
    interventionDTOToReturn.add(linkTo(methodOn(InterventionController.class).getInterventionById(intervention.getId())).withSelfRel());
    return interventionDTOToReturn;
  }

  @Override
  public List<InterventionDTO> getInterventionsByBonsaiId(Long id) {

    List<Intervention> interventions = interventionRepository.findByBonsaiId(id);

    if (interventions.isEmpty())
      throw new ResourceNotFoundException("Interventions not found for the bonsai with the ID " + id);

    List<InterventionDTO> interventionDTOS = interventions.stream().map(this::interventionToDTO).toList();

    for (InterventionDTO interventionDTO : interventionDTOS) {
      interventionDTO.add(linkTo(methodOn(InterventionController.class).getInterventionById(interventionDTO.getId())).withSelfRel());
    }

    return interventionDTOS;
  }

  @Override
  public InterventionDTO getInterventionById(Long id) {

    Intervention intervention = interventionRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Intervention with the ID " + id + "  not found!"));

    InterventionDTO interventionDTOToReturn = interventionToDTO(intervention);
    interventionDTOToReturn.add(linkTo(methodOn(InterventionController.class).getInterventionById(id)).withSelfRel());
    return interventionDTOToReturn;
  }


  private Intervention interventionDTOToEntity(InterventionDTO interventionDTO) {

    return modelMapper.map(interventionDTO, Intervention.class);
  }

  private InterventionDTO interventionToDTO(Intervention intervention) {

    return modelMapper.map(intervention, InterventionDTO.class);
  }
}
