package com.hua.Distributed.systems.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity//energopoioume tous security kanones
@EnableMethodSecurity(securedEnabled = true)//to idio alla se controllers
public class SecurityConfig {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(BCryptPasswordEncoder passwordEncoder){//ftiaxnoume users in memory
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{//http security me vasi ta paths
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/register", "/saveUser").permitAll()
                        .requestMatchers("/course/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


}
