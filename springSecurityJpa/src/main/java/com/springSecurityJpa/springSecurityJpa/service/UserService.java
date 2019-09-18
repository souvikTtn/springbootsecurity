package com.springSecurityJpa.springSecurityJpa.service;

import com.springSecurityJpa.springSecurityJpa.entity.User;
import com.springSecurityJpa.springSecurityJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(User user) {
        Integer id = user.getId();
        Optional<User> editUser = userRepository.findById(id);
        editUser.orElseThrow(() -> new UsernameNotFoundException("No User Exists"));
        editUser.get().setEmail(user.getEmail());
        return userRepository.save(editUser.get());
    }
}

