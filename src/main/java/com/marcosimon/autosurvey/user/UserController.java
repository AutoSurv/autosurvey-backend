package com.marcosimon.autosurvey.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{name}")
    public UserModel getUser(@PathVariable String name) {

        UserModel userModel = userService.getUserByName(name);
        return  userModel;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        String result = userService.createUser(userModel);
        if (result == null) return  ResponseEntity.status(409).build();

        return ResponseEntity.status(201).build();
    }
}
