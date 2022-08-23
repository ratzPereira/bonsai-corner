package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.ImageLinkDTO;
import com.ratz.bonsaicorner.DTO.ImagesSetDTO;
import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.DTO.UpdateInterventionDTO;

import java.util.List;

public interface InterventionService {

    InterventionDTO createIntervention(Long id, InterventionDTO interventionDTO);

    List<InterventionDTO> getInterventionsByBonsaiId(Long id);

    InterventionDTO getInterventionById(Long id);

    ImagesSetDTO getInterventionImagesByBonsaiId(Long id);

    void deleteInterventionById(Long id);

    void deleteInterventionImage(Long id, ImageLinkDTO link);

    void deleteAllInterventionImages(Long id);

    void addImagesForIntervention(Long id, ImagesSetDTO images);

    InterventionDTO updateIntervention(Long id, UpdateInterventionDTO interventionDTO);
}
