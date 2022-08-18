package com.ratz.bonsaicorner.service;

import com.ratz.bonsaicorner.DTO.BonsaiDTO;
import com.ratz.bonsaicorner.DTO.ImageLinkDTO;
import com.ratz.bonsaicorner.DTO.ImagesSetDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface BonsaiService {

  PagedModel<EntityModel<BonsaiDTO>> findAll(Pageable pageable);

  BonsaiDTO findBonsaiById(Long id);

  BonsaiDTO createBonsai(BonsaiDTO bonsaiDTO);

  void deleteBonsaiById(Long id);

  BonsaiDTO updateBonsai(BonsaiDTO bonsaiDTO);

  ImagesSetDTO getAllBonsaiImages(Long id);

  void deleteImageOfTheBonsai(Long id, ImageLinkDTO link);

  void deleteAllBonsaiImages(Long id);

  List<String> addImagesToBonsai(Long id, ImagesSetDTO images);
}
