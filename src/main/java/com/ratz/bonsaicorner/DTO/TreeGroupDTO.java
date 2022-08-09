package com.ratz.bonsaicorner.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeGroupDTO extends RepresentationModel<TreeGroupDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;
  private String treeGroupName;
}
