package com.ratz.bonsaicorner.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProfileDTO extends RepresentationModel<ProfileDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate birthDate;

  @Size(min = 3, max = 55)
  private String country;

  @NumberFormat
  private Integer age;

  @NumberFormat
  private Integer experienceYears;

  @NumberFormat()
  private Integer numberOfTrees;
}
