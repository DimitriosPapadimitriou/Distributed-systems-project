package com.hua.Distributed.systems.project.service;

import com.hua.Distributed.systems.project.entity.Role;
import com.hua.Distributed.systems.project.entity.User;
import com.hua.Distributed.systems.project.repository.RolesRepository;
import com.hua.Distributed.systems.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//fernei stoixeia user otan kanei login
        Optional<User> opt = userRepository.findByUsername(username);

        if(opt.isEmpty())
            throw new UsernameNotFoundException("User with email: " +username +" not found !");
        else {
            User user = opt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(role-> new SimpleGrantedAuthority(role.toString()))
                            .collect(Collectors.toSet())
            );
        }
    }

    @Transactional
    public Integer saveUser(User user){
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        Role role =  rolesRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user = userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public User getUser(Integer id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public Object getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Integer updateUser(User user){
        user = userRepository.save(user);
        return  user.getId();
    }

    public String deleteUser(User user){
        try {
            userRepository.delete(user);
        }catch (Exception e){
            return "Error deleting user: " + e.getMessage();
        }
        return "User successfully deleted.";
    }
}
