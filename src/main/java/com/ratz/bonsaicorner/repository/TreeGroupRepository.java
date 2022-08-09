package com.ratz.bonsaicorner.repository;

import com.ratz.bonsaicorner.model.TreeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeGroupRepository extends JpaRepository<TreeGroup, Long> {

  TreeGroup findTreeGroupByTreeGroupName(String treeGroupName);
}
