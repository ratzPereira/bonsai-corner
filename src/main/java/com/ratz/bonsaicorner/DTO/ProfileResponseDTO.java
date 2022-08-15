package com.ratz.bonsaicorner.DTO;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProfileResponseDTO extends RepresentationModel<ProfileResponseDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private LocalDate birthDate;
  private String country;
  private String username;
  private String name;
  private String profilePhoto;
  private Integer age;
  private Integer experienceYears;
  private Integer numberOfTrees;
}
