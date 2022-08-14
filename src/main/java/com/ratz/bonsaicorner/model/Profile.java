package com.ratz.bonsaicorner.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "profile")
@ToString
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private LocalDate birthDate;

  private String country;

  private String profilePhoto;

  private Integer age;

  private Integer experienceYears;

  private Integer numberOfTrees;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
