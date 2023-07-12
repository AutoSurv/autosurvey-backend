package com.marcosimon.autosurvey.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

<<<<<<< HEAD
=======
//    public UserModel getUserByName(String userName) {
//        return userRepository.findUserModelByUsername(userName).orElse(null);
//    }

>>>>>>> main
    public String createUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(userModel.getRoles().toUpperCase());
        userRepository.save(userModel);
        return String.format("User [%s] has been added to the database", userModel.getUsername());
    }
}
