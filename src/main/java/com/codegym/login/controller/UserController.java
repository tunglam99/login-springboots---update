package com.codegym.login.controller;

import com.codegym.login.model.Role;
import com.codegym.login.model.User;
import com.codegym.login.service.RoleService;
import com.codegym.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class UserController {
    public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String TEXT = "To confirm account click here :"
            + "http://localhost:5000/confirm-account?token=";
    public static String SUBJECT = "Đăng ký thành công";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        Iterable<User> users = userService.findAll();
        for (User currentUser: users) {
            if (currentUser.getUsername().equals(user.getUsername())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        Role role = roleService.findRoleByName(DEFAULT_ROLE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

}
