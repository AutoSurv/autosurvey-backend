package com.marcosimon.autosurvey.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDbRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findUserModelByUsername(String username);

}
