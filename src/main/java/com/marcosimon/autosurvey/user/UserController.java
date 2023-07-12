package com.marcosimon.autosurvey.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000", "https://autosurvey-frontend.vercel.app"})
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
        return  userService.getUserByName(name);
    }

    @PostMapping("/new")
    public String createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }
}
