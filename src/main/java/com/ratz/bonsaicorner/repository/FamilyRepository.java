package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamilyRepository extends JpaRepository<Family, Long> {

  @Query(value = "select p from Family p where p.familyName like lower(concat('%', :family, '%'))")
  Family findByFamilyName(@Param("family") String family);
}
