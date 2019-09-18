package com.springSecurityJpa.springSecurityJpa.controller;

import com.springSecurityJpa.springSecurityJpa.entity.User;
import com.springSecurityJpa.springSecurityJpa.repository.UserRepository;
import com.springSecurityJpa.springSecurityJpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class homeController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String getHomePage() {
        return "<h1>welcome spring security accessed by all</h1>";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public List<User> getUserPage(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminPage() {
        return "<h1> this is a Admin page only accessible by admin</h1>";
    }

    @GetMapping("/users/{id}")
    /*@PreAuthorize("#userName==authentication.principal.username")*/
    @PreAuthorize("")
    @PostAuthorize("returnObject.getEmail() == authentication.getName()")
    public User getUserDetails(@PathVariable Integer id) {
       return (userRepository.findById(id)).orElseThrow(() -> new UsernameNotFoundException("No such User"));
    }
}
