package com.ratz.bonsaicorner.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonsaiDTO extends RepresentationModel<BonsaiDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private int age;
  private String description;
  private Set<String> images;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate bonsaiCreationDate;
}
