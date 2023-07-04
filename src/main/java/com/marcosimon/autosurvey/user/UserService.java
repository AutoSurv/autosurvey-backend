package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.CreateUserDto;
import com.marcosimon.autosurvey.models.LoginUserDto;
import com.marcosimon.autosurvey.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public UserDto loginUser(LoginUserDto user) {
        User userResponse = userRepository.getByUserEmail(user.email());

        if (userResponse == null) return null;

        System.out.println("userService.loginUser.userResponse: " + userResponse);

        UserDto userDto = new UserDto(userResponse.getUserId(), userResponse.getEmail(), "");

        return null;
    }


    public UserDto addNewUser(CreateUserDto dto) {
        if (dto.email() == null || dto.email().equals("") || dto.password() == null || dto.password().equals("")) return null;

        User userResponse = userRepository.getByUserEmail(dto.email());
        System.out.println("userService.addNewUser.userResponse: " + userResponse);
        if (userResponse != null) return null;

        User newUser = userRepository.addNewUser(dto);
        System.out.println("userService.addNewUser.newUser: " + newUser);

        return new UserDto(newUser.getUserId(), newUser.getEmail(), "");

    }
}
