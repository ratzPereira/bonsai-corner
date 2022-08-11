package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.InterventionDTO;
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
import java.util.stream.Collectors;

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
    return interventionToDTO(intervention);
  }

  @Override
  public List<InterventionDTO> getInterventionsByBonsaiId(Long id) {

    List<Intervention> interventions = interventionRepository.findByBonsaiId(id);

    if (interventions.isEmpty()) {

      throw new ResourceNotFoundException("Interventions not found for the bonsai with the ID " + id);
    }

    return interventions.stream().map(this::interventionToDTO).collect(Collectors.toList());
  }


  private Intervention interventionDTOToEntity(InterventionDTO interventionDTO) {

    return modelMapper.map(interventionDTO, Intervention.class);
  }

  private InterventionDTO interventionToDTO(Intervention intervention) {

    return modelMapper.map(intervention, InterventionDTO.class);
  }
}
