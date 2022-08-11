package com.ratz.bonsaicorner.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ratz.bonsaicorner.enums.InterventionStatus;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterventionDTO extends RepresentationModel<BonsaiDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;

  @Size(min = 4, max = 255)
  private String job;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate interventionDate;


  private Set<String> images;

  private InterventionStatus interventionStatus;

}
