package com.hua.Distributed.systems.project.controller;

import com.hua.Distributed.systems.project.entity.Role;
import com.hua.Distributed.systems.project.entity.User;
import com.hua.Distributed.systems.project.repository.RolesRepository;
import com.hua.Distributed.systems.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_registration";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        System.out.println("Roles: "+user.getRoles());
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "home";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", rolesRepository.findAll());
        return "users";
    }

    @GetMapping("/user/{user_id}")
    public String showUser(@PathVariable Integer user_id, Model model){
        model.addAttribute("user", userService.getUser(user_id));
        return "edit_user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/user/role/delete/{user_id}/{role_id}")
    public String deleteRoleFromUser(@PathVariable Integer user_id, @PathVariable Integer role_id, Model model){
        User user = userService.getUser(user_id);
        Role role = rolesRepository.findById(role_id).get();
        user.getRoles().remove(role);
        userService.updateUser(user);

        System.out.println("Roles:" + user.getRoles());

        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", rolesRepository.findAll());
        return "users";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/user/role/add/{user_id}/{role_id}")
    public String addRoletoUser(@PathVariable Integer user_id, @PathVariable Integer role_id, Model model){
        User user = userService.getUser(user_id);
        Role role = rolesRepository.findById(role_id).get();
        user.getRoles().add(role);
        userService.updateUser(user);

        System.out.println("Roles: " + user.getRoles());

        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", rolesRepository.findAll());
        return "users";
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("user/{user_id}/{role_id}/delete")
    public String deleteUser(@PathVariable Integer user_id, @PathVariable Integer role_id, Model model){
        User user = userService.getUser(user_id);
        Role role = rolesRepository.findById(role_id).get();

        userService.deleteUser(user);
        rolesRepository.delete(role);

        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", rolesRepository.findAll());
        return "users";
    }

}
