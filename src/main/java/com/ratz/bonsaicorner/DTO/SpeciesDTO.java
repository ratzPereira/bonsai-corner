package com.ratz.bonsaicorner.DTO;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesDTO extends RepresentationModel<SpeciesDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Mapping("id")
  private Long id;
  
  private String speciesName;
  private String description;
  private String pruning;
  private String watering;
  private String transplant;
  private String soil;
  private String propagation;
  private String regions;
  private String sunExposure;
  private String fertilizing;
  private String family;
  private String group;
  Set<String> commonBonsaiStyles;
}
