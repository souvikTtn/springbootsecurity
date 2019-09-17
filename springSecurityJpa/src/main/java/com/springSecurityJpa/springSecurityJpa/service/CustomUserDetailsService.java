package com.springSecurityJpa.springSecurityJpa.service;

import com.springSecurityJpa.springSecurityJpa.entity.User;
import com.springSecurityJpa.springSecurityJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException("No user with this username"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoleSet().forEach(it -> {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(it.name());
            authorities.add(authority);
        });

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
                                                                      user.get().getActive(), true,                                                                                          true, true,
                                                                      authorities);
    }
}
