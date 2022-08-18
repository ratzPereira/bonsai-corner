package com.ratz.bonsaicorner.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ImageLinkDTO {

  @NotEmpty
  String url;
}
