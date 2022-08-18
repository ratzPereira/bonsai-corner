package com.ratz.bonsaicorner.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProfileImageDTO {

  @NotEmpty
  @Size(min = 30)
  private String image;
}
