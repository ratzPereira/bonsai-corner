package com.ratz.bonsaicorner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Data
@AllArgsConstructor
public class Permission implements GrantedAuthority {

  @Serial
  private static final long serialVersionUID = 3905055687532527066L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String description;

  public Permission() {
  }

  @Override
  public String getAuthority() {
    return this.description;
  }

}
