package com.ratz.bonsaicorner.service.impl;

import com.ratz.bonsaicorner.DTO.ImageLinkDTO;
import com.ratz.bonsaicorner.DTO.ImagesSetDTO;
import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.DTO.UpdateInterventionDTO;
import com.ratz.bonsaicorner.controller.InterventionController;
import com.ratz.bonsaicorner.exceptions.ImageUploadFailedException;
import com.ratz.bonsaicorner.exceptions.ResourceNotFoundException;
import com.ratz.bonsaicorner.model.Bonsai;
import com.ratz.bonsaicorner.model.Intervention;
import com.ratz.bonsaicorner.repository.BonsaiRepository;
import com.ratz.bonsaicorner.repository.InterventionRepository;
import com.ratz.bonsaicorner.service.FileService;
import com.ratz.bonsaicorner.service.InterventionService;
import com.ratz.bonsaicorner.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class InterventionServiceImpl implements InterventionService {

  private ModelMapper modelMapper;
  private BonsaiRepository bonsaiRepository;
  private InterventionRepository interventionRepository;

  private FileService fileService;
  private UserService userService;


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

  @Override
  public ImagesSetDTO getInterventionImagesByBonsaiId(Long id) {

    Intervention intervention = getIntervention(id);

    ImagesSetDTO imagesSetDTO = new ImagesSetDTO();
    imagesSetDTO.setImages(intervention.getImages());
    return imagesSetDTO;
  }

  @Override
  public void deleteInterventionById(Long id) {
    Intervention intervention = getIntervention(id);

    try {

      fileService.deleteMultipleImages(intervention.getImages());
      interventionRepository.deleteById(id);

    } catch (Exception ex) {

      throw new ImageUploadFailedException("Image uploading failed! Please try again later.");
    }
  }

  @Override
  public void deleteInterventionImage(Long id, ImageLinkDTO link) {

    Intervention intervention = getIntervention(id);

    if (intervention.getImages().contains(link.getUrl())) {

      try {

        fileService.deleteImage(link.getUrl());
        intervention.getImages().remove(link.getUrl());
        interventionRepository.save(intervention);
      } catch (Exception ex) {

        throw new ImageUploadFailedException("Failed to delete image! Please try again later.");
      }
    }
  }

  @Override
  public void deleteAllInterventionImages(Long id) {

    Intervention intervention = getIntervention(id);

    if (intervention.getImages().isEmpty())
      throw new ResourceNotFoundException("Images for Intervention with the id " + intervention.getId() + " deleted");

    try {

      fileService.deleteMultipleImages(intervention.getImages());
      intervention.setImages(new HashSet<>());
      interventionRepository.save(intervention);

    } catch (Exception ex) {
      throw new ImageUploadFailedException("Failed to delete image! Please try again later.");
    }
  }

  @Override
  @Transactional
  public void addImagesForIntervention(Long id, ImagesSetDTO images) {

    Intervention intervention = getIntervention(id);

    try {
      Set<String> imagesUploaded = fileService.uploadBonsaiImages(images.getImages());
      Set<String> interventionImages = intervention.getImages();
      interventionImages.addAll(imagesUploaded);
      intervention.setImages(interventionImages);

    } catch (Exception e) {

      throw new ImageUploadFailedException("Failed to upload images for Intervention");
    }
  }

  @Override
  public InterventionDTO updateIntervention(Long id, UpdateInterventionDTO interventionDTO) {

    Intervention intervention = getIntervention(id);

    intervention.setInterventionDate(interventionDTO.getInterventionDate());
    intervention.setInterventionStatus(interventionDTO.getInterventionStatus());
    intervention.setJob(interventionDTO.getJob());

    interventionRepository.save(intervention);
    return interventionToDTO(intervention);
  }

  private Intervention getIntervention(Long id) {
    Intervention intervention = interventionRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Intervention not found!"));

    if (!userService.isTheResourceOwner(intervention.getBonsai().getUser().getId())) {

      throw new ResourceNotFoundException(("Intervention not found!"));
    }
    return intervention;
  }

  private Intervention interventionDTOToEntity(InterventionDTO interventionDTO) {

    return modelMapper.map(interventionDTO, Intervention.class);
  }

  private InterventionDTO interventionToDTO(Intervention intervention) {

    return modelMapper.map(intervention, InterventionDTO.class);
  }
}
