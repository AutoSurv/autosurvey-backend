package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.CreateUserDto;
import com.marcosimon.autosurvey.models.LoginUserDto;
import com.marcosimon.autosurvey.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;
/*
    @GetMapping
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginUserDto user) {
        System.out.println("userController.loginUser.user: " + user);
        if (user == null ||
            user.email() == null || user.email().equals("") ||
            user.password() == null || user.password().equals("")) return ResponseEntity.badRequest().build();

        UserDto userDto = userService.loginUser(user);

        if (userDto == null) return ResponseEntity.notFound().build();

         return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> addNewUser(@RequestBody CreateUserDto dto, HttpServletRequest request) {
        System.out.println("userController.addNewUser.dto: " + dto);
        if (dto == null) return ResponseEntity.badRequest().build();

        UserDto newUser = userService.addNewUser(dto);

        if (newUser == null) return ResponseEntity.unprocessableEntity().build();

        URI location = URI.create((request.getRequestURI() + "/" + newUser.userId()));
        return ResponseEntity.created(location).body(newUser);
    }
*/

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody LoginUserDto dto, HttpServletRequest request) {

        return ResponseEntity.ok(userService.authenticateUser(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserDto dto, HttpServletRequest request) {

        return ResponseEntity.ok(userService.registerUser(dto));
    }


    @PatchMapping
    public ResponseEntity<UserDto> editUser(String userEmail) {

        return null;
    }
}
