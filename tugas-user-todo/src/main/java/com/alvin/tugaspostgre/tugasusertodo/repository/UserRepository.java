package com.alvin.tugaspostgre.tugasusertodo.repository;

import com.alvin.tugaspostgre.tugasusertodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}