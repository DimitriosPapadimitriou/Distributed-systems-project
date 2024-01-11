package com.hua.Distributed.systems.project.rest;

import com.hua.Distributed.systems.project.config.JwtUtils;
import com.hua.Distributed.systems.project.entity.Role;
import com.hua.Distributed.systems.project.entity.User;
import com.hua.Distributed.systems.project.payload.request.LoginRequest;
import com.hua.Distributed.systems.project.payload.request.SignupRequest;
import com.hua.Distributed.systems.project.payload.response.JwtResponse;
import com.hua.Distributed.systems.project.payload.response.MessageResponse;
import com.hua.Distributed.systems.project.repository.RolesRepository;
import com.hua.Distributed.systems.project.repository.UserRepository;
import com.hua.Distributed.systems.project.service.UserDetailsImpl;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostConstruct
    public void setup() {
        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_ADMIN"));
            return null;
        });
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_USER"));
            return null;
        });
        roleRepository.findByName("ROLE_FARMER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_FARMER"));
            return null;
        });
        roleRepository.findByName("ROLE_INSPECTOR").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_INSPECTOR"));
            return null;
        });

        this.userRepository.findByUsername("user1").orElseGet(() -> {
            User user = new User("user1", "user1@gmail.com", encoder.encode("user1"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_ADMIN").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
        this.userRepository.findByUsername("user2").orElseGet(() -> {
            User user = new User("user2", "user2@gmail.com", encoder.encode("user2"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_FARMER").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
        this.userRepository.findByUsername("user3").orElseGet(() -> {
            User user = new User("user3", "user3@gmail.com", encoder.encode("user3"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_INSPECTOR").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "farmer":
                        Role farmerRole = roleRepository.findByName("ROLE_FARMER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(farmerRole);

                        break;
                    case "inspector":
                        Role inspectorRole = roleRepository.findByName("ROLE_INSPECTOR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(inspectorRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("authentication");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("authentication: " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("post authentication");
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("jw: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
