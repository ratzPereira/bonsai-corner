package com.ratz.bonsaicorner.mapper.custom;

import com.ratz.bonsaicorner.repository.FamilyRepository;
import com.ratz.bonsaicorner.repository.TreeGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpeciesMapper {

  private FamilyRepository familyRepository;
  private TreeGroupRepository treeGroupRepository;

//  public Species speciesDtoToEntity(SpeciesDTO speciesDTO) {
//
//    Species species = new Species();
//    species.setId(speciesDTO.getId());
//    species.setSpecies(speciesDTO.getSpecies());
//    species.setDescription(speciesDTO.getDescription());
//    species.setPruning(speciesDTO.getPruning());
//    species.setWatering(speciesDTO.getWatering());
//    species.setTransplant(speciesDTO.getTransplant());
//    species.setSoil(speciesDTO.getSoil());
//    species.setPropagation(speciesDTO.getPropagation());
//    species.setRegions(speciesDTO.getRegions());
//    species.setSunExposure(speciesDTO.getSunExposure());
//    species.setFertilizing(speciesDTO.getFertilizing());
//    species.setCommonBonsaiStyles(speciesDTO.getCommonBonsaiStyles());
//    Family family = familyRepository.findByFamilyName(speciesDTO.getFamily().getFamilyName());
//    species.setFamily(family);
//    TreeGroup treeGroup = treeGroupRepository.findTreeGroupByTreeGroupName(speciesDTO.getGroup().getTreeGroupName());
//    species.setTreeGroup(treeGroup);
//
//    return species;
//  }
//
//  public SpeciesDTO speciesEntityToDto(Species species) {
//
//    SpeciesDTO speciesDTO = new SpeciesDTO();
//    speciesDTO.setId(species.getId());
//    speciesDTO.setSpecies(species.getSpecies());
//    speciesDTO.setDescription(species.getDescription());
//    speciesDTO.setPruning(species.getPruning());
//    speciesDTO.setWatering(species.getWatering());
//    speciesDTO.setTransplant(species.getTransplant());
//    speciesDTO.setSoil(species.getSoil());
//    speciesDTO.setPropagation(species.getPropagation());
//    speciesDTO.setRegions(species.getRegions());
//    speciesDTO.setSunExposure(species.getSunExposure());
//    speciesDTO.setFertilizing(species.getFertilizing());
//    speciesDTO.setCommonBonsaiStyles(species.getCommonBonsaiStyles());
//    Family family = familyRepository.findByFamilyName(species.getFamily().getFamilyName());
//    speciesDTO.setFamily(family);
//    TreeGroup treeGroup = treeGroupRepository.findTreeGroupByTreeGroupName(species.getTreeGroup().getTreeGroupName());
//    speciesDTO.setGroup(treeGroup);
//
//    return speciesDTO;
//  }
}
