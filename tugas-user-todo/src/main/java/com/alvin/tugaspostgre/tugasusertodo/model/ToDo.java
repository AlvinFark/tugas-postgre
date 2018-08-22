package com.alvin.tugaspostgre.tugasusertodo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "todolist")
public class ToDo extends AuditModel {
  @Id
  @GeneratedValue(generator = "to_do_generator")
  @SequenceGenerator(
      name = "to_do_generator",
      sequenceName = "to_do_sequence",
      initialValue = 2000
  )
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private User user;

}
