package com.ratz.bonsaicorner.service;

import java.util.Set;

public interface FileService {
  String uploadSingleImage(String image);

  Set<String> uploadBonsaiImages(Set<String> images);

  void deleteImage(String url);

  void deleteMultipleImages(Set<String> images);
}
