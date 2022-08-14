package com.ratz.bonsaicorner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bonsais")
public class Bonsai implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "age", nullable = false)
  private int age;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "images")
  @ElementCollection
  private Set<String> images;

  @Column(name = "bonsai_creation_date")
  private LocalDate bonsaiCreationDate;

  @ManyToOne
  @JoinColumn(name = "species_id")
  private Species species;

  @OneToMany(mappedBy = "bonsai", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Intervention> interventionList = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
