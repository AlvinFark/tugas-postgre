package com.alvin.tugaspostgre.tugasusertodo.controller;

import com.alvin.tugaspostgre.tugasusertodo.exception.ResourceNotFoundException;
import com.alvin.tugaspostgre.tugasusertodo.model.ToDo;
import com.alvin.tugaspostgre.tugasusertodo.repository.ToDoRepository;
import com.alvin.tugaspostgre.tugasusertodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ToDoController {

  @Autowired
  private ToDoRepository todoRepository;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users/{userId}/todolist")
  public List<ToDo> getToDolistByUserId(@PathVariable Long userId){
    return todoRepository.findByUserId(userId);
  }

  @PostMapping("/users/{userId}/todolist")
  public ToDo addToDo(@PathVariable Long userId,
                      @Valid @RequestBody ToDo toDo){
    return userRepository.findById(userId).map(user -> {
      toDo.setUser(user);
      return todoRepository.save(toDo);
    }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
  }

  @PutMapping("/users/{userId}/todolist/{todoId}")
  public ToDo updateToDo(@PathVariable Long userId,
                         @PathVariable Long todoId,
                         @Valid @RequestBody ToDo todoRequest){
    if(!userRepository.existsById(userId)){
      throw new ResourceNotFoundException("User not found with id " + userId);
    }
    return todoRepository.findById(todoId).map(todo -> {
      todo.setName(todoRequest.getName());
      return todoRepository.save(todo);
    }).orElseThrow(() -> new ResourceNotFoundException("To Do not found with id " + todoId));
  }

  @DeleteMapping("/users/{userId}/todolist/{todoId}")
  public ResponseEntity<?> deleteToDp(@PathVariable Long userId,
                                        @PathVariable Long todoId){
    if(!userRepository.existsById(userId)){
      throw new ResourceNotFoundException("User not found with id " + userId);
    }
    return todoRepository.findById(todoId).map(todo -> {
      todoRepository.delete(todo);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("To Do not found with id " + todoId));
  }

}