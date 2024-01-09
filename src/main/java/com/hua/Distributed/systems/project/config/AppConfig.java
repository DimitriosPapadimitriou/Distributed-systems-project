package com.hua.Distributed.systems.project.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

//    @Autowired
//    private UserService userService;

//    @PostConstruct //tried to make some users on startup alla kati leei gia context cycle
//    public void setUp(){
//        User user1 = new User("user1", "user1@hua.gr", "user1");
//        User user2 = new User("user2", "user2@hua.gr", "user2");
//        User user3 = new User("user3", "user3@hua.gr", "user3");
//
//        Set<Roles> roles = new HashSet<>();
//        roles.add(new Roles("USER"));
//        user1.setRoles(roles);
//        user2.setRoles(roles);
//        roles.add(new Roles("ADMIN"));
//        user3.setRoles(roles);
//
//        userService.saveUser(user1);
//        userService.saveUser(user2);
//        userService.saveUser(user3);
//
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
