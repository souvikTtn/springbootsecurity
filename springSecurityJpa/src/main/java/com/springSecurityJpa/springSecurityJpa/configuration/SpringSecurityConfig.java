package com.springSecurityJpa.springSecurityJpa.configuration;

import com.springSecurityJpa.springSecurityJpa.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void configure(HttpSecurity http)
    throws Exception {
        /*http.authorizeRequests()
            .antMatchers("/users")
            .hasAnyAuthority("USER,ADMIN")
            .antMatchers("/admin")
            .hasAuthority("ADMIN")
            .antMatchers("/").permitAll()
            .and().formLogin();*/
        http.authorizeRequests().antMatchers("/").permitAll().and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
