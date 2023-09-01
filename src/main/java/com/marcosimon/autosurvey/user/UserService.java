package com.marcosimon.autosurvey.user;


import com.marcosimon.autosurvey.models.UserOrgResponseDTO;
import com.marcosimon.autosurvey.organization.Organization;
import com.marcosimon.autosurvey.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserOrgResponseDTO> getAllUsers() {
        List<UserModel> userModels = userRepository.findAll();
        List<UserOrgResponseDTO> dtoList = new ArrayList<>();
        for(UserModel userModel : userModels) {
            dtoList.add(new UserOrgResponseDTO(userModel.getUserId(),
                            userModel.getUsername(),
                            userModel.getEmail(),
                            userModel.getRoles(),
                            userModel.getStatus()));
        }
        return dtoList;
    }


    public UserModel getUserByName(String userName) {
        return userRepository.findUserModelByUsername(userName).orElse(null);
    }

    public UserOrgResponseDTO getUserDtoByName(String userName) {
        UserModel userModel = userRepository.findUserModelByUsername(userName).orElse(null);
        if(userModel != null) return new UserOrgResponseDTO(
                userModel.getUserId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getRoles(),
                userModel.getStatus());

        return null;
    }

    public UserModel getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public String createUser(UserModel userModel) {
        UserModel isUser = userRepository.findUserModelByUsername(userModel.getUsername()).orElse(null);

        //if user is found return user
        if (isUser != null) return "present";
        //String.format("User [%s] already present in the database!", isUser.getUsername());

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(userModel.getRoles().toUpperCase());
        UserModel newUser =  userRepository.save(userModel);

//        Organization org = new Organization ("", newUser);
//        Organization newOrg = organizationRepository.saveOrganization(org);
//
//        newUser.setOrganization(newOrg);
        userRepository.save(newUser);
        return "created";

    }

    public UserModel editStatus(String name, String status) {
        UserModel user = userRepository.findUserModelByUsername(name).orElse(null);
        if (user != null) {
            user.setStatus(status);
            UserModel updatedUser = userRepository.save(user);
            return updatedUser;
        }
        return null;
    }

}
