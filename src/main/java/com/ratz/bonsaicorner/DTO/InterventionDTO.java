package com.ratz.bonsaicorner.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ratz.bonsaicorner.enums.InterventionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterventionDTO {

  private Long id;

  @Size(min = 4, max = 255)
  private String job;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate interventionDate;


  private Set<String> images;

  private InterventionStatus interventionStatus;

}
