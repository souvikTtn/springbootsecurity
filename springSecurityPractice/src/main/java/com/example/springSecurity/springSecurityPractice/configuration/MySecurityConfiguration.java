package com.example.springSecurity.springSecurityPractice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
        //set your configuration in the auth object
        //configuring in memory authentication
        auth.inMemoryAuthentication()
            .withUser("souvik")
            .password("ABCdef123@")
            .roles("ADMIN")
            .and()
            .withUser("subham")
            .password("1234")
            .roles("USER");
    }

    //order of authorisation should be from most restrictive to least restrictive
    @Override
    protected void configure(HttpSecurity http)
    throws Exception {
        http.authorizeRequests()
            .antMatchers("/users")
            .hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin")
            .hasRole("ADMIN")
            .antMatchers("/welcome").permitAll()
            .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
