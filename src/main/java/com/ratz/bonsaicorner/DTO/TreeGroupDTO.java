package com.ratz.bonsaicorner.DTO;

import com.ratz.bonsaicorner.model.TreeGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeGroupDTO extends RepresentationModel<TreeGroupDTO> implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private List<TreeGroup> treeGroupList;
}
