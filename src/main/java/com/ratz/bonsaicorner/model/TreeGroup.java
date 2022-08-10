package com.ratz.bonsaicorner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeGroup implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "tree_group_name")
  private String treeGroupName;

  @OneToMany(mappedBy = "treeGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Species> species;
}
