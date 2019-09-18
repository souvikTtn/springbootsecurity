package com.springSecurityJpa.springSecurityJpa.repository;

import com.springSecurityJpa.springSecurityJpa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends CrudRepository<User,Integer> {
     Optional<User> findByEmail(String email);
     List<User> findAll();
}
