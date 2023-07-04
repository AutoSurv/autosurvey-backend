package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.CreateUserDto;
import com.marcosimon.autosurvey.models.LoginUserDto;
import com.marcosimon.autosurvey.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.config.JwtService;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserDto authenticateUser(LoginUserDto user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.email(),
                        user.password()
                )
        );

        User userResponse = userRepository.getByUserEmail(user.email());

        if (userResponse == null) return null;

        String accessToken = jwtService.generateToken(userResponse);
        System.out.println("userService.addNewUser.loginUser.accessToken: " + accessToken);

        UserDto userDto = new UserDto(userResponse.getUserId(), userResponse.getEmail(), accessToken);
        System.out.println("userService.authenticateUser.userDto: " + userDto);
        return userDto;
    }


    public UserDto registerUser(CreateUserDto dto) {
        if (dto.email() == null || dto.email().equals("") || dto.password() == null || dto.password().equals("")) return null;

        User userResponse = userRepository.getByUserEmail(dto.email());
        System.out.println("userService.addNewUser.userResponse: " + userResponse);
        if (userResponse != null) return null;

        User newUser = userRepository.addNewUser(new CreateUserDto(dto.email(), passwordEncoder.encode(dto.password()), dto.role()));
        //User newUser = userRepository.addNewUser(new CreateUserDto(dto.email(), dto.password(), dto.role()));

        String accessToken = jwtService.generateToken(newUser);
        System.out.println("userService.addNewUser.newUser.accessToken: " + accessToken);

        UserDto userDto = new UserDto(newUser.getUserId(), newUser.getEmail(), accessToken);
        System.out.println("userService.authenticateUser.userDto: " + userDto);
        return userDto;

    }


}
