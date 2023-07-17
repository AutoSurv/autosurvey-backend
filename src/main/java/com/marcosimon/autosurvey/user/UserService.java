package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
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
    @Autowired
    OrganizationRepository organizationRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserByName(String userName) {
        return userRepository.findUserModelByUsername(userName).orElse(null);
    }

    public String createUser(UserModel userModel) {
        UserModel isUser = userRepository.findUserModelByUsername(userModel.getUsername()).orElse(null);
        if (isUser != null) return null;

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(userModel.getRoles().toUpperCase());
        UserModel newUser =  userRepository.save(userModel);

        Organization org = new Organization ("", userModel);
        Organization newOrg = organizationRepository.saveOrganization(org);

        newUser.setOrganization(newOrg);
        userRepository.save(newUser);
        return String.format("User [%s] has been added to the database", newUser.getUsername());
    }
}
