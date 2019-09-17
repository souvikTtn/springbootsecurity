package com.springSecurityJpa.springSecurityJpa.event;

import com.springSecurityJpa.springSecurityJpa.entity.Role;
import com.springSecurityJpa.springSecurityJpa.entity.User;
import com.springSecurityJpa.springSecurityJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserBootstrap {

    @Autowired
    UserRepository userRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void init(){
        System.out.println("your application is up and running");

        if(!userRepository.findAll().iterator().hasNext()){
            User user=new User();
            user.setFirstName("Souvik");
            user.setLastName("Chakraborty");
            user.setEmail("souvik.chakraborty@tothenew.com");
            user.setPassword("souvikdgreat");
            user.setFullName(user.getFirstName()+" "+user.getLastName());
            user.setActive(true);
            Set<Role> roles=new HashSet<>();
            roles.add(Role.ADMIN);
            roles.add(Role.USER);
            user.setRoleSet(roles);
            userRepository.save(user);

            User user1 = new User();
            user1.setActive(true);
            user1.setEmail("deepika.tiwari@tothenew.com");
            user1.setFirstName("Deepika");
            user1.setLastName("Tiwari");
            user1.setPassword("deesssooo");
            user1.setFullName(user1.getFirstName()+" "+user1.getLastName());
            userRepository.save(user1);
        }
    }
}
