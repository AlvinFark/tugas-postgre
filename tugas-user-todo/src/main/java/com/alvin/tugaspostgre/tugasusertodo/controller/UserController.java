package com.alvin.tugaspostgre.tugasusertodo.controller;

import com.alvin.tugaspostgre.tugasusertodo.exception.ResourceNotFoundException;
import com.alvin.tugaspostgre.tugasusertodo.model.User;
import com.alvin.tugaspostgre.tugasusertodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public Page<User> getUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user){
    return userRepository.save(user);
  }

  @PutMapping("/users/{userId}")
  public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest){
    return userRepository.findById(userId).map(user -> {
      user.setName(userRequest.getName());
      return userRepository.save(user);
      }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
  }

  @DeleteMapping("/users/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Long userId){
    return userRepository.findById(userId).map(user -> {
      userRepository.delete(user);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
  }

}
