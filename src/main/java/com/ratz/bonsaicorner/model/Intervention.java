package com.ratz.bonsaicorner.model;

import com.ratz.bonsaicorner.enums.InterventionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interventions")
public class Intervention implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "job")
  private String job;

  @Column(name = "intervention_date")
  private LocalDate interventionDate;

  @Column(name = "images")
  @ElementCollection
  private Set<String> images;

  @Enumerated(EnumType.STRING)
  private InterventionStatus interventionStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bonsai_id", nullable = false)
  private Bonsai bonsai;

}
