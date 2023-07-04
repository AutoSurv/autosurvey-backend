package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.CreateUserDto;
import com.marcosimon.autosurvey.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    UserDbRepository userDbRepository;
    public User getByUserEmail(String email) {
        return userDbRepository.findByEmail(email);
    }

    public User addNewUser(CreateUserDto dto) {
        return userDbRepository.save(new User(dto.email(), dto.password()));
    }
}
