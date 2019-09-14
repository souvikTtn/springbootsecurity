package com.example.springSecurity.springSecurityPractice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    /*@Override
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
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {/*//implementation 'org.springframework.boot:spring-boot-starter-data-jpa'*/
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    //order of authorisation should be from most restrictive to least restrictive
    @Override
    protected void configure(HttpSecurity http)
    throws Exception {
        http.authorizeRequests()
            .antMatchers("/users")
            .hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/admin")
            .hasAuthority("ADMIN")
            .antMatchers("/welcome").permitAll()
            .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}