package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.UserOrgResponseDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getUserByName(String userName) {
        return userRepository.findUserModelByUsername(userName).orElse(null);
    }

    /*public String createUser(UserModel userModel) {
        System.out.println(userModel.getUsername());
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(userModel.getRoles().toUpperCase());
        Organization org = new Organization ("", userModel);
        System.out.println(org.getOrgId());
        Organization newOrg = organizationRepository.saveOrganization(org);
        System.out.println(newOrg.getUsers().get(0).getUsername());
        userModel.setOrganization(newOrg);
        userRepository.save(userModel);
        return String.format("User [%s] has been added to the database", userModel.getUsername());
    }*/

    public String createUser(UserModel userModel) {
        Organization org = new Organization ("", userModel);
        UserModel newUser = new UserModel(UUID.randomUUID().toString(), userModel.getUsername(), userModel.getPassword(), userModel.getEmail(), userModel.getRoles(), org);
        System.out.println(newUser.getUserId());
        newUser.setPassword(passwordEncoder.encode(userModel.getPassword()));
        newUser.setRoles(userModel.getRoles().toUpperCase());
        System.out.println(newUser.getUsername());
        Organization newOrg = organizationRepository.saveOrganization(org);
        System.out.println(newOrg.getUsers().get(0).getUsername());
        userRepository.save(newUser);
        return String.format("User [%s] has been added to the database", userModel.getUsername());
    }

}
