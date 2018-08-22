package com.alvin.tugaspostgre.tugasusertodo.repository;

import com.alvin.tugaspostgre.tugasusertodo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
  List<ToDo> findByUserId(Long userId);
}
