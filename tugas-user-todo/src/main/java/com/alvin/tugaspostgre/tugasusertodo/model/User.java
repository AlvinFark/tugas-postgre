package com.alvin.tugaspostgre.tugasusertodo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditModel {
  @Id
  @GeneratedValue(generator = "user_generator")
  @SequenceGenerator(
      name = "user_generator",
      sequenceName = "user_sequence",
      initialValue = 1000
  )
  private Long id;

  @NotBlank
  @Size(max = 100)
  private String name;

}
