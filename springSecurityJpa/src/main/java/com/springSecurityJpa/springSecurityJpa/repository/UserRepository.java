package com.springSecurityJpa.springSecurityJpa.repository;

import com.springSecurityJpa.springSecurityJpa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    public Optional<User> findByEmail(String email);
}
